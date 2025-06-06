package game.enemies;

import java.util.LinkedList;
import java.util.Random;

public class NormalModeFactory extends EnemyFactory {
    private final int NB_ENEMIES = 25;

    @Override
    public LinkedList<Enemy> createEnemies() {
        LinkedList<Enemy> enemies = new LinkedList<>();
        for(int i = 0; i < NB_ENEMIES; i++) {
            if (new Random().nextInt(100) % 3 == 0)
                enemies.add(new Seagull());
            else
                enemies.add(new Quaz());
        }

        return enemies;
    }
}
