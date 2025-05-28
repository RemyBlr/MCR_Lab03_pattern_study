package command;

import tools.ToolManager;
import tools.ToolOption;

/**
 * ToolSelectionCommand class represents a command to select a tool.
 * It implements the Command interface.
 */
public class ToolSelectionCommand implements Command {
    private final ToolOption toolName;

    /**
     * Constructor for ToolSelectionCommand.
     *
     * @param toolName the name of the tool to be selected
     */
    public ToolSelectionCommand(ToolOption toolName) {
        this.toolName = toolName;
    }

    @Override
    public void execute() {
        System.out.println("Selecting tool: " + toolName);
        ToolManager.getInstance().setCurrentTool(toolName);
    }
}
