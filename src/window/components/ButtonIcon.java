/*
 * File: ButtonIcon.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: ButtonIcon class represents a custom button with an icon.
 * Version: 1.0
 */
package window.components;

import javax.swing.*;
import java.awt.*;

/*
 * Custom button with an icon class
 */
public class ButtonIcon extends JButton {
    private static final int ICON_SIZE = 50;

    /**
     * Creates a new button with the specified icon and tool name.
     *
     * @param iconPath Path to the icon image file
     * @param toolName Name of the tool
     */
    public ButtonIcon(String iconPath, String toolName, String shortcut) {

        setLayout(new BorderLayout());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        // ImageIcon chargée depuis le chemin
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));

        setText(toolName + " (" + shortcut + ")");
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
    }
}
