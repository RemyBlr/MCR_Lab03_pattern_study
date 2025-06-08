package game;

import game.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final int ink;
    private final int gold;
    private final int health;
    private final int wave;

    private final double timeElapsed;
    private final boolean isPaused;
    private final long tickDuration;       // Durée d'un tick en millisecondes (ex: 20)

    public GameState() {
        this.ink = 0;
        this.gold = 0;
        this.health = 100;
        this.wave = 0;
        this.timeElapsed = 0;
        this.isPaused = false;
        this.tickDuration = 20;
    }

    public double getTimeElapsed() { return timeElapsed; }
    public boolean isPaused() { return isPaused; }
    public long getTickDuration() { return tickDuration; }

    public String getFormattedTime() {
        long seconds = (long) timeElapsed;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}