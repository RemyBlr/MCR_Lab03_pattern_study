package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class NormalModeFactory extends EnemyFactory {

    public NormalModeFactory() { this.NB_ENEMIES = 50; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i ->
        {
            if (new Random().nextInt(100) % 2 == 0)
                enemies.add(new Quazo());
            else
                enemies.add(new Quaza());
        });

        return enemies;
    }
}
