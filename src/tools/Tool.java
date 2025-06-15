/*
 * File: Tool.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Tool interface to handle mouse events.
 * Version: 1.0
 */
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
