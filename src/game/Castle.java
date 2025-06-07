package game;

public class Castle {
    private static final int WIDTH = 170;
    private static final int HEIGHT = 170;
    private static final int RADIUS = WIDTH / 2;
    private static final int DEFENSE_RADIUS = 200;


    private final Position position;
    private int hp;
    private int defenseRadius;
    private int maxHp;

    public Castle(int hp) {
        this.position = new Position(0, 0);
        this.hp = hp;
        this.maxHp = hp;
        this.defenseRadius = DEFENSE_RADIUS;
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

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void addHp(int amount) {
        hp += amount;
        maxHp += amount;
    }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public int getDefenseRadius() {
        return defenseRadius;
    }

    public void increaseDefenseRadius(int amount) {
        defenseRadius += amount;
    }
}
