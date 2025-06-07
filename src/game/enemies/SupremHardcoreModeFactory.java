package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class SupremHardcoreModeFactory extends EnemyFactory{
    
    public SupremHardcoreModeFactory() { this.NB_ENEMIES = 999; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 15) // 15% chance 
                enemies.add(new Quazo());
            else if (chance < 35) // 20% chance
                enemies.add(new Quaza());
            else if (chance < 50) // 15% chance
                enemies.add(new Quazi());
            else if (chance < 75) // 25% chance
                enemies.add(new Seagull());
            else // 25% chance
                enemies.add(new Quaqua());
        });

        return enemies;
    }
}
