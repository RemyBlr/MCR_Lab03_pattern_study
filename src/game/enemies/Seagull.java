package game.enemies;

import game.Position;

import java.awt.geom.Path2D;

import java.awt.*;
import java.awt.geom.Path2D;

public class Seagull extends Enemy {
    private static final Color SEAGULL_COLOR = new Color(200, 200, 200); // Gris clair

    public Seagull(Path2D path, double speed, int hp) {
        super(path, speed, hp);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw seagull
        g2d.setColor(SEAGULL_COLOR);
        g2d.fillOval(pos.getX() - 10, pos.getY() - 10, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.fillArc(pos.getX() - 15, pos.getY() - 5, 30, 10, 0, 180); // Wings
        g2d.setColor(Color.BLACK);
        g2d.drawOval(pos.getX() - 10, pos.getY() - 10, 20, 20);
    }
}
