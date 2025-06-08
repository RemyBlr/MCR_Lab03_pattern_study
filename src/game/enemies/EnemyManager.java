package game.enemies;

import game.Game;
import game.Position;
import game.Wall;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EnemyManager {
    private EnemyFactory enemyFactory;
    private LinkedList<Enemy> waitingEnemies;       // File d'attente des ennemis de la vague généré par la factory
    private List<Enemy> activeEnemies;              // Ennemis actuellement sur le terrain

    private long lastSpawnTime;
    private static final long MIN_SPAWN_INTERVAL = 2000; // Attendre au moins 2s entre chaque spawn'

    public EnemyManager() {
        this.enemyFactory = new PeacefullModeFactory();
        this.waitingEnemies = new LinkedList<>();
        this.activeEnemies = new ArrayList<>();
        this.lastSpawnTime = System.currentTimeMillis();
    }

    public void update() {
        Game game = Game.getInstance();

        // Create a new wave
        if (waitingEnemies.isEmpty() && activeEnemies.isEmpty() && !game.isPausedGame())
            initializeNewWave();

        // Spawn enemies if any with a minimum delay between each spawn
        long currentTime = System.currentTimeMillis();
        if (!waitingEnemies.isEmpty() && currentTime - lastSpawnTime >= MIN_SPAWN_INTERVAL) {
            Enemy enemy = waitingEnemies.removeFirst();
            activeEnemies.add(enemy);

            lastSpawnTime = currentTime;
        }

        // Update active enemies and check for collisions
        if(!activeEnemies.isEmpty()) {
            Iterator<Enemy> iterator = activeEnemies.iterator();
            while(iterator.hasNext()) {
                Enemy enemy = iterator.next();

                // Check if the next position would hit a wall
                Wall hitWall = hitWall(enemy);
                if (hitWall != null) {
                    // Remove both the wall and the enemy
                    Game.getInstance().removeWall(hitWall);
                    game.setGold(enemy.getReward());
                    iterator.remove();
                    continue;
                }

                enemy.update(); // Move the enemy if no wall collision

                if (isAtCastle(enemy.getPos())) {
                    game.setDamageToBase(2); // Damage to the base
                    iterator.remove();
                }
            }
        }

        // Difficulty increasing by time/waves
        if(game.getWaveCount() >= 10 && game.getWaveCount() < 999 && waitingEnemies.isEmpty())
            enemyFactory = new ExpertModeFactory();
        else if(game.getWaveCount() >= 5 && waitingEnemies.isEmpty())
            enemyFactory = new HardModeFactory();
        else if(game.getWaveCount() >= 2 && waitingEnemies.isEmpty())
            enemyFactory = new NormalModeFactory();
    }

    private Wall hitWall(Enemy enemy) {
        for(Wall wall : Game.getInstance().getWalls()) {

            // Actual width of the wall
            BasicStroke stroke = new BasicStroke(
                    wall.getWidth(),
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND
            );

            Shape wallShape = stroke.createStrokedShape(wall.getPath());

            // Get actual enemy hit box
            Rectangle enemyBounds = new Rectangle(
                    (int)(enemy.getPos().getX() - enemy.getSize()/2),
                    (int)(enemy.getPos().getY() - enemy.getSize()/2),
                    enemy.getSize(),
                    enemy.getSize()
            );

            // Check if the enemy's bounds intersect with the wall && is of the same color
            if (wall.getColor() == enemy.getColor() && wallShape.intersects(enemyBounds)) {
                return wall;
            }
        }
        return null;
    }

    private boolean isAtCastle(Position pos) {
        Position castlePosition = Game.getInstance().getCastle().getPosition();
        double castleX = castlePosition.getX() + Game.getInstance().getCastle().getWidth()/2;
        double castleY = castlePosition.getY() + Game.getInstance().getCastle().getHeight()/2;

        double distance = Math.sqrt(
                Math.pow(pos.getX() - castleX, 2) +
                        Math.pow(pos.getY() - castleY, 2)
        );

        return distance < Game.getInstance().getCastle().getRadius();
    }

    private void initializeNewWave() {
        Game.getInstance().nextWave();

        if (enemyFactory != null) {
            waitingEnemies = enemyFactory.createEnemies();
        }

        System.out.println("New wave initialized.");
    }

    public List<Enemy> getActiveEnemies() {
        return this.activeEnemies;
    }

    public void setMode(EnemyFactory factory) {
        this.enemyFactory = factory;
    }
}