package command;

import game.Game;

/**
 * Command to change the ink amount in the game.
 * This command is undoable, meaning it can be reversed.
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
