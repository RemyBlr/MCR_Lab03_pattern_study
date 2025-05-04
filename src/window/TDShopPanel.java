package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Custom panel for the shop in Paint Tower Defense.
 * Contains gold display, purchase buttons and item grid.
 */
public class TDShopPanel extends JPanel {
    private TDGoldDisplay goldDisplay;
    private TDShopButton refillInkButton;
    private TDShopButton addInkButton;
    private TDShopButton addPvButton;
    private TDShopButton addZoneButton;
    private TDShopButton mysteryButton;
    private TDItemGrid itemGrid;

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
    public TDShopPanel(int initialGold) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 230, 250));

        // Gold display
        goldDisplay = new TDGoldDisplay(initialGold);

        // Add gold display with padding
        add(Box.createVerticalStrut(20));
        add(goldDisplay);
        add(Box.createVerticalStrut(40));

        // Container for buttons with margins
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setOpaque(false);
        buttonsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Create shop buttons
        refillInkButton = new TDShopButton("refill ink", REFILL_INK_PRICE, new Color(216, 181, 207));
        addInkButton = new TDShopButton("+ ink", ADD_INK_PRICE, new Color(211, 84, 50));
        addPvButton = new TDShopButton("+ pv", ADD_PV_PRICE, new Color(162, 208, 183));
        addZoneButton = new TDShopButton("+ zone", ADD_ZONE_PRICE, new Color(183, 187, 208));
        mysteryButton = new TDShopButton("???", MYSTERY_PRICE, new Color(234, 180, 167));

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
        itemGrid = new TDItemGrid();

        // Center the grid container
        JPanel gridWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridWrapper.setOpaque(false);
        gridWrapper.add(itemGrid);
        add(gridWrapper);

        // Add bottom padding
        add(Box.createVerticalStrut(20));
        add(Box.createVerticalGlue());

        // Set up button actions
        setupButtonActions();
    }

    /**
     * Set up action listeners for all buttons
     */
    private void setupButtonActions() {
        refillInkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryPurchase(REFILL_INK_PRICE, "ink refilled");
            }
        });

        addInkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryPurchase(ADD_INK_PRICE, "ink added");
            }
        });

        addPvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryPurchase(ADD_PV_PRICE, "pv added");
            }
        });

        addZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryPurchase(ADD_ZONE_PRICE, "zone added");
            }
        });

        mysteryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tryPurchase(MYSTERY_PRICE, "mystery item purchased");
            }
        });

        // Setup item grid actions
        for (int i = 0; i < 4; i++) {
            final int itemIndex = i;
            itemGrid.setItemActionListener(i, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Item " + (itemIndex + 1) + " selected");
                }
            });
        }
    }

    /**
     * Try to purchase an item
     *
     * @param price Item price
     * @param successMessage Message to print on successful purchase
     * @return true if purchase was successful
     */
    private boolean tryPurchase(int price, String successMessage) {
        boolean success = goldDisplay.spendGold(price);
        if (success) {
            System.out.println(successMessage);
        } else {
            System.out.println("Not enough gold!");
        }
        return success;
    }

    /**
     * Add gold to the player's balance
     *
     * @param amount Amount to add
     */
    public void addGold(int amount) {
        goldDisplay.addGold(amount);
    }

    /**
     * Get current gold amount
     *
     * @return Current gold amount
     */
    public int getGoldAmount() {
        return goldDisplay.getGoldAmount();
    }

    /**
     * Set a custom action listener for a button
     *
     * @param buttonType Type of button to set listener for
     * @param listener ActionListener to add
     */
    public void setButtonActionListener(ShopButtonType buttonType, ActionListener listener) {
        switch (buttonType) {
            case REFILL_INK:
                refillInkButton.addActionListener(listener);
                break;
            case ADD_INK:
                addInkButton.addActionListener(listener);
                break;
            case ADD_PV:
                addPvButton.addActionListener(listener);
                break;
            case ADD_ZONE:
                addZoneButton.addActionListener(listener);
                break;
            case MYSTERY:
                mysteryButton.addActionListener(listener);
                break;
        }
    }

    /**
     * Enum for easier reference to shop buttons
     */
    public enum ShopButtonType {
        REFILL_INK,
        ADD_INK,
        ADD_PV,
        ADD_ZONE,
        MYSTERY
    }
}