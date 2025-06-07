package game.enemies;

import java.awt.*;

public class Seagull extends Enemy {
    private static final Color COLOR = new Color(220, 220, 220);
    private static final double SEAGULL_SPEED = 3.0;

    public Seagull() {
        super(SEAGULL_SPEED);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int size = getSize();
        int x = (int) pos.getX();
        int y = (int) pos.getY();

        // Draw the main body
        g2d.setColor(COLOR);
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