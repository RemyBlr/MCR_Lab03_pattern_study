package game.enemies;

import game.Game;
import java.util.*;

public class EnemyManager {
    private LinkedList<Enemy> waitingEnemies; // File d'attente des ennemis de la vague généré
    private List<Enemy> activeEnemies; // Ennemis actuellement sur le terrain
    private EnemyFactory enemyFactory;

    private long lastSpawnTime;
    private static final long MIN_SPAWN_INTERVAL = 2000; // Attendre au moins 2s entre chaque spawn'

    public EnemyManager() {
        this.waitingEnemies = new LinkedList<>();
        this.activeEnemies = new ArrayList<>();
        this.lastSpawnTime = System.currentTimeMillis();
    }

    public void update() {
        Game game = Game.getInstance();

        // New waves
        if (waitingEnemies.isEmpty() && activeEnemies.isEmpty() && !game.isPausedGame()) {
            if (game.getWaveCount() > 0) {
                initializeNewWave();
            }
        }

        // Spawn existing enemies
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

    private void initializeNewWave() {
        Game.getInstance().nextWave();

        if (enemyFactory != null) {
            waitingEnemies = enemyFactory.createEnemies();
        }
    }

    public void clearWave() {
        waitingEnemies.clear();
        activeEnemies.clear();
        this.lastSpawnTime = 0;
    }
}