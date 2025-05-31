package game.enemies;

import game.Game;
import java.util.*;

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
        if (waitingEnemies.isEmpty() && activeEnemies.isEmpty() && !game.isPausedGame()) {
            if (game.getWaveCount() > 0) {
                initializeNewWave();
            }
        }

        // Spawn existing enemies if any
        long currentTime = System.currentTimeMillis();
        if (!waitingEnemies.isEmpty() && currentTime - lastSpawnTime >= MIN_SPAWN_INTERVAL) {
            Enemy enemy = waitingEnemies.removeFirst();
            activeEnemies.add(enemy);

            lastSpawnTime = currentTime;
        }

        // Mise à jour des ennemis actifs
        if(!activeEnemies.isEmpty()) {
            for (Enemy enemy : activeEnemies) {
                enemy.update();
            }
        }
    }

//    private boolean isAtCastle(Position pos) {
//        // Implement castle collision detection
//        // This is a simple example - adjust the values based on your castle size
//        double castleX = 400; // Should match castle center X
//        double castleY = 300; // Should match castle center Y
//        double distance = Math.sqrt(
//                Math.pow(pos.getX() - castleX, 2) +
//                        Math.pow(pos.getY() - castleY, 2)
//        );
//        return distance < 50; // Adjust radius as needed
//    }

    private void initializeNewWave() {
        Game.getInstance().nextWave();

        if (enemyFactory != null) {
            waitingEnemies = enemyFactory.createEnemies();
        }
    }

    public List<Enemy> getActiveEnemies() {
        return this.activeEnemies;
    }
}