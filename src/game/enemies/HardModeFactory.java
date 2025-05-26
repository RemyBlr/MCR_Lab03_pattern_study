package game.enemies;

import java.awt.geom.Path2D;
import java.util.LinkedList;

public class HardModeFactory extends EnemyFactory {
    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            enemies.add(new Seagull(path, 3.0, 200, 15));
        }
        return enemies;
    }
}
