/*
 * File: Icon.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Icon class represents an icon component.
 * Version: 1.0
 */
package window.components;

import javax.swing.*;
import java.awt.*;

public class Icon extends JLabel {
    protected int size = 40;
    protected String filename;

    public Icon(String filename) {
        this.filename = filename;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);

        // Add coin image
        ImageIcon goldCoinImg = new ImageIcon(filename);
        Image scaledImage = goldCoinImg.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
    }
}