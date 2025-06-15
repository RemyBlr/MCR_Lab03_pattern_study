/*
 * File: CompositeCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: CompositeCommand class represents a command that can contain multiple commands.
 * Version: 1.0
 */
package command;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * CompositeCommand is a concrete implementation of the UndoableCommand interface.
 * It represents a composite command consisting of multiple UndoableCommand instances.
 *
 * This class allows multiple commands to be grouped together and executed as a single unit.
 * It also supports undo functionality for all commands it contains, in reverse order.
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
