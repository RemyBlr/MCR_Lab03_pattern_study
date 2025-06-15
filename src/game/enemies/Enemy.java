/*
 * File: Enemy.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Enemy class represent the abstraction of an enemy.
 * Version: 1.0
 */
package game.enemies;

import game.Position;
import game.Game;

import java.awt.*;

/**
 * The Enemy class represents an abstract entity which serves as the foundation
 * for various enemy types in the game. It handles movement, position tracking,
 * and attributes such as speed, reward, damage, and visual representation.
 * Concrete subclasses are required to provide specific rendering behavior.
 */
public abstract class Enemy {
    protected Position pos;
    private final double speed;
    private Color color;
    private final int reward; // Gold reward
    private final int damage;

    private static final int SIZE = 20;
    Position director; // Vector director towards castle from the spawning point

    public Enemy(double speed, Color color, int reward, int damage) {
        this.speed = speed;
        this.color = color;
        this.reward = reward;
        this.damage = damage;
        this.pos = initializeStartingPos();
    }

    /**
     * Initializes and calculates a random spawning position for the enemy
     * within a defined radius around the castle's center. It also computes
     * and sets the direction vector for movement towards the castle.
     *
     * @return a Position object representing the starting coordinates for the enemy.
     */
    private Position initializeStartingPos() {
        double randomAngle = Math.random() * 2 * Math.PI;
        double radius = 700; // Spawn radius around the castle

        Position castleCenter = Game.getInstance().getCastle().getCenter();

        double randomX = castleCenter.getX() + radius * Math.cos(randomAngle);
        double randomY = castleCenter.getY() + radius * Math.sin(randomAngle);

        // Calculate direction vector toward castle center
        double dx = (castleCenter.getX() - randomX);
        double dy = (castleCenter.getY() - randomY);
        //double distance = Math.sqrt(dx * dx + dy * dy); // Normalize
        double distance = new Position(randomX, randomY).distanceTo(castleCenter);

        if (distance > 0) {
            dx = dx / distance * speed;
            dy = dy / distance * speed;
        }
        this.director = new Position(dx, dy);

        return new Position(randomX, randomY);
    }

    /**
     * Straigth movement to the castle by default
     */
    public void update() {
        pos.setX(pos.getX() + director.getX());
        pos.setY(pos.getY() + director.getY());
    }

    /**
     * Renders the graphical representation of the enemy onto the provided graphics context.
     * This method must be implemented by all subclasses to define unique visual appearances
     * for different enemy types.
     *
     * @param g2d the Graphics2D object used for drawing the enemy on the screen.
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * Retrieves the current position of the enemy.
     *
     * @return a Position object representing the current coordinates of the enemy.
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Retrieves the size of the enemy, which determines its dimensions or scale
     * in the game.
     *
     * @return the size of the enemy as an integer.
     */
    public int getSize() { return SIZE; }

    /**
     * Retrieves the color associated with the enemy instance.
     *
     * @return the color representing the enemy.
     */
    public Color getColor() { return color; }

    /**
     * Retrieves the reward value associated with the enemy.
     *
     * @return the amount of gold or points rewarded upon defeating this enemy.
     */
    public int getReward() {
        return reward;
    }

    /**
     * Retrieves the damage value inflicted by the enemy.
     *
     * @return the amount of damage this enemy can deal.
     */
    public int getDamage() {
        return damage;
    }
}