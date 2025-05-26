package game.enemies;

import game.Game;
import java.util.*;

public class EnemyManager {
    private LinkedList<Enemy> waitingEnemies;         // File d'attente des ennemis à spawner
    private List<Enemy> activeEnemies;         // Ennemis actuellement sur le terrain
    private EnemyFactory enemyFactory;
    private static final long SPAWN_INTERVAL = 1000; // Intervalle entre chaque spawn (ms)
    private long lastSpawnTime;

    public EnemyManager() {
        this.waitingEnemies = new LinkedList<>();
        this.activeEnemies = new ArrayList<>();
        this.lastSpawnTime = System.currentTimeMillis();
    }

    public void setEnemyFactory(EnemyFactory factory) {
        this.enemyFactory = factory;
    }

    public void update() {
        Game game = Game.getInstance();

        // New waves
        if (waitingEnemies.isEmpty() && activeEnemies.isEmpty() && !game.isPausedGame()) {
            if (game.getWaveCount() > 0) {
                startNewWave();
            }
        }

        // Spawn existing enemies
        long currentTime = System.currentTimeMillis();
        if (!waitingEnemies.isEmpty() && currentTime - lastSpawnTime >= SPAWN_INTERVAL) {
            Enemy enemy = waitingEnemies.removeFirst();
            activeEnemies.add(enemy);
            lastSpawnTime = currentTime;
        }

        // Mise à jour des ennemis actifs
        updateActiveEnemies();
    }

    private void startNewWave() {
        Game.getInstance().nextWave();

        if (enemyFactory != null) {
            waitingEnemies = enemyFactory.createEnemies();
        }
    }

    private void updateActiveEnemies() {
        if(activeEnemies.isEmpty()) return;

        for (Enemy enemy : activeEnemies) {
            enemy.update();
        }
    }

    public List<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public boolean isWaveInProgress() {
        return !waitingEnemies.isEmpty() || !activeEnemies.isEmpty();
    }

    public void clearEnemies() {
        waitingEnemies.clear();
        activeEnemies.clear();
    }
}