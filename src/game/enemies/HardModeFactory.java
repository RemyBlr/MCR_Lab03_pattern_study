package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class HardModeFactory extends EnemyFactory {

    public HardModeFactory() { this.NB_ENEMIES = 25; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 35)
                enemies.add(new Quaza());
            else if (chance < 60)
                enemies.add(new Quazi());
            else
                enemies.add(new Quazo());
        });

        return enemies;
    }
}

