package command;

import game.Game;
import game.Wall;
import window.DrawingCanvas;
import java.awt.*;
import java.awt.geom.Path2D;

public class DrawWallCommand implements UndoableCommand {
    private final int wallWidth;
    private final Color color;
    private final DrawingCanvas canvas;
    private final Path2D path;
    private Wall wall;

    public DrawWallCommand(DrawingCanvas canvas,
                           Path2D path,
                           Color color,
                           int wallWidth,
                           int wallCost) {
        this.canvas = canvas;
        this.path = path;
        this.color = color;
        this.wallWidth = wallWidth;
        this.wall = new Wall(path, color, wallWidth, wallCost);
    }

    @Override
    public void execute() {
        System.out.println("Drawing wall...");

        /*Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(wallWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(path);
        g2d.dispose();*/
        Game.getInstance().addWall(wall);
        canvas.repaint();
    }

    @Override
    public void undo() {
        System.out.println("Undoing wall drawing...");

        Game.getInstance().removeWall(wall);
        canvas.repaint();
    }
}
