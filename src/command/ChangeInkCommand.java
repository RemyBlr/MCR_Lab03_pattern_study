/*
 * File: ChangeInkCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: ChangeInkCommand class represents a command to change the ink amount in the game.
 * Version: 1.0
 */
package command;

import game.Game;

/**
 * Represents a command to change the ink amount in the game.
 * This command is part of the command pattern and is undoable.
 */
public class ChangeInkCommand implements UndoableCommand{
    private final int amount;

    /**
     * Constructor for ChangeInkCommand.
     *
     * @param amount The amount of ink to change.
     */
    public ChangeInkCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute() { Game.getInstance().setInk(amount); }

    @Override
    public void undo() { Game.getInstance().setInk(-amount); }
}
