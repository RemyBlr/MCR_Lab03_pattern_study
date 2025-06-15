/*
 * File: ExpertModeFactory.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: ExpertModeFactory is an implementation of the factory that add Seagull enemies.
 * Version: 1.0
 */
package game.enemies;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class ExpertModeFactory extends EnemyFactory {
    public ExpertModeFactory() { this.NB_ENEMIES = 15; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        Random random = new Random();
        IntStream.range(0, NB_ENEMIES).forEach(i -> {
            int chance = random.nextInt(100);
            if (chance < 30)         // 30% Seagull
                enemies.add(new Seagull());
            else if (chance < 60)    // 30% Quazi
                enemies.add(new Quazi());
            else if (chance < 85)    // 25% Quaza
                enemies.add(new Quaza());
            else                     // 15% Quazo
                enemies.add(new Quazo());
        });


        return enemies;
    }
}
