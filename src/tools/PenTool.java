package tools;

import command.CommandManager;
import command.CreateWallCommand;
import game.Game;
import game.Wall;
import window.DrawingCanvas;

import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/**
 * PenTool class implements the Tool interface to handle mouse events for drawing walls.
 * It allows the user to draw walls on the canvas by clicking and dragging the mouse.
 */
public class PenTool implements Tool {
    private final DrawingCanvas canvas;
    private final CommandManager commandManager;
    private Path2D currentPath;
    private final List<Wall> walls = new ArrayList<>();

    /**
     * Constructor for PenTool.
     *
     * @param canvas the drawing canvas
     * @param commandManager the command manager to handle commands
     */
    public PenTool(DrawingCanvas canvas, CommandManager commandManager) {
        this.canvas = canvas;
        this.commandManager = commandManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!canvas.isInsideDrawingZone(e.getX(), e.getY())) return;
        currentPath = new Path2D.Double();
        currentPath.moveTo(e.getX(), e.getY());
        canvas.startPath(currentPath);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentPath == null && canvas.isInsideDrawingZone(e.getX(), e.getY())) return;
        currentPath.lineTo(e.getX(), e.getY());
        canvas.updatePath();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Game game = Game.getInstance();
        if (currentPath == null) return;
        currentPath.lineTo(e.getX(), e.getY());
        int cost = (int)Math.ceil(canvas.getPathLength(currentPath));
        if (game.canUseInk(cost)) {
            CreateWallCommand cmd = new CreateWallCommand(
                    game, canvas,
                    currentPath, canvas.getCurrentColor(),
                    canvas.getStrokeWidth(), cost
            );
            commandManager.executeCommand(cmd);
        }
        canvas.finishPath();
        currentPath = null;
    }
}
