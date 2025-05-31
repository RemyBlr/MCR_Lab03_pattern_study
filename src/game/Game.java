package game;

import game.enemies.Enemy;
import game.enemies.EnemyManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Centralizes the game logic and manages the game state.
 */
public class Game {
    private static Game instance;

    private int ink;
    private int maxInk;
    private int baseHp;
    private int gold;
    private List<Wall> walls = new ArrayList<>();
    private static EnemyManager enemyManager;
    private static boolean isPausedGame = false;
    private long timeElapsed = 0;
    private long startTime = System.nanoTime();
    private int waveNumber;

    /**
     * Constructor for the game.Game class.
     *
     * @param ink ink amount you start with
     * @param baseHp health amount of the base at the beginning
     */
    private Game(int ink, int baseHp, int gold) {
        this.ink = ink;
        this.maxInk = ink;
        this.baseHp = baseHp;
        this.gold = gold;
        enemyManager = new EnemyManager();
        this.waveNumber = 1;
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
    }

    /**
     * Removes a wall from the list of walls.
     * @param wall the wall to remove
     */
    public void removeWall(Wall wall) {
        walls.remove(wall);
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

    public int getWaveCount() { return waveNumber; }

    public void nextWave() { this.waveNumber++; }

    public static boolean isPausedGame() { return isPausedGame; }

    public static void setPausedGame(boolean pausedGame) { isPausedGame = pausedGame; }

    /**
     * Get the singleton instance of TDWindow.
     *
     * @return TDWindow instance
     */
    public static Game getInstance() {
        if(instance == null)
            instance = new Game(500, 100, 1000);
        return instance;
    }

    public void tick(){
        timeElapsed = System.nanoTime() - startTime;
        enemyManager.update();
    }

    public long getTimeElapsed() { return timeElapsed; }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public int getWaveNumber() {
        long elapsedSeconds = timeElapsed / 1_000_000_000L;
        // Every 60 seconds, increase the wave number
        if (elapsedSeconds / 60 > waveNumber - 1) {
            waveNumber++;
        }
        return waveNumber;
    }

    public int getMaxInk() { return maxInk; }

    public void refillInk() { ink = maxInk; }

    public void increaseMaxInk(int amount) {
        maxInk += amount;
        ink += amount;
    }

    public void addBaseHp(int amount) { baseHp += amount; }
}
