package game.enemies;

import game.Position;

import java.awt.geom.Path2D;
import java.util.LinkedList;

public class HardModeFactory extends EnemyFactory {
    public HardModeFactory(Position castlePosition, double speed) {
        super(castlePosition, speed);
    }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            double randomSpeed = 1 * (0.8 + Math.random() * 0.4);
            enemies.add(new Seagull(1 + randomSpeed));
        }
        return enemies;
    }
}

