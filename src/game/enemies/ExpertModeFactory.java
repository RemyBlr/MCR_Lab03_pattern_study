package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class ExpertModeFactory extends EnemyFactory {
    public ExpertModeFactory() { this.NB_ENEMIES = 30; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 25)
                enemies.add(new Seagull());
            else if (chance < 30)
                enemies.add(new Quazi());
            else if (chance < 60)
                enemies.add(new Quaza());
            else
                enemies.add(new Quazo());
        });

        return enemies;
    }
}
