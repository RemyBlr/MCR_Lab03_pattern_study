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
    private int gold;
    private List<Wall> walls = new ArrayList<>();
    private static EnemyManager enemyManager;
    private static boolean isPausedGame = false;
    private long timeElapsed = 0;
    private long startTime = System.nanoTime();
    private int waveNumber;
    private Castle castle;

    private State state;

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
        this.state = State.RUNNING;
        this.ink = ink;
        this.maxInk = ink;
        this.gold = gold;
        enemyManager = new EnemyManager();
        this.waveNumber = 1;
        this.castle = new Castle(baseHp);

        this.gameState = new GameState();
    }


    public void tick(){
        timeElapsed = System.nanoTime() - startTime;
        enemyManager.update();

        // check if castle dead
        if (castle.getHp() <= 0){
            gameOver();
        }
        notifyObservers();
    }

    private void gameOver(){
        state = State.GAMEOVER;

        System.out.println("Game Over !!");
        // other stuff ?
    }

    public static void reset() {
        instance = new Game(500, 2, 0);
    }


    public Castle getCastle() {
        return castle;
    }

    // TODO ajouter les futures classes


    public long getTimeElapsed() { return timeElapsed; }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public State getState(){
        return state;
    }

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
            instance = new Game(500, 2, 0);
        return instance;
    }

    //endregion
}
