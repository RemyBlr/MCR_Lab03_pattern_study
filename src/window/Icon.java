package window;

import javax.swing.*;
import java.awt.*;

/**
 * Custom component for displaying icon within a JPanel
 */
public class Icon extends JButton {
    protected JLabel iconLabel;
    private static int ICON_SIZE = 40;
    protected static String filename;

    /**
     * Creates a new icon with the specified filename
     *
     * @param filename The path of the icon to display
     */
    public Icon(String filename) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);

        this.filename = filename;

        // Add coin image
        ImageIcon goldCoinImg = new ImageIcon(filename);
        java.awt.Image scaledImage = goldCoinImg.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        iconLabel = new JLabel(resizedIcon);
        iconLabel.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        add(iconLabel);
    }

    public Icon(String filename, int size) {
        this(filename);
        ICON_SIZE = size;
    }

    /**
     * Updates the icon size. Default is 40.
     */
    public void setIconSize(int size) {
        ICON_SIZE = size;
    }
}