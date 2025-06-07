package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class SupremModeFactory extends EnemyFactory{
    public SupremModeFactory() { this.NB_ENEMIES = 999; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> {
            if (new Random().nextInt(100) % 3 == 0) // 1/3 chance
                enemies.add(new Quazy());
            else if (new Random().nextInt(100) % 3 == 1) // 2/3 chance
                enemies.add(new Seagull());
            else if(new Random().nextInt(100) % 2 == 1) // 1/3 chance
                enemies.add(new Quazo());
            else
                enemies.add(new Quaza());
        });

        return enemies;
    }
}
