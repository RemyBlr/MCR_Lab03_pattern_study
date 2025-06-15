/*
 * File: TogglePauseCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: TogglePauseCommand class represents a command to toggle the game state.
 * Version: 1.0
 */
package command;

import game.Game;
import game.State;

/**
 * A command that toggles the pause state of the game.
 * Implements the Command interface and is part of the Command pattern
 * for managing game state changes.
 *
 * This command toggles the game's state between RUNNING and PAUSED:
 * - If the game state is RUNNING, the command pauses the game.
 * - If the game state is PAUSED, the command resumes the game.
 */
public class TogglePauseCommand implements Command {

    @Override
    public void execute() {
        Game game = Game.getInstance();
        if (game.getState() == State.RUNNING) {
            game.pause();
        } else if (game.getState() == State.PAUSED) {
            game.resume();
        }
    }
}
