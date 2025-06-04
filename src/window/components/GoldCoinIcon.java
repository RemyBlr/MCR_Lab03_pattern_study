package window.components;

import game.Game;
import game.GameObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Custom component for displaying gold amount with coin icon
 * in Paint Tower Defense.
 */
public class GoldCoinIcon extends Icon implements GameObserver {
    private JLabel goldLabel;

    /**
     * Creates a new gold display with initial amount
     */
    public GoldCoinIcon() {
        super("./img/gold-coin.png");

        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Gold amount label
        goldLabel = new JLabel("Gold: " + Game.getInstance().getGold());
        goldLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the panel
        add(new JLabel(getIcon())); // Add the coin icon
        add(goldLabel); // Add the gold amount text
    }

    /**
     * Updates the gold amount displayed
     */
    public void update() {
        goldLabel.setText("Gold : " + Game.getInstance().getGold());
    }
}