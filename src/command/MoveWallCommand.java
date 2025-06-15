/*
 * File: MoveWallCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: MoveWallCommand class represents a command to move a wall.
 * Version: 1.0
 */
package command;

import game.Wall;

/**
 * MoveWallCommand class represents a command to move a wall.
 * It implements the UndoableCommand interface.
 */
public class MoveWallCommand implements UndoableCommand {
    private final Wall wall;
    private final double dx;
    private final double dy;

    /**
     * Constructor for MoveWallCommand.
     *
     * @param wall the wall to be moved
     * @param dx   the change in x coordinate
     * @param dy   the change in y coordinate
     */
    public MoveWallCommand(Wall wall, double dx, double dy) {
        this.wall = wall;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Executes the command to move the wall.
     */
    public void execute() {
        wall.move(dx, dy);
    }

    /**
     * Undoes the command to move the wall back to its original position.
     */
    public void undo() {
        wall.move(-dx, -dy);
    }
}
