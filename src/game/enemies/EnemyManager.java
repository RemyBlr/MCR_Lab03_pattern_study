package game.enemies;

import game.Game;
import game.Position;
import game.Wall;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The EnemyManager class is responsible for managing enemy behavior in the game.
 * It handles the generation of new enemy waves, spawning enemies, tracking their
 * positions, and managing collisions between enemies and other game objects such as walls
 * or the castle. The class ensures a dynamic difficulty progression by adjusting the
 * enemy spawning factory based on the wave count.
 *
 */
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

    /**
     * Updates the state of the enemy manager and processes game logic related to enemy waves and interactions.
     *
     * This method manages the spawning of enemies, transitions between difficulty levels
     * based on the current wave count, updates the movement of active enemies, checks for
     * collisions with walls, and applies damage to the castle if an enemy reaches its destination.
     *
     * The method ensures proper management of enemies, wall collisions, and wave mechanics to
     * maintain the game's progression and balance.
     */
    public void update() {
        Game game = Game.getInstance();

        if(waitingEnemies.isEmpty() && activeEnemies.isEmpty()){
            if(game.getWaveCount() >= 10)
                enemyFactory = new ExpertModeFactory();
            else if(game.getWaveCount() >= 5)
                enemyFactory = new HardModeFactory();
            else if(game.getWaveCount() >= 2)
                enemyFactory = new NormalModeFactory();

             initializeNewWave();
        }

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
                    // Refund wall cost
                    Game.getInstance().setInk(hitWall.getCost());
                    // Remove both the wall and the enemy
                    Game.getInstance().removeWall(hitWall);
                    game.setGold(enemy.getReward());
                    iterator.remove();
                    continue;
                }

                enemy.update(); // Move the enemy if no wall collision

                if (isAtCastle(enemy.getPos())) {
                    game.getCastle().takeDamage(enemy.getDamage());
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Checks if the given enemy collides with any wall in the game and returns the collided wall.
     *
     * The method iterates through all walls in the game and calculates their actual
     * shape and boundaries. It then checks if the enemy's hitbox intersects with a wall
     * of the same color. If a collision is detected, the corresponding wall is returned.
     *
     * @param enemy the enemy being checked for collision with walls
     * @return the wall that the enemy collides with, or null if no collision is detected
     */
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

    /**
     * Determines if the given position is within the castle's radius.
     *
     * @param pos the position to check
     * @return true if the position is within the castle's radius, false otherwise
     */
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

    /**
     * Initializes the settings for a new wave in the game. This method performs the following
     */
    private void initializeNewWave() {
        Game.getInstance().nextWave();

        if (enemyFactory != null) {
            waitingEnemies = enemyFactory.createEnemies();
        }

        System.out.println("New wave initialized.");
    }

    /**
     * Retrieves a list of all currently active enemies in the game.
     * The list contains enemies that are actively moving or engaging
     * within the game environment.
     *
     * @return a List of Enemy objects representing the active enemies.
     */
    public List<Enemy> getActiveEnemies() {
        return this.activeEnemies;
    }

    /**
     * Sets the enemy creation mode by assigning the specified EnemyFactory instance.
     * This factory defines the logic for creating and managing enemy objects in the game.
     *
     * @param factory an instance of EnemyFactory that determines how enemies are created.
     */
    public void setMode(EnemyFactory factory) {
        this.enemyFactory = factory;
    }

    /**
     * Resets the state of the enemy manager, preparing it for a new wave or a game restart.
     *
     * Clears the lists of active and waiting enemies, ensuring that no enemies remain in the game world.
     * Updates the last spawn time to the current system time, resetting the spawn timer for new enemy generation.
     */
    public void reset() {
        this.activeEnemies.clear();
        this.waitingEnemies.clear();
        this.lastSpawnTime = System.currentTimeMillis();
    }
}