package game.enemies;

import java.awt.geom.Path2D;
import java.util.LinkedList;

public abstract class EnemyFactory {
      abstract public LinkedList<Enemy> createEnemies();
}

