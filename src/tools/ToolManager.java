package tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that manages the current tool and notifies listeners of changes.
 */
public class ToolManager {
    private static final ToolManager instance = new ToolManager();

    private ToolOption currentTool = ToolOption.PEN;
    private final List<ToolChangeListener> listeners = new ArrayList<>();

    private ToolManager() {}

    /**
     * Returns the singleton instance of ToolManager.
     *
     * @return the ToolManager instance
     */
    public static ToolManager getInstance() {
        return instance;
    }

    /**
     * Gets the current tool.
     *
     * @return the current ToolOption
     */
    public ToolOption getCurrentTool() {
        return currentTool;
    }

    /**
     * Sets the current tool and notifies all listeners of the change.
     *
     * @param tool the ToolOption to set as current
     */
    public void setCurrentTool(ToolOption tool) {
        if (tool == currentTool) return;
        this.currentTool = tool;
        // notify all listeners about the tool change
        for (ToolChangeListener l : listeners) {
            l.toolChanged(tool);
        }
    }

    /**
     * Adds a listener to be notified when the tool changes.
     *
     * @param listener the ToolChangeListener to add
     */
    public void addListener(ToolChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener so it will no longer be notified of tool changes.
     *
     * @param listener the ToolChangeListener to remove
     */
    public void removeListener(ToolChangeListener listener) {
        listeners.remove(listener);
    }
}
