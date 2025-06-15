/*
 * File: ShopButton.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: ShopButton class represents a custom button component for the shop panel in Paint Tower Defense.
 * Version: 1.0
 */
package window.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Custom button component for the shop panel in Paint Tower Defense.
 * Includes button text and price display with gold coin icon.
 */
public class ShopButton extends JPanel {
    private JButton button;
    private JLabel priceLabel;
    private JLabel coinLabel;
    private static final int COIN_SIZE = 20;

    /**
     * Creates a new shop button with text, price and background color
     *
     * @param text Button text
     * @param price Item price
     */
    public ShopButton(String text, int price) {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        setOpaque(false);

        // Create button
        button = new JButton(text);
        button.setFocusPainted(false);
        add(button, BorderLayout.WEST);

        // Create price label with coin
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pricePanel.setOpaque(false);

        priceLabel = new JLabel(String.valueOf(price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));

        ImageIcon coinIcon = new ImageIcon("./img/gold-coin.png");
        Image scaledCoin = coinIcon.getImage().getScaledInstance(COIN_SIZE, COIN_SIZE, Image.SCALE_SMOOTH);
        coinLabel = new JLabel(new ImageIcon(scaledCoin));

        pricePanel.add(priceLabel);
        pricePanel.add(coinLabel);

        add(pricePanel, BorderLayout.EAST);
    }

    /**
     * Add an action listener to the button
     *
     * @param listener ActionListener to add
     */
    public void addActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    /**
     * Set the button text
     *
     * @param text New button text
     */
    public void setText(String text) {
        button.setText(text);
    }

    /**
     * Set the price value
     *
     * @param price New price value
     */
    public void setPrice(int price) {
        priceLabel.setText(String.valueOf(price));
    }

    /**
     * Enable or disable the button
     *
     * @param enabled Whether the button should be enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        button.setEnabled(enabled);
    }
}