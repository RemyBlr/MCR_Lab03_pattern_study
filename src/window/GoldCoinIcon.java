package window;

import javax.swing.*;
import java.awt.*;

/**
 * Custom component for displaying gold amount with coin icon
 * in Paint Tower Defense.
 */
public class GoldCoinIcon extends Icon {
    private JLabel goldLabel;
    private int goldAmount;
    private static final int COIN_SIZE = 40;

    /**
     * Creates a new gold display with initial amount
     *
     * @param initialAmount Initial gold amount
     */
    public GoldCoinIcon(int initialAmount) {
        super("./img/gold-coin.png");

        this.goldAmount = initialAmount;

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);

        goldLabel = new JLabel("Gold : " + goldAmount);
        goldLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(goldLabel);

        // Add coin image
        ImageIcon goldCoinImg = new ImageIcon(filename);
        java.awt.Image scaledImage = goldCoinImg.getImage().getScaledInstance(COIN_SIZE, COIN_SIZE, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        iconLabel = new JLabel(resizedIcon);
        iconLabel.setPreferredSize(new Dimension(COIN_SIZE, COIN_SIZE));
        add(iconLabel);
    }

    /**
     * Updates the gold amount displayed
     *
     * @param amount New gold amount
     */
    public void updateGoldAmount(int amount) {
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

    /**
     * Add gold to the current amount
     *
     * @param amount Amount to add
     */
    public void addGold(int amount) {
        updateGoldAmount(goldAmount + amount);
    }

    /**
     * Spend gold if there's enough
     *
     * @param amount Amount to spend
     * @return true if transaction successful, false if not enough gold
     */
    public boolean spendGold(int amount) {
        if (goldAmount >= amount) {
            updateGoldAmount(goldAmount - amount);
            return true;
        }
        return false;
    }
}