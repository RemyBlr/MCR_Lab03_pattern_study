package window;

import command.CommandManager;
import command.DrawWallCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;

public class DrawingCanvas extends JPanel {
    private static final int CASTLE_WIDTH = 200;
    private static final int CASTLE_HEIGHT = 200;
    private static int DEFENSE_RADIUS = 200;
    private Image castleImage;

    private Path2D currentPath;
    private CommandManager commandManager;
    private Color currentColor = Color.BLACK;
    private int strokeWidth = 3;
    private boolean isDrawingZone = false;

    public DrawingCanvas(CommandManager commandManager) {
        this.commandManager = commandManager;

        ImageIcon castleIcon = new ImageIcon("./img/castle.png");
        castleImage = castleIcon.getImage();
        castleImage = castleImage.getScaledInstance(CASTLE_WIDTH, CASTLE_HEIGHT, Image.SCALE_SMOOTH);

        setBackground(Color.WHITE);

        setupMouseListener();
    }

    private void setupMouseListener() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentPath = new Path2D.Double();
                currentPath.moveTo(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawingZone && currentPath != null && isInsideDrawingZone(e.getX(), e.getY())) {
                    currentPath.lineTo(e.getX(), e.getY());
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentPath != null) {
                    currentPath.lineTo(e.getX(), e.getY());

                    DrawWallCommand cmd = new DrawWallCommand(DrawingCanvas.this, currentPath, currentColor, strokeWidth);
                    CommandManager.executeCommand(cmd);

                    currentPath = null;
                }
            }
        };

        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Red zone
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        Color transparentRed = new Color(255, 0, 0, 27);
        g2d.setColor(transparentRed);
        g2d.fillOval(centerX - DEFENSE_RADIUS, centerY - DEFENSE_RADIUS, DEFENSE_RADIUS * 2, DEFENSE_RADIUS * 2);

        // Castle
        int imgX = centerX - CASTLE_WIDTH / 2;
        int imgY = centerY - CASTLE_HEIGHT / 2;
        g2d.drawImage(castleImage, imgX, imgY, this);

        if (currentPath != null) {
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(currentPath);
        }

        g2d.dispose();
    }

    public void setDrawingEnabled(boolean status) {
        isDrawingZone = status;
    }

    private boolean isInsideDrawingZone(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int dx = x - centerX;
        int dy = y - centerY;

        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= DEFENSE_RADIUS;
    }
}
