package game.enemies;

import game.Position;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Seagull extends Enemy {
    private static final Color SEAGULL_COLOR = new Color(200, 200, 200); // Light gray

    public Seagull(double speed) {
        super(speed);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Save the current transform
        AffineTransform oldTransform = g2d.getTransform();

        // Calculate angle towards castle for rotation
        double dx = castlePosition.getX() - pos.getX();
        double dy = castlePosition.getY() - pos.getY();
        double angle = Math.atan2(dy, dx);

        // Translate to seagull position and rotate
        g2d.translate(pos.getX(), pos.getY());
        g2d.rotate(angle);

        // Draw seagull
        g2d.setColor(SEAGULL_COLOR);
        g2d.fillOval(-10, -10, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.fillArc(-15, -5, 30, 10, 0, 180); // Wings
        g2d.setColor(Color.BLACK);
        g2d.drawOval(-10, -10, 20, 20);

        // Restore the original transform
        g2d.setTransform(oldTransform);
    }
}