package command;

import java.util.Stack;

/**
 * CommandManager is responsible for managing the execution of commands.
 * It maintains a history of executed commands, allowing for undo functionality.
 */
public class CommandManager {
    private static final Stack<Command> history = new Stack<>();

    /**
     * Executes a command and pushes it onto the history stack.
     *
     * @param command The command to execute.
     */
    public static void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }
}
