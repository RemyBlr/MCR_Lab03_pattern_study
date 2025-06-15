/*
 * File: Command.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Command interface represents a command in the command pattern.
 * Version: 1.0
 */
package command;

/**
 * Interface representing a command in the command pattern.
 * This interface defines a method to execute a command.
 */
public interface Command {
    /**
     * Executes the command.
     */
    void execute();
}
