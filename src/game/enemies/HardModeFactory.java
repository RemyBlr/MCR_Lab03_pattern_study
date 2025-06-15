package game.enemies;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class HardModeFactory extends EnemyFactory {

    public HardModeFactory() { this.NB_ENEMIES = 12; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        Random random = new Random();
        IntStream.range(0, NB_ENEMIES).forEach(i -> {
            int chance = random.nextInt(100);
            if (chance < 45)         // 45% Quazi
                enemies.add(new Quazi());
            else if (chance < 75)    // 30% Quaza
                enemies.add(new Quaza());
            else                     // 25% Quazo
                enemies.add(new Quazo());
        });

        return enemies;
    }
}