package command;

import java.util.Stack;

/**
 * CommandManager is responsible for managing the execution of commands.
 * It maintains a history of executed commands, allowing for undo functionality.
 */
public class CommandManager {
    private final Stack<UndoableCommand> history = new Stack<>();

    /**
     * Executes a command without pushing it onto the history stack.
     *
     * @param command The command to execute.
     */
    public void executeCommand(Command command) {
        command.execute();
    }

    /**
     * Executes a command and pushes it onto the history stack.
     *
     * @param command The command to execute.
     */
    public void executeCommand(UndoableCommand command) {
        command.execute();
        history.push(command);
    }

    /**
     * Undoes the last executed command.
     * If there are no commands in the history, nothing happens.
     */
    public void undo() {
        if(!history.isEmpty()) {
            UndoableCommand command = history.pop();
            command.undo();
        }
    }

    /**
     * Records a command in the history stack without executing it.
     * @param command The command to record.
     */
    public void recordCommand(UndoableCommand command) {
        history.push(command);
    }
}
