/*
 * File: PeacefullModeFactory.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: PeacefullModeFactory is an implementation of the factory that add Quazo enemies.
 * Version: 1.0
 */
package game.enemies;

import java.util.LinkedList;

public class PeacefullModeFactory extends EnemyFactory {

    public PeacefullModeFactory() { this.NB_ENEMIES = 5; }

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();

        // Only quazo
        java.util.stream.IntStream.range(0, NB_ENEMIES).forEach(i -> enemies.add(new Quazo()));
        return enemies;
    }
}
