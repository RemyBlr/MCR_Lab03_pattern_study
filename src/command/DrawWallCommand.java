/*
 * File: DrawWallCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: DrawWallCommand class represents a command to draw a wall in the game.
 * Version: 1.0
 */
package command;

import game.Game;
import game.Wall;
import window.DrawingCanvas;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * The DrawWallCommand class represents a command responsible for drawing a wall on a canvas.
 * This command can be executed and undone, making it suitable for use in systems with undo/redo functionality.
 *
 * The wall is represented by a path, color, width, and associated cost, and is rendered on a provided canvas.
 */
public class DrawWallCommand implements UndoableCommand {
    private final int wallWidth;
    private final Color color;
    private final DrawingCanvas canvas;
    private final Path2D path;
    private Wall wall;

    public DrawWallCommand(DrawingCanvas canvas,
                           Path2D path,
                           Color color,
                           int wallWidth,
                           int wallCost) {
        this.canvas = canvas;
        this.path = path;
        this.color = color;
        this.wallWidth = wallWidth;
        this.wall = new Wall(path, color, wallWidth, wallCost);
    }

    @Override
    public void execute() {
        System.out.println("Drawing wall...");
        Game.getInstance().addWall(wall);
        canvas.repaint();
    }

    @Override
    public void undo() {
        System.out.println("Undoing wall drawing...");

        Game.getInstance().removeWall(wall);
        canvas.repaint();
    }
}
