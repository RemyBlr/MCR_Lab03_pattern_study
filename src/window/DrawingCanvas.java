package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends JPanel {
    private static final int CASTLE_WIDTH = 100;
    private static final int CASTLE_HEIGHT = 100;
    private static int DEFENSE_RADIUS = 200;
    private Image castleImage;

    /**
     * Constructeur de la classe Canvas.
     */
    public DrawingCanvas() {
        ImageIcon castleIcon = new ImageIcon("./img/castle.png");
        Image castleImage = castleIcon.getImage();
        castleImage = castleImage.getScaledInstance(CASTLE_WIDTH, CASTLE_HEIGHT, Image.SCALE_SMOOTH);

        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Center coordinates for circle
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        // Red drawing zone
        int radius = 200;
        Color transparentRed = new Color(255, 0, 0, 27);
        g2d.setColor(transparentRed);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Castle
        int imgX = centerX - CASTLE_WIDTH / 2;
        int imgY = centerY - CASTLE_HEIGHT / 2;
        g2d.drawImage(castleImage, imgX, imgY, this);

        g2d.dispose();
    }
};
