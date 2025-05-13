package command;

import java.util.Stack;

/**
 * CommandManager is responsible for managing the execution of commands.
 * It maintains a history of executed commands, allowing for undo functionality.
 */
public class CommandManager {
    private final Stack<UndoableCommand> history = new Stack<>();

    /**
     * Executes a command and pushes it onto the history stack.
     *
     * @param command The command to execute.
     */
    public void executeCommand(UndoableCommand command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if(!history.isEmpty()) {
            UndoableCommand command = history.pop();
            command.undo();
        }
    }
}
