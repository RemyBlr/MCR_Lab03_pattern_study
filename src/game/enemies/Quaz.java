package game.enemies;

import java.awt.*;

abstract class Quaz extends Enemy {

    public Quaz(double speed, Color color) {
        super(speed);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int size = getSize();
        int x = (int) pos.getX();
        int y = (int) pos.getY();

        // Draw square body
        g2d.setColor(this.color);
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
        super(1.2, Color.BLACK);
    }
}

class Quaza extends Quaz {
    public Quaza() {
        super(1.5, Color.BLUE);
    }
}

//class Quavu extends Quaz {
//    public Quavu() {
//        super(1.7, Color.GREEN);
//    }
//}

class Quazy extends Quaz {
    public Quazy() {
        super(2.0, Color.RED);
    }
}