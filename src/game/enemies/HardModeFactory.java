package game.enemies;

import game.Position;

import java.util.LinkedList;

public class HardModeFactory extends EnemyFactory {
    private final int NB_ENEMIES = 50;

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for(int i = 0; i < NB_ENEMIES; i++) {
            double speed = 1 * (1.2 + Math.random() * 0.4); // enemies speed between 1.2 and 1.6
            enemies.add(new Seagull(speed));
        }

        return enemies;
    }
}

