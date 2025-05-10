package command;

import window.DrawingCanvas;
import java.awt.*;
import java.awt.geom.Path2D;

public class DrawWallCommand implements Command {
    private final int wallWidth;
    private final Color color;
    private final DrawingCanvas canvas;
    private final Path2D path;

    public DrawWallCommand(DrawingCanvas canvas, Path2D path, Color color, int wallWidth) {
        this.canvas = canvas;
        this.path = path;
        this.color = color;
        this.wallWidth = wallWidth;
    }

    @Override
    public void execute() {
        System.out.println("Drawing wall...");
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(wallWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(path);
        g2d.dispose();
    }
}
