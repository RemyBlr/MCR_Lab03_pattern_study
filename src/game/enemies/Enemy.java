
package game.enemies;

import game.Position;
import game.Game;

import java.awt.*;

public abstract class Enemy {
    protected Position pos;
    private final double speed;
    private Color color;
    private final int reward; // Gold reward

    private static final int SIZE = 20;
    Position director; // Vector director towards castle from the spawning point

    public Enemy(double speed, Color color, int reward) {
        this.speed = speed;
        this.color = color;
        this.reward = reward;
        this.pos = initializeStartingPos();
    }

    // Gives the ennemy a random position around the castle
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

    // Basic straigth movement by default
    public void update() {
        pos.setX(pos.getX() + director.getX());
        pos.setY(pos.getY() + director.getY());
    }

    public abstract void draw(Graphics2D g2d);

    public Position getPos() {
        return pos;
    }

    public int getSize() { return SIZE; }

    public Color getColor() { return color; }

    public int getReward() {
        return reward;
    }
}