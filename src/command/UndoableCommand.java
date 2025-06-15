/*
 * File: UndoableCommand.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: UndoableCommand interface represents a command that can be undone.
 * Version: 1.0
 */
package command;

/**
 * Interface representing an undoable command in the command pattern.
 * This interface defines the behavior for commands that can be both
 * executed and undone.
 *
 * Classes implementing this interface should provide their own
 * implementation of the `undo` method, ensuring the ability to revert
 * the effects of the `execute` method.
 */
public interface UndoableCommand extends Command {
    /**
     * Undo the command
     */
    void undo();
}
