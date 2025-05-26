package game;

import game.enemies.Enemy;
import game.enemies.EnemyManager;

import java.util.*;

/**
 * Centralizes the game logic and manages the game state.
 */
public class Game {
    private static Game instance;

    private int ink;
    private int baseHp;
    private int gold;
    private int waveCount = 0;
    private List<Wall> walls = new ArrayList<>();
    private static EnemyManager enemyManager;
    private static boolean isPausedGame = false;
    private long timeElapsed = 0;
    private long startTime = System.nanoTime();

    /**
     * Constructor for the game.Game class.
     *
     * @param ink ink amount you start with
     * @param baseHp health amount of the base at the beginning
     */
    private Game(int ink, int baseHp, int gold) {
        this.ink = ink;
        this.baseHp = baseHp;
        this.gold = gold;
        enemyManager = new EnemyManager();
    }

    /**
     * Sets the new ink amount.
     *
     * @param amount the amount of ink to change
     */
    public void setInk(int amount) {
        ink = ink + amount;
    }

    /**
     * Sets the new gold amount.
     *
     * @param amount the amount of gold to change
     */
    public void setGold(int amount) { gold = gold + amount; }

    /**
     * Sets the new base health points.
     * Math.max is used to ensure that the base health points do not go below 0.
     *
     * @param amount the amount of health that the base will lose
     */
    public void setDamageToBase(int amount) { baseHp = Math.max(0, baseHp - amount); }

    /**
     * @param amount the amount of ink that will be used
     * @return true if there is enough ink to use
     */
    public boolean canUseInk(int amount) {return ink >= amount;}

    /**
     * @param amount the amount of gold that will be used
     * @return true if there is enough ink to use
     */
    public boolean canUseGold(int amount) {return gold >= amount;}

    /**
     * Adds a wall to the list of walls.
     * @param wall the wall to add
     */
    public void addWall(Wall wall) {
        walls.add(wall);
        ink -= wall.getCost();
    }

    /**
     * Removes a wall from the list of walls.
     * @param wall the wall to remove
     */
    public void removeWall(Wall wall) {
        walls.remove(wall);
        ink += wall.getCost() / 2; // TODO faire mieux qu'un chiffre arbitraire
    }

    /**
     * @return A list of all the walls
     */
    public List<Wall> getWalls() { return walls; }

    // TODO ajouter les futures classes


    /**
     * Returns the amount of ink left
     * @return int amount
     */
    public int getInk() { return ink; }

    public int getGold() { return gold; }

    public int getWaveCount() { return waveCount; }

    public void nextWave() { this.waveCount++; }

    public static boolean isPausedGame() { return isPausedGame; }

    public static void setPausedGame(boolean pausedGame) { isPausedGame = pausedGame; }

    /**
     * Get the singleton instance of TDWindow.
     *
     * @return TDWindow instance
     */
    public static Game getInstance() {
        if(instance == null)
            instance = new Game(500, 100, 0);
        return instance;
    }

    public void tick(){
        timeElapsed = System.nanoTime() - startTime;
        enemyManager.update();
    }

    public long getTimeElapsed() { return timeElapsed; }

    public List<Enemy> getActiveEnemies() {
        return enemyManager.getActiveEnemies();
    }
}
