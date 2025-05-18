package tools;

import command.CommandManager;
import command.CompositeCommand;
import command.MoveWallCommand;
import game.Wall;
import window.DrawingCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * SelectTool class implements the Tool interface to handle mouse events for selecting and moving walls.
 * It allows the user to select walls by dragging a rectangle around them and move them together.
 */
public class SelectTool implements Tool {
    private final DrawingCanvas canvas;
    private final CommandManager commandManager;
    private Point start;
    private Point last;
    private Rectangle selectedRectangle;
    private boolean isDragging;

    public SelectTool(DrawingCanvas canvas, CommandManager mgr) {
        this.canvas = canvas;
        this.commandManager = mgr;
    }

    // TODO mon implémentation de fonctionne pas

    @Override
    public void mousePressed(MouseEvent e) {
        /*start = e.getPoint();
        selectedRectangle = new Rectangle(start);
        isDragging = false;*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*if (!isDragging) {
            selectedRectangle.setFrameFromDiagonal(start, e.getPoint());
        } else {
            int dx = e.getX() - last.x;
            int dy = e.getY() - last.y;
            for (Wall w : canvas.getGame().getWalls()) {
                if (selectedRectangle.intersects(w.getBounds())) {
                    w.move(dx, dy);
                }
            }
        }
        last = e.getPoint();
        canvas.repaint();*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /*if (!isDragging) {
            isDragging = true;
            last = e.getPoint();
        } else {
            double totalDx = e.getX() - start.x;
            double totalDy = e.getY() - start.y;
            CompositeCommand compositeCommand = new CompositeCommand();
            for (Wall wall : canvas.getGame().getWalls()) {
                if (selectedRectangle.intersects(wall.getBounds())) {
                    compositeCommand.addCommand(new MoveWallCommand(wall, totalDx, totalDy));
                }
            }

            commandManager.executeCommand(compositeCommand);
            selectedRectangle = null;
        }
        canvas.repaint();*/
    }
}
