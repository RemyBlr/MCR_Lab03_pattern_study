package command;

import window.DrawingCanvas;

/**
 * ToolSelectionCommand class represents a command to select a tool.
 * It implements the Command interface.
 */
public class ToolSelectionCommand implements Command {
    private final DrawingCanvas canvas;
    private final String toolName;

    /**
     * Constructor for ToolSelectionCommand.
     *
     * @param canvas   the drawing canvas
     * @param toolName the name of the tool to be selected
     */
    public ToolSelectionCommand(DrawingCanvas canvas, String toolName) {
        this.canvas = canvas;
        this.toolName = toolName;
    }

    @Override
    public void execute() {
        System.out.println("Selecting tool: " + toolName);
        canvas.setCurrentTool(toolName);
    }
}
