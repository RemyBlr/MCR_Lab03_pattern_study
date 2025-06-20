/*
 * File: NormalModeFactory.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: NormalModeFactory is an implementation of the factory that add Quazo enemies.
 * Version: 1.0
 */
package game.enemies;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class NormalModeFactory extends EnemyFactory {

    public NormalModeFactory() { this.NB_ENEMIES = 10; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        Random random = new Random();
        IntStream.range(0, NB_ENEMIES).forEach(i -> {
            int chance = random.nextInt(100);
            if (chance < 70)         // 70% Quaza
                enemies.add(new Quaza());
            else                     // 30% Quazo
                enemies.add(new Quazo());
        });

        return enemies;
    }
}