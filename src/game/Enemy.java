package game;

import java.awt.Rectangle;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Enemy class represents an enemy in the game.
 */
public class Enemy {
    private final Path2D path;
    private final double speed;
    private int hp;
    private final int reward;
    private double distanceTraveled = 0.0;

    /**
     * @param path path that the enemy follows
     * @param speed enemy speed in pixels per second
     * @param hp enemy Health Points
     * @param reward golds rapportés à la mort
     */
    public Enemy(Path2D path, double speed, int hp, int reward) {
        this.path = (Path2D) path.clone();
        this.speed = speed;
        this.hp = hp;
        this.reward = reward;
    }

    /**
     * Get the current position of the enemy on the path.
     *
     * @return a Rectangle representing the enemy's hitbox
     */
    public Rectangle getBounds() { return path.getBounds(); }

    /**
     * @return true if enemy has no more HP
     */
    public boolean isDead() { return hp <= 0; }

    /**
     * @return the amount of gold the enemy gives when killed
     */
    public int getReward() { return reward; }

    /**
     * @return the distance traveled by the enemy
     */
    public double getDistanceTraveled() { return distanceTraveled; }

    /**
     * @return the enemy's speed
     */
    public double getSpeed() { return speed; }

    // TODO completer cette classe
}
