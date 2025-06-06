package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class HardModeFactory extends EnemyFactory {
    private final int NB_ENEMIES = 50;

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for(int i = 0; i < NB_ENEMIES; i++) {
            if (new Random().nextInt(10) % 2 == 0)
                enemies.add(new Seagull());
            else
                enemies.add(new Quaz());
        }

        return enemies;
    }
}

