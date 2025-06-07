package game;

public class Castle {
    private static final int WIDTH = 170;
    private static final int HEIGHT = 170;
    private static final int RADIUS = WIDTH / 2;

    private final Position position;

    public Castle() {
        this.position = new Position(0, 0);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        this.position.setXY(x, y);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getRadius() {
        return RADIUS;
    }

    public Position getCenter() {
        return new Position(position.getX() + WIDTH / 2, position.getY() + HEIGHT / 2);
    }
}
