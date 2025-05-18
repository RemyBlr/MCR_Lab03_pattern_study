package tools;

import java.awt.event.MouseEvent;

/**
 * @brief Tool interface to handle mouse events.
 */
public interface Tool {
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseDragged(MouseEvent e);
}
