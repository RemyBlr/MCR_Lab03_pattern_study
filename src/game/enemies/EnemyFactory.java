/*
 * File: EnemyFactory.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: EnemyFactory is an abstraction for the factory design pattern.
 * Version: 1.0
 */
package game.enemies;

import java.util.LinkedList;


/**
 * The EnemyFactory class is an abstract base class that provides a blueprint for
 * creating enemy objects in the game. Subclasses must implement the method
 * to define specific logic for generating a collection of enemies with varied characteristics.
 *
 * This class includes a configurable number of enemies to be created, represented
 * by the field NB_ENEMIES. The subclasses determine the specific attributes
 * and types of enemies that are instantiated.
 */
public abstract class EnemyFactory {
      protected int NB_ENEMIES = 50;

      abstract public LinkedList<Enemy> createEnemies();
}

