package game.enemies;

import java.util.LinkedList;

public abstract class EnemyFactory {
      protected int NB_ENEMIES = 50;

      abstract public LinkedList<Enemy> createEnemies();
}

