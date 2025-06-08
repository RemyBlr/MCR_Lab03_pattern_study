package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class NormalModeFactory extends EnemyFactory {

    public NormalModeFactory() { this.NB_ENEMIES = 15; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 50)
                enemies.add(new Quazo());
            else
                enemies.add(new Quaza());
        });

        return enemies;
    }
}
