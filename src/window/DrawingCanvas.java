package window;

import command.CommandManager;
import command.CreateWallCommand;
import game.Game;
import game.Wall;
import tools.PenTool;
import tools.SelectTool;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends JPanel {
    private static final int CASTLE_WIDTH = 170;
    private static final int CASTLE_HEIGHT = 170;
    private static int DEFENSE_RADIUS = 200;
    private static final int CASTLE_RADIUS = CASTLE_WIDTH / 2;
    private Image castleImage;

    private final Game game;
    private CommandManager commandManager;
    private Path2D currentPath;
    private Color currentColor = Color.BLACK;
    private int strokeWidth = 3;
    private final List<Wall> walls = new ArrayList<>();
    private Tool currentTool;


    public DrawingCanvas(Game game, CommandManager commandManager) {
        this.game = game;
        this.commandManager = commandManager;

        PenTool pen = new PenTool(this, commandManager);

        currentTool = pen;

        ImageIcon castleIcon = new ImageIcon("./img/castle.png");
        castleImage = castleIcon.getImage().getScaledInstance(CASTLE_WIDTH, CASTLE_HEIGHT, Image.SCALE_SMOOTH);

        setBackground(Color.WHITE);

        setupMouseListener();
    }

    private void setupMouseListener() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentTool.mousePressed(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentTool.mouseDragged(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentTool.mouseReleased(e);
            }
        };

        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    public double getPathLength(Path2D path) {
        double length = 0;
        // TODO calculer la longueur du chemin JSP si correct c'est Copilot
        PathIterator it = path.getPathIterator(null, 1);
        double[] coords = new double[6];
        double[] prev = new double[2];
        if (!it.isDone()) {
            it.currentSegment(prev);
            it.next();
        }
        while (!it.isDone()) {
            int type = it.currentSegment(coords);
            if (type != PathIterator.SEG_CLOSE) {
                double dx = coords[0] - prev[0];
                double dy = coords[1] - prev[1];
                length += Math.hypot(dx, dy);
                prev[0] = coords[0];
                prev[1] = coords[1];
            }
            it.next();
        }
        return length;
    }

    public Game getGame() { return game; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // zones
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        // red zone
        Color transparentRed = new Color(255, 0, 0, 27);
        g2d.setColor(transparentRed);
        g2d.fillOval(centerX - DEFENSE_RADIUS, centerY - DEFENSE_RADIUS, DEFENSE_RADIUS * 2, DEFENSE_RADIUS * 2);
        // inner cercle
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - CASTLE_RADIUS, centerY - CASTLE_RADIUS,
                CASTLE_RADIUS * 2, CASTLE_RADIUS * 2);

        // Castle
        int imgX = centerX - CASTLE_WIDTH / 2;
        int imgY = centerY - CASTLE_HEIGHT / 2;
        g2d.drawImage(castleImage, imgX, imgY, this);

        // Draw walls that are already drawn
        for (Wall w : walls) {
            g2d.setColor(w.getColor());
            g2d.setStroke(new BasicStroke(
                    w.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(w.getPath());
        }

        if (currentPath != null) {
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(currentPath);
        }

        g2d.dispose();
    }

    public void startPath(Path2D path) {
        currentPath = path;
        repaint();
    }

    public void updatePath() {
        repaint();
    }

    public void finishPath() {
        currentPath = null;
        walls.clear();
        walls.addAll(game.getWalls());
        repaint();
    }

    public boolean isInsideDrawingZone(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int dx = x - centerX;
        int dy = y - centerY;

        double distance = dx*dx + dy*dy;

        return distance <= DEFENSE_RADIUS * DEFENSE_RADIUS && distance >= CASTLE_RADIUS  * CASTLE_RADIUS;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setCurrentTool(String toolName) {
        switch (toolName) {
            case "Pen" -> currentTool = new PenTool(this, commandManager);
            case "Select" -> currentTool = new SelectTool(this, commandManager);
            default -> throw new IllegalArgumentException("Unknown tool: " + toolName);
        }
    }
}
