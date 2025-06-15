/*
 * File: RestartGameCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: RestartGameCommand class represents a command to restart the game.
 * Version: 1.0
 */
package command;
import app.Main;

/**
 * RestartGameCommand is a concrete implementation of the Command interface.
 * This command is responsible for restarting the game by invoking the restart functionality
 * of the main game window. If the main game window exists, its associated restart logic is triggered.
 *
 * The restart process typically involves resetting various components of the game, such as:
 * - Game state
 * - UI elements (e.g., status bars, shop panels, toolbars)
 * - Removing any "Game Over" panels
 * - Redrawing the game frame
 */
public class RestartGameCommand implements Command {

    public RestartGameCommand() {
    }

    @Override
    public void execute() {
        if (Main.mainWindow != null) {
            Main.mainWindow.restartGame();
        }
    }
}
