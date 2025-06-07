package game.enemies;

import java.awt.*;

abstract class Quaz extends Enemy {

    public Quaz(double speed, Color color, int reward) {
        super(speed, color, reward);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int size = getSize();
        int x = (int) pos.getX();
        int y = (int) pos.getY();

        // Draw square body
        g2d.setColor(getColor());
        g2d.fillRect(x - size/2, y - size/2, size, size);

        // Draw eyes
        g2d.setColor(Color.WHITE);
        int eyeSize = size/3;
        g2d.fillOval(x - size/3, y - size/4, eyeSize, eyeSize);
        g2d.fillOval(x, y - size/4, eyeSize, eyeSize);
    }
}

class Quazo extends Quaz {
    public Quazo() {
        super(1.2, Color.BLACK, 1);
    }
}

class Quaza extends Quaz {
    public Quaza() {
        super(1.5, Color.BLUE, 2);
    }
}

class Quazi extends Quaz {
    public Quazi() {
        super(2, Color.GREEN, 2);
    }
}

class Quaqua extends Quaz {
    public Quaqua() {
        super(2.5, Color.ORANGE, 10);
    }
}