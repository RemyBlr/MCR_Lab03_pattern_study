package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Custom component for displaying a grid of items in
 * the shop panel of Paint Tower Defense.
 */
public class TDItemGrid extends JPanel {
    private JButton[] itemButtons;
    private static final int ROWS = 2;
    private static final int COLS = 2;

    /**
     * Creates a new item grid with clickable buttons
     */
    public TDItemGrid() {
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setLayout(new GridLayout(ROWS, COLS, 5, 5));
        setPreferredSize(new Dimension(200, 150));
        setMaximumSize(new Dimension(200, 150));

        itemButtons = new JButton[ROWS * COLS];

        // Add item buttons
        for (int i = 0; i < itemButtons.length; i++) {
            itemButtons[i] = createItemButton("Item " + (i + 1));
            add(itemButtons[i]);
        }
    }

    /**
     * Create a single item button
     *
     * @param text Button text
     * @return The created button
     */
    private JButton createItemButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(200, 200, 200));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorderPainted(false);
        return button;
    }

    /**
     * Set an action listener for an item button
     *
     * @param index Item index (0-3)
     * @param listener ActionListener to add
     */
    public void setItemActionListener(int index, ActionListener listener) {
        if (index >= 0 && index < itemButtons.length) {
            itemButtons[index].addActionListener(listener);
        }
    }

    /**
     * Update an item's text
     *
     * @param index Item index (0-3)
     * @param text New text
     */
    public void setItemText(int index, String text) {
        if (index >= 0 && index < itemButtons.length) {
            itemButtons[index].setText(text);
        }
    }

    /**
     * Update an item's icon
     *
     * @param index Item index (0-3)
     * @param icon New icon
     */
    public void setItemIcon(int index, Icon icon) {
        if (index >= 0 && index < itemButtons.length) {
            itemButtons[index].setIcon(icon);
        }
    }

    /**
     * Enable or disable an item
     *
     * @param index Item index (0-3)
     * @param enabled Whether the item should be enabled
     */
    public void setItemEnabled(int index, boolean enabled) {
        if (index >= 0 && index < itemButtons.length) {
            itemButtons[index].setEnabled(enabled);
        }
    }
}