package tools;

import command.CommandManager;
import command.CompositeCommand;
import command.MoveWallCommand;
import game.Game;
import game.GameObserver;
import game.Wall;
import window.DrawingCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * SelectTool class implements the Tool interface to handle mouse events for selecting and moving walls.
 * It allows the user to select walls by dragging a rectangle around them and move them together.
 */
public class SelectTool implements Tool, GameObserver {
    private final DrawingCanvas canvas;
    private final CommandManager commandManager;

    // Variables for selection rectangle
    private Point start;
    private Point last;
    private Rectangle selectionRectangle;
    private boolean isDragging = false;
    private boolean hasSelection = false;

    // selected walls
    private final List<Wall> selectedWalls = new ArrayList<>();

    /**
     * Constructor for SelectTool.
     *
     * @param canvas the drawing canvas
     * @param commandManager the command manager to handle commands
     */
    public SelectTool(DrawingCanvas canvas, CommandManager commandManager) {
        this.canvas = canvas;
        this.commandManager = commandManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
        last = start;

        // move wall if we are inside selected walls
        boolean clickInsideSelected = hasSelection && selectedWalls.stream()
                .anyMatch(w -> w.getBounds().contains(start));


        if (clickInsideSelected) {
            isDragging = true;
            // TODO ne change pas le curseur
            canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            // ensure no residual selection rectangle is kept
            selectionRectangle = null;
            canvas.setSelectionRectangle(null);
        } else {
            // new selection rectangle
            isDragging = false;
            hasSelection = false;
            selectedWalls.clear();
            selectionRectangle = new Rectangle(start);
            canvas.setSelectionRectangle(selectionRectangle);
            canvas.setHighlightedWalls(selectedWalls);
            // TODO ne change pas le curseur
            canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();

        if (isDragging) {
            // if current selected wall is destroyed
            // cancel drag until mouse is released
            selectedWalls.removeIf(w -> !Game.getInstance().getWalls().contains(w));
            if (selectedWalls.isEmpty()) {
                isDragging = false;
                hasSelection = false;
                canvas.setHighlightedWalls(selectedWalls);
                canvas.setSelectionRectangle(null);
                canvas.setCursor(Cursor.getDefaultCursor());
                return;
            }

            // moves selected walls "Live"
            int dx = point.x - last.x;
            int dy = point.y - last.y;

            // move only if all corners of the wall are inside the canvas
            List<Wall> movable = new ArrayList<>();
            for (Wall wall : selectedWalls) {
                Rectangle bounds = wall.getBounds();

                // Check all four corners of the wall
                Point[] corners = {
                        new Point(bounds.x + dx, bounds.y + dy),
                        new Point(bounds.x + dx + bounds.width, bounds.y + dy),
                        new Point(bounds.x + dx,bounds.y + dy + bounds.height),
                        new Point(bounds.x + dx + bounds.width, bounds.y + dy + bounds.height)
                };
                boolean allInside = true;

                for (Point c : corners) {
                    if (!canvas.isInsideDrawingZone(c.x, c.y)) {
                        allInside = false;
                        break;
                    }
                }
                // add wall only if all corners are inside the canvas
                if (allInside) {
                    movable.add(wall);
                }
            }

            // move all selected walls that are movable
            for (Wall wall : movable) {
                wall.move(dx, dy);
            }

            last = point;
            canvas.repaint();
        } else {
            // updates the selection rectangle
            if (selectionRectangle != null) {
                // setFrameFromDiagonal sets the diagonal of the framing rectangle of this Shape based on the two
                // specified coordinates.
                selectionRectangle.setFrameFromDiagonal(start, point);
                canvas.setSelectionRectangle(selectionRectangle);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point releasePoint = e.getPoint();

        if (isDragging) {
            // ensure dragged wall still exists
            selectedWalls.removeIf(w -> !Game.getInstance().getWalls().contains(w));
            if (!selectedWalls.isEmpty()) {
                // move selected walls to the new position
                int totalDx = releasePoint.x - start.x;
                int totalDy = releasePoint.y - start.y;
                // composite command to move all selected walls
                CompositeCommand comp = new CompositeCommand();
                for (Wall wall : selectedWalls)
                    comp.addCommand(new MoveWallCommand(wall, totalDx, totalDy));
                // push command to history without executing it
                commandManager.recordCommand(comp);  // undoable
            }
        } else {
            if (selectionRectangle != null) {
                // finalize the selection rectangle
                selectedWalls.clear();
                for (Wall wall : Game.getInstance().getWalls()) {
                    if (selectionRectangle.intersects(wall.getBounds())) {
                        selectedWalls.add(wall);
                    }
                }
                // update the selection state
                hasSelection = !selectedWalls.isEmpty();
                canvas.setHighlightedWalls(selectedWalls);
            }
        }

        isDragging = false;
        canvas.setSelectionRectangle(null);
        // TODO ne change pas le curseur
        canvas.setCursor(Cursor.getDefaultCursor());
        canvas.repaint();
    }

    @Override
    public void update() {
        List<Wall> currentWalls = Game.getInstance().getWalls();
        if (selectedWalls.removeIf(w -> !currentWalls.contains(w))) {
            hasSelection = !selectedWalls.isEmpty();
            if (!hasSelection) {
                isDragging = false;
                canvas.setSelectionRectangle(null);
            }
            canvas.setHighlightedWalls(selectedWalls);
        }
    }
}
