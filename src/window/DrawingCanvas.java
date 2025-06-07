package window;

import command.CommandManager;
import game.Game;
import game.GameObserver;
import game.Position;
import game.Wall;
import game.enemies.Enemy;
import game.enemies.EnemyManager;
import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends JPanel implements ToolChangeListener, GameObserver {
    // Castle
    private final Position castlePos = Game.getInstance().getCastle().getPosition();
    private final int castleWidth = Game.getInstance().getCastle().getWidth();
    private final int castleHeight = Game.getInstance().getCastle().getHeight();
    private final int castleRadius = Game.getInstance().getCastle().getRadius();
    private Image castleImage;
    private int defenseRadius;

    // Commands
    private CommandManager commandManager;

    // Walls
    private Path2D currentPath;
    private Color currentColor = Color.BLACK;
    private int strokeWidth = 3;
    private final List<Wall> walls = new ArrayList<>();

    // highlighted walls and selection rectangle
    private Rectangle selectionRectangle;
    private List<Wall> highlightedWalls = new ArrayList<>();

    // Tools
    private Tool currentTool;

    /**
     * Constructor for the DrawingCanvas.
     * Initializes the canvas with a background color and sets up the mouse listener.
     * @param commandManager the command manager to handle commands
     */
    public DrawingCanvas(CommandManager commandManager) {
        this.commandManager = commandManager;

        // Register the canvas as a listener for tool changes
        ToolManager.getInstance().addListener(this);

        currentTool = new PenTool(this, commandManager);

        ImageIcon castleIcon = new ImageIcon("./img/castle.png");
        castleImage = castleIcon.getImage().getScaledInstance(castleWidth, castleHeight, Image.SCALE_SMOOTH);

        defenseRadius = Game.getInstance().getCastle().getDefenseRadius();

        setBackground(Color.WHITE);

        setupMouseListener();
    }

    /**
     * Sets up the mouse listener for the canvas.
     * Events when mouse is pressed, dragged, or released are handled by the current tool.
     */
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

    /**
     * Returns the path length of the given path.
     * @param path the path to measure
     * @return the length of the path
     */
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

    /**
     * Paints the component.
     * @param g the <code>Graphics</code> object to paint
     */
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
        g2d.fillOval(centerX - defenseRadius, centerY - defenseRadius, defenseRadius * 2, defenseRadius * 2);
        // inner cercle
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - castleRadius, centerY - castleRadius,
                castleRadius * 2, castleRadius * 2);

        // Castle
        castlePos.setX(centerX - castleWidth / 2);
        castlePos.setY(centerY - castleHeight / 2);
        g2d.drawImage(castleImage, (int)castlePos.getX(), (int)castlePos.getY(), this);

        // Castle health bar
        int maxHp = Game.getInstance().getCastle().getMaxHp();
        int currentHp = Game.getInstance().getCastle().getHp();
        if(maxHp > 0) {
            int barWidth = castleWidth;
            int barHeight = 10;
            int barX = (int) castlePos.getX();
            int barY = (int) castlePos.getY() + castleHeight + 5;

            double hpRatio = (double) currentHp / maxHp;
            int filledWidth = (int) (barWidth * hpRatio);

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(barX, barY, barWidth, barHeight);
            g2d.setColor(Color.RED);
            g2d.fillRect(barX, barY, filledWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(barX, barY, barWidth, barHeight);

            String hpText = currentHp + "/" + maxHp;
            FontMetrics metrics = g2d.getFontMetrics();
            // font size
            g2d.setFont(new Font( g.getFont().getFontName(), Font.PLAIN, 10));
            int textWidth = metrics.stringWidth(hpText);
            int textHeight = metrics.getAscent();
            int textX = barX + (barWidth - textWidth) / 2;
            int textY = barY + (barHeight + textHeight) / 2 - 2;
            g2d.drawString(hpText, textX, textY);
        }

        // Draw walls that are already drawn
        for (Wall w : Game.getInstance().getWalls()) {
            g2d.setColor(w.getColor());
            g2d.setStroke(new BasicStroke(
                    w.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(w.getPath());
        }

        // Live drawing of the wall
        if (currentPath != null) {
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(currentPath);
        }

        drawEnemies(g2d);

        // Highlight walls that are selected
        g2d.setColor(new Color(0, 120, 215, 80));
        for (Wall w : highlightedWalls) {
            Rectangle b = w.getBounds();
            g2d.fillRect(b.x, b.y, b.width, b.height);
        }

        // Adds a pointed border when dragging the selection tool
        if (selectionRectangle != null) {
            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(
                    1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    1, new float[]{5}, 0
            ));
            g2d.draw(selectionRectangle);
        }

        g2d.dispose();
    }

    /**
     * Sets the rectangle used for selection.
     * @param rectangle the rectangle to set
     */
    public void setSelectionRectangle(Rectangle rectangle) {
        this.selectionRectangle = rectangle;
        repaint();
    }

    /**
     * Set the walls to be highlighted.
     * @param walls the selected walls
     */
    public void setHighlightedWalls(List<Wall> walls) {
        this.highlightedWalls = walls;
        repaint();
    }

    /**
     * Used by the PenTool to start drawing a new path.
     * Updates the current path and repaints the canvas on mouse press.
     * @param path the starting path
     */
    public void startPath(Path2D path) {
        currentPath = path;
        repaint();
    }

    /**
     * Repaints the canvas to show the current path.
     * Used by the PenTool to update the path on mouse drag.
     */
    public void updatePath() {
        repaint();
    }

    /**
     * Finishes the current path and adds it to the list of walls.
     * Used by the PenTool to finalize the wall on mouse release.
     */
    public void updateWalls() {
        currentPath = null;
        walls.clear();
        walls.addAll(Game.getInstance().getWalls());
        repaint();
    }

    /**
     * Checks if the given coordinates are inside the drawing zone.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if the coordinates are inside the drawing zone, false otherwise
     */
    public boolean isInsideDrawingZone(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        Position center = new Position(centerX, centerY);
        Position point = new Position(x, y);

        double distance = point.distanceTo(center);

        return distance <= defenseRadius && distance >= castleRadius;
    }

    /**
     * @return the current color used for drawing
     */
    public Color getCurrentColor() {
        return currentColor;
    }

    /**
     * @return the stroke width used for drawing
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Called by the ToolManager when the tool changes.
     * @param toolName the name of the tool to set
     */
    @Override
    public void toolChanged(ToolOption toolName) {
        if (currentTool instanceof GameObserver) {
            Game.getInstance().removeObserver((GameObserver) currentTool);
        }

        switch (toolName) {
            case ToolOption.SELECT -> {
                currentTool = new SelectTool(this, commandManager);
                Game.getInstance().addObserver((GameObserver) currentTool);
            }
            case ToolOption.BLACK_PEN -> {
                currentColor = Color.BLACK;
                currentTool =new PenTool(this, commandManager);
            }
            case ToolOption.BLUE_PEN -> {
                currentColor = Color.BLUE;
                currentTool =new PenTool(this, commandManager);
            }
            case ToolOption.GREEN_PEN -> {
                currentColor = Color.GREEN;
                currentTool =new PenTool(this, commandManager);
            }
            case ToolOption.RED_PEN -> {
                currentColor = Color.RED;
                currentTool =new PenTool(this, commandManager);
            }
            default -> throw new IllegalArgumentException("Unknown tool: " + toolName);
        }
    }

    private void drawEnemies(Graphics2D g2d) {
        EnemyManager enemyManager = Game.getInstance().getEnemyManager();
        List<Enemy> enemies = enemyManager.getActiveEnemies();
        if(enemies.isEmpty()) return;

        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }
    }

    public void update(){
        defenseRadius = Game.getInstance().getCastle().getDefenseRadius();
        repaint();
    }
}
