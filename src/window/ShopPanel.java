package window;

import command.*;
import game.GameObserver;
import window.components.GoldCoinIcon;
import window.components.ShopButton;
import game.Game;


import javax.swing.*;
import java.awt.*;

/**
 * Custom panel for the shop in Paint Tower Defense.
 * Contains gold display, purchase buttons and item grid.
 */
public class ShopPanel extends JPanel implements GameObserver {
    private final Game game;
    private final GoldCoinIcon playerGold;
    private final CommandManager commandManager;


    private ShopButton refillInkButton;
    private ShopButton addInkButton;
    private ShopButton addPvButton;
    private ShopButton addZoneButton;
    private ShopButton mysteryButton;

    // Price constants
    public static final int REFILL_INK_PRICE = 100;
    public static final int ADD_INK_PRICE = 8;
    public static final int ADD_PV_PRICE = 20;
    public static final int ADD_ZONE_PRICE = 75;
    public static final int MYSTERY_PRICE = 999;

    /**
     * Creates a new shop panel with all components
     */
    public ShopPanel(Game game, CommandManager commandManager) {
        this.game = game;
        this.commandManager = commandManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 230, 250));

        // Add player sold
        playerGold = new GoldCoinIcon();
        playerGold.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerGold.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        add(playerGold);

        // Container for buttons
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setOpaque(false);
        buttonsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Create shop buttons
        refillInkButton = new ShopButton("Remplir encre (ctrl + 1)", REFILL_INK_PRICE);
        addInkButton = new ShopButton("Encre +50 (ctrl + 2)", ADD_INK_PRICE);
        addPvButton = new ShopButton("PV +100 (ctrl + 3)", ADD_PV_PRICE);
        addZoneButton = new ShopButton("Zone +20 (ctrl + 4)", ADD_ZONE_PRICE);
        mysteryButton = new ShopButton("??? (ctrl + 5)", MYSTERY_PRICE);

        // Add buttons to container with spacing
        buttonsContainer.add(refillInkButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addInkButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addPvButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(addZoneButton);
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(mysteryButton);

        add(buttonsContainer);

        // Listeners
        refillInkButton.addActionListener(e -> {
            commandManager.executeCommand(new RefillInkCommand(Game.getInstance(), REFILL_INK_PRICE));
        });

        addInkButton.addActionListener(e -> {
            commandManager.executeCommand(new AddInkCapacityCommand(Game.getInstance(), ADD_INK_PRICE));
        });

        addPvButton.addActionListener(e -> {
            commandManager.executeCommand(new AddHpCommand(Game.getInstance(), ADD_PV_PRICE));
        });

        addZoneButton.addActionListener(e -> {
            commandManager.executeCommand(new ExtendZoneCommand(Game.getInstance(), ADD_ZONE_PRICE));
        });

    }

    // TODO trouver moyen refresh ui sur shortcut
    public void update() {
        playerGold.update();
    }
}