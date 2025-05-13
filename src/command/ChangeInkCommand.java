package command;

import game.Game;

/**
 * Command to change the ink amount in the game.
 * This command is undoable, meaning it can be reversed.
 */
public class ChangeInkCommand implements UndoableCommand{
    private final Game game;
    private final int amount;

    /**
     * Constructor for ChangeInkCommand.
     *
     * @param game The game instance to change the ink for.
     * @param amount The amount of ink to change.
     */
    public ChangeInkCommand(Game game, int amount) {
        this.game = game;
        this.amount = amount;
    }

    @Override
    public void execute() { game.setInk(amount); }

    @Override
    public void undo() { game.setInk(-amount); }
}
