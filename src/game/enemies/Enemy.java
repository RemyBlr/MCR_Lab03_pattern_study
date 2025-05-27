package game.enemies;

import game.Position;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Enemy {
    private final Path2D path;
    private Position pos;
    private final double speed;
    private int reward = 10; // Gold reward
//    private double distanceTraveled = 0.0;
    private static final int SIZE = 20;

    public Enemy(Path2D path, double speed) {
        this.path = (Path2D) path.clone();
        this.speed = speed;
        this.pos = initializeStartingPos();
    }

    /*
     * Gives a random starting position around the castle.
     */
    private Position initializeStartingPos() {
        Point2D startPoint = getPathPoint(0);
        double randomAngle = Math.random() * 2 * Math.PI;

        double radius = 800; // around the castle radius
        double randomX = startPoint.getX() + radius * Math.cos(randomAngle);
        double randomY = startPoint.getY() + radius * Math.sin(randomAngle);
        return new Position(randomX, randomY);
    }

    private Point2D getPathPoint(int i) {
        return null;
    }

    public void update() {
//        distanceTraveled += speed;
//        Point2D point = getPathPoint(distanceTraveled);
//        pos = new Position((int) point.getX(), (int) point.getY());
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval((int)pos.getX() - SIZE / 2, (int)pos.getY() - SIZE / 2, SIZE, SIZE);
    }

    public Position getPos() { return pos; }

    public double getSpeed() { return speed; }

    public int getSize() { return SIZE; }

    public int getReward() { return reward; }
}