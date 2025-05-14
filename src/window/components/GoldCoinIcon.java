package window.components;

import javax.swing.*;
import java.awt.*;

/**
 * Custom component for displaying gold amount with coin icon
 * in Paint Tower Defense.
 */
public class GoldCoinIcon extends Icon {
    private JLabel goldLabel;
    private int goldAmount;

    /**
     * Creates a new gold display with initial amount
     *
     * @param initialAmount Initial gold amount
     */
    public GoldCoinIcon(int initialAmount) {
        super("./img/gold-coin.png");

        this.goldAmount = initialAmount;

        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Gold amount label
        goldLabel = new JLabel("Gold: " + goldAmount);
        goldLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the panel
        add(new JLabel(getIcon())); // Add the coin icon
        add(goldLabel); // Add the gold amount text
    }

    /**
     * Updates the gold amount displayed
     *
     * @param amount New gold amount
     */
    public void updateGoldAmount(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }

        this.goldAmount = amount;
        goldLabel.setText("Gold : " + goldAmount);
    }

    /**
     * Gets the current gold amount
     *
     * @return Current gold amount
     */
    public int getGoldAmount() {
        return goldAmount;
    }
}