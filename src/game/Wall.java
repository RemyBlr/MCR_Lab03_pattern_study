package game;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Wall class represents a wall in the game.
 * It contains the path, color, width, and cost of the wall.
 */
public class Wall {
    private final Path2D path;
    private final Color color;
    private final int width;
    private final int cost;

    /**
     * @param path wall path
     * @param color color of the wall
     * @param width width of the wall
     * @param cost ink cost of the wall
     */
    public Wall(Path2D path, Color color, int width, int cost) {
        this.path = (Path2D) path.clone();
        this.color = color;
        this.width = width;
        this.cost = cost;
    }

    /**
     * @return the path of the wall
     */
    public Path2D getPath() { return (Path2D) path.clone(); }

    /**
     * @return the color of the wall
     */
    public Color getColor() { return color; }

    /**
     * @return the width of the wall
     */
    public int getWidth() { return width; }

    /**
     * @return the cost of the wall
     */
    public int getCost() { return cost; }

    /**
     * Checks if the wall collides with an enemy.
     * For simplicity, the enemy's hitbox is a rectangle
     */
    public boolean isCollision(Enemy enemy) {
        Rectangle bounds = path.getBounds();
        return enemy.getBounds().intersects(bounds);
    }

    // TODO completer cette classe
}
