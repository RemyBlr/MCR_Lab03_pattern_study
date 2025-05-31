package game.enemies;

import java.awt.geom.Path2D;
import java.util.LinkedList;

public class NormalModeFactory extends EnemyFactory {
    private final int NB_ENEMIES = 25;

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for(int i = 0; i < NB_ENEMIES; i++) {
            double speed = 1 * (0.8 + Math.random() * 0.4); // enemies speed between 0.8 and 1.2
            enemies.add(new Seagull(speed));
        }

        return enemies;
    }
}
