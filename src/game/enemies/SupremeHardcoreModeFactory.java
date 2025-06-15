/*
 * File: SupremeHardcoreModeFactory.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: SupremHardcoreModeFactory is an implementation of the factory that add Quaza enemies.
 * Version: 1.0
 */
package game.enemies;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class SupremeHardcoreModeFactory extends EnemyFactory{
    
    public SupremeHardcoreModeFactory() { this.NB_ENEMIES = 999; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        Random random = new Random();
        IntStream.range(0, 100).forEach(i -> {
            int chance = random.nextInt(100);
            if (chance < 25)
                enemies.add(new Quaqua());      // 0–24
            else if (chance < 45)
                enemies.add(new Seagull());     // 25–44
            else if (chance < 70)
                enemies.add(new Quazi());       // 45–69
            else if (chance < 85)
                enemies.add(new Quaza());       // 70–84
            else
                enemies.add(new Quazo());       // 85–99
        });

        return enemies;
    }
}
