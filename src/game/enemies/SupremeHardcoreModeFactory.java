package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class SupremeHardcoreModeFactory extends EnemyFactory{
    
    public SupremeHardcoreModeFactory() { this.NB_ENEMIES = 999; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        java.util.stream.IntStream.range(0, 100).forEach(i -> {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance < 25)
                enemies.add(new Quaqua());
            else if (chance < 50)
                enemies.add(new Seagull());
            else if (chance < 70)
                enemies.add(new Quazi());
            else if (chance < 85)
                enemies.add(new Quaza());
            else
                enemies.add(new Quazo());
        });

        return enemies;
    }
}
