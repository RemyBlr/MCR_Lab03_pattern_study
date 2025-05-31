package game.enemies;

import java.awt.*;

public class Quaz extends Enemy {
    private static final Color QUAZ_COLOR = new Color(147, 112, 219); // Purple
    private static final double QUAZ_SPEED = 1.5;

    public Quaz() {
        super(QUAZ_SPEED);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int size = getSize();
        int x = (int) pos.getX();
        int y = (int) pos.getY();

        // Draw square body
        g2d.setColor(QUAZ_COLOR);
        g2d.fillRect(x - size/2, y - size/2, size, size);

        // Draw eyes
        g2d.setColor(Color.WHITE);
        int eyeSize = size/3;
        g2d.fillOval(x - size/3, y - size/4, eyeSize, eyeSize);
        g2d.fillOval(x, y - size/4, eyeSize, eyeSize);
    }
}
