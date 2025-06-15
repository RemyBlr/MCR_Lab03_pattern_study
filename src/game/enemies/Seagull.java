/*
 * File: Seagull.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Seagull is a rapid enemy that goes straight to the castle.
 * Version: 1.0
 */
package game.enemies;

import java.awt.*;

public class Seagull extends Enemy {
    public Seagull() {
        super(3.0, Color.RED, 20, 8);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int size = getSize();
        int x = (int) pos.getX();
        int y = (int) pos.getY();

        // Draw the main body
        g2d.setColor(getColor());
        g2d.fillOval(x - size/2, y - size/2, size, size);

        // Draw wings
        int wingWidth = size * 2;
        int wingHeight = size/2;
        g2d.fillArc(x - wingWidth/2, y - wingHeight/2, wingWidth, wingHeight, 0, 180);

        // Draw beak
        g2d.setColor(Color.ORANGE);
        int[] xPoints = {x + size/2, x + size/2 + size/4, x + size/2};
        int[] yPoints = {y - size/4, y, y + size/4};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}