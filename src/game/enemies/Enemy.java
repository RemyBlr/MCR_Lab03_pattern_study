
package game.enemies;

import game.Position;
import window.DrawingCanvas;
import window.TDWindow;

import java.awt.*;

public abstract class Enemy {
    protected Position pos;
    private final double speed;
    private int reward = 10; // Gold reward
    private static final int SIZE = 20;
    Position director; // Vector director towards castle

    public Enemy(double speed) {
        this.speed = speed;
        this.pos = initializeStartingPos();
    }

    // Gives the ennemy a random position around the castle
    private Position initializeStartingPos() {
        double randomAngle = Math.random() * 2 * Math.PI;
        double radius = 700; // Spawn radius around the castle

        // Get castle center position instead of top-left
        Position castleCenter = new Position(
                TDWindow.getCastlePos().getX() + TDWindow.getCastleWidth()/2,
                TDWindow.getCastlePos().getY() + TDWindow.getCastleHeight()/2
        );

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
        director = new Position(dx, dy);

        return new Position(randomX, randomY);
    }

    // For now just move towards the castle
    public void update() {
        pos.setX(pos.getX() + director.getX());
        pos.setY(pos.getY() + director.getY());
    }

    public abstract void draw(Graphics2D g2d);

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