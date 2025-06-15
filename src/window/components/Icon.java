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