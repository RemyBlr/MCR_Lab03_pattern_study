package window;

import javax.swing.*;

public class ButtonIcon extends JButton {
    private static final int ICON_SIZE = 50;
    private Icon toolIcon;
    private String toolName;

    /**
     * Creates a new button with the specified icon and tool name.
     *
     * @param iconPath Path to the icon image file
     * @param toolName Name of the tool
     */
    public ButtonIcon(String iconPath, String toolName) {
        this.toolName = toolName;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        // Load and scale the icon
        toolIcon = new Icon(iconPath, ICON_SIZE);
        add(toolIcon);
        JLabel label = new JLabel(toolName);
        label.setAlignmentX(CENTER_ALIGNMENT);
    }
}
