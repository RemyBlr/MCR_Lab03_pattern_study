package game.enemies;

import java.util.LinkedList;

public class PeacefullModeFactory extends EnemyFactory {
    private final int NB_ENEMIES = 10;

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for(int i = 0; i < NB_ENEMIES; i++) {
            enemies.add(new Quaz());
        }

        return enemies;
    }
}
