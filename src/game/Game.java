package game;

import game.enemies.EnemyManager;

import java.util.ArrayList;
import java.util.List;

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
    private int defenseRadius = 200;

    private List<GameObserver> observers = new ArrayList<>();

    // TODO : use a state instead
    private GameState gameState;

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

        this.gameState = new GameState();
    }


    public void tick(){
        timeElapsed = System.nanoTime() - startTime;
        enemyManager.update();
        notifyObservers();
    }

    // TODO ajouter les futures classes

    /**
     * Sets the new base health points.
     * Math.max is used to ensure that the base health points do not go below 0.
     *
     * @param amount the amount of health that the base will lose
     */
    public void setDamageToBase(int amount) { baseHp = Math.max(0, baseHp - amount); }

    public long getTimeElapsed() { return timeElapsed; }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public void addBaseHp(int amount) { baseHp += amount; }

    //region Defense zone
    public int getDefenseRadius() { return defenseRadius; }

    public void increaseDefenseRadius(int amount) {
        defenseRadius += amount;
        notifyObservers();
    }
    //endregion

    //region Gold
    /**
     * Return the amount of gold available
     * @return int
     */
    public int getGold() { return gold; }

    /**
     * Sets the new gold amount.
     *
     * @param amount the amount of gold to change
     */
    public void setGold(int amount) { gold = gold + amount; }

    /**
     * @param amount the amount of gold that will be used
     * @return true if there is enough ink to use
     */
    public boolean canUseGold(int amount) {return gold >= amount;}
    //endregion

    //region Wave management
    /**
     * Get the number of current wave
     * @return int WaveNumber
     */
    public int getWaveCount() { return waveNumber; }

    /**
     * Go to the next wave
     */
    public void nextWave() { this.waveNumber++; }

    public int getWaveNumber() {
        long elapsedSeconds = timeElapsed / 1_000_000_000L;
        // Every 60 seconds, increase the wave number
        if (elapsedSeconds / 60 > waveNumber - 1) {
            waveNumber++;
        }
        return waveNumber;
    }
    //endregion

    //region Ink Management
    /**
     * Returns the amount of ink left
     * @return int amount
     */
    public int getInk() { return ink; }

    /**
     * Sets the new ink amount.
     *
     * @param amount the amount of ink to change
     */
    public void setInk(int amount) {
        ink = ink + amount;
    }

    /**
     * @param amount the amount of ink that will be used
     * @return true if there is enough ink to use
     */
    public boolean canUseInk(int amount) {return ink >= amount;}

    /**
     * Increase how much ink you can store
     * @param amount int
     */
    public void increaseMaxInk(int amount) {
        maxInk += amount;
        ink += amount;
    }

    /**
     * Get the maximum amount of int
     * @return int max int amount
     */
    public int getMaxInk() { return maxInk; }

    /**
     * Refill ink up to max ink
     */
    public void refillInk() { ink = maxInk; }
    //endregion

    //region Wall Management
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
    //endregion

    //region Pause
    public static boolean isPausedGame() { return isPausedGame; }

    public static void setPausedGame(boolean pausedGame) { isPausedGame = pausedGame; }
    //endregion

    //region Observer Management
    /**
     * Add an observer
     * @param observer GameObserver
     */
    public void addObserver(GameObserver observer) {
        if (observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }

    /**
     * Remove an observer from the list
     * @param observer GameObserver
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers
     * Call when changes have been made
     */
    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(); // will pass a state later I think
        }
    }
    //endregion

    //region Singleton
    /**
     * Get the singleton instance of Game.
     * @return Game instance
     */
    public static Game getInstance() {
        if(instance == null)
            instance = new Game(500, 10, 1000);
        return instance;
    }

    //endregion
}
