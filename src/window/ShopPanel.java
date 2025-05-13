package window;

import window.components.GoldCoinIcon;
import window.components.ItemGrid;
import window.components.ShopButton;

import javax.swing.*;
import java.awt.*;

/**
 * Custom panel for the shop in Paint Tower Defense.
 * Contains gold display, purchase buttons and item grid.
 */
public class ShopPanel extends JPanel {
    private GoldCoinIcon playerGold;
    private ShopButton refillInkButton;
    private ShopButton addInkButton;
    private ShopButton addPvButton;
    private ShopButton addZoneButton;
    private ShopButton mysteryButton;
    private ItemGrid itemGrid;

    // Price constants
    private static final int REFILL_INK_PRICE = 100;
    private static final int ADD_INK_PRICE = 8;
    private static final int ADD_PV_PRICE = 20;
    private static final int ADD_ZONE_PRICE = 75;
    private static final int MYSTERY_PRICE = 999;

    /**
     * Creates a new shop panel with all components
     *
     * @param initialGold Initial gold amount
     */
    public ShopPanel(int initialGold) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 230, 250));

        // Add player sold
        playerGold = new GoldCoinIcon(initialGold);
        playerGold.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerGold.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(playerGold);

        // Container for buttons
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setOpaque(false);
        buttonsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Create shop buttons
        refillInkButton = new ShopButton("refill ink", REFILL_INK_PRICE, new Color(216, 181, 207));
        addInkButton = new ShopButton("+ ink", ADD_INK_PRICE, new Color(211, 84, 50));
        addPvButton = new ShopButton("+ pv", ADD_PV_PRICE, new Color(162, 208, 183));
        addZoneButton = new ShopButton("+ zone", ADD_ZONE_PRICE, new Color(183, 187, 208));
        mysteryButton = new ShopButton("???", MYSTERY_PRICE, new Color(234, 180, 167));

        // Add buttons to container with spacing
        buttonsContainer.add(refillInkButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addInkButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addPvButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addZoneButton);
        buttonsContainer.add(Box.createVerticalStrut(40));
        buttonsContainer.add(mysteryButton);

        add(buttonsContainer);
        add(Box.createVerticalStrut(50));

        // Item grid
        itemGrid = new ItemGrid();

        // Center the grid container
        JPanel gridWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridWrapper.setOpaque(false);
        gridWrapper.add(itemGrid);
        add(gridWrapper);

        // Add bottom padding
        add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());
    }
}