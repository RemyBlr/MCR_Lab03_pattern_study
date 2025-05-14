package command;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * CompositeCommand is a command that can contain multiple commands.
 * It allows for executing and undoing all contained commands as a single unit.
 */
public class CompositeCommand implements UndoableCommand {
    private final List<UndoableCommand> commands = new ArrayList<>();

    /**
     * Adds a command to the composite command.
     * @param command The command to add.
     */
    public void addCommand(UndoableCommand command) { commands.add(command); }

    @Override
    public void execute() {
        for (UndoableCommand command : commands)
            command.execute();
    }

    @Override
    public void undo() {
        ListIterator<UndoableCommand> it = commands.listIterator(commands.size());
        while (it.hasPrevious())
            it.previous().undo();
    }
}
