
package game.enemies;

import game.Position;
import java.awt.*;

public class Enemy {
    protected Position pos;
    private final double speed;
    private int reward = 10; // Gold reward
    private static final int SIZE = 20;

    public Enemy(double speed) {
        this.speed = speed;
        this.pos = initializeStartingPos();
    }

    // Gives the ennemy a random position around the castle
    private Position initializeStartingPos() {
        double randomAngle = Math.random() * 2 * Math.PI;
        double radius = 800; // Spawn radius around the castle
        double randomX = castlePosition.getX() + radius * Math.cos(randomAngle); // castlePosition needed
        double randomY = castlePosition.getY() + radius * Math.sin(randomAngle);
        return new Position(randomX, randomY);
    }

    public void update() {
        // Calculate direction vector toward castle
        double dx = castlePosition.getX() - pos.getX();
        double dy = castlePosition.getY() - pos.getY();

        // Normalize the direction vector
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            dx = dx / distance * speed;
            dy = dy / distance * speed;
        }

        pos.setX(pos.getX() + dx);
        pos.setY(pos.getY() + dy);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval((int) pos.getX() - SIZE / 2, (int) pos.getY() - SIZE / 2, SIZE, SIZE);
    }

    public Position getPos() {
        return pos;
    }

    public double getSpeed() {
        return speed;
    }

    public int getSize() {
        return SIZE;
    }

    public int getReward() {
        return reward;
    }
}