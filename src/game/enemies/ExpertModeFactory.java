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
            if (chance < 35) // 35% chance
                enemies.add(new Quazo());
            else if (chance < 60) // 25% chance
                enemies.add(new Quaza());
            else if (chance < 70) // 10% chance
                enemies.add(new Quazi());
            else // 30% chance
                enemies.add(new Seagull());
        });

        return enemies;
    }
}
