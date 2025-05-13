package command;

import game.Game;
import window.DrawingCanvas;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * CreateWallCommand is responsible for creating a wall in the game.
 * It combines the ink cost change and the wall drawing into a single command.
 */
public class CreateWallCommand implements UndoableCommand {
    private final CompositeCommand compositeCommand = new CompositeCommand();

    /**
     * Constructor for CreateWallCommand.
     *
     * @param game The game instance to change the ink for.
     * @param canvas The canvas to draw the wall on.
     * @param path The path of the wall.
     * @param color The color of the wall.
     * @param wallWidth The width of the wall.
     * @param wallCost The cost of the wall in ink.
     */
    public CreateWallCommand(Game game,
                             DrawingCanvas canvas,
                             Path2D path,
                             Color color,
                             int wallWidth,
                             int wallCost) {
        // Remove ink cost from the game
        compositeCommand.addCommand(new ChangeInkCommand(game, -wallCost));
        // Draw the wall and add it to the game
        compositeCommand.addCommand(new DrawWallCommand(game, canvas, path, color, wallWidth, wallCost));
    }

    /**
     * Execute the command.
     * This will draw the wall and remove the ink cost from the game.
     */
    @Override
    public void execute() { compositeCommand.execute(); }

    /**
     * Undo the command.
     * This will remove the wall and add the ink cost back to the game.
     */
    @Override
    public void undo() { compositeCommand.undo(); }
}
