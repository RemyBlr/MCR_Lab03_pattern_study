package game.enemies;

import java.util.LinkedList;

public class PeacefullModeFactory extends EnemyFactory {

    public PeacefullModeFactory() { this.NB_ENEMIES = 10; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        // Only quazo
        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> enemies.add(new Quazo()));
        return enemies;
    }
}
