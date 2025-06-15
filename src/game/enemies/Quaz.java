/*
 * File: Quaz.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Quazs are square enemies with specific attributes and behaviors.
 * Version: 1.0
 */
package game.enemies;

import java.awt.*;

abstract class Quaz extends Enemy {

    public Quaz(double speed, Color color, int reward, int damage) {
        super(speed, color, reward, damage);
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
        super(1.2, Color.BLACK, 2, 1);
    }
}

class Quaza extends Quaz {
    public Quaza() {
        super(1.5, Color.BLUE, 5, 2);
    }

    @Override
    public void update() {
        double time = System.currentTimeMillis() / 200.0;
        pos.setX(pos.getX() + director.getX() + Math.sin(time) * 10);
        pos.setY(pos.getY() + director.getY());
    }
}

class Quazi extends Quaz {
    public Quazi() {
        super(2, Color.GREEN, 10, 5);
    }

    @Override
    public void update() {
        double time = System.currentTimeMillis() / 200.0;
        double radius = 10;
        pos.setX(pos.getX() + director.getX() + Math.cos(time) * radius);
        pos.setY(pos.getY() + director.getY() + Math.sin(time) * radius);
    }
}

class Quaqua extends Quaz {
    public Quaqua() {
        super(0.5, Color.ORANGE, 15, 10);
    }

    @Override
    public void update() {
        double time = System.currentTimeMillis() / 200.0;
        pos.setX(pos.getX() + director.getX() + Math.sin(time * 0.5) * 15);
        pos.setY(pos.getY() + director.getY() + Math.cos(time * 0.5) * 15);
    }
}