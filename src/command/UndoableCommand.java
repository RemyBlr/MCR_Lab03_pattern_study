package command;

public interface UndoableCommand extends Command {
    /**
     * Undo the command
     */
    void undo();
}
