package window;

import command.*;
import game.GameObserver;
import window.components.GoldCoinIcon;
import window.components.ShopButton;
import game.Game;
import game.upgrades.*;


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

    // Upgrades
    private final Upgrade refillInkUpgrade = new RefillInkUpgrade();
    private final Upgrade addInkUpgrade = new AddInkUpgrade();
    private final Upgrade addHpUpgrade = new AddHpUpgrade();
    private final Upgrade extendZoneUpgrade = new ExtendZoneUpgrade();
    private final Upgrade mysteryUpgrade = new MysteryUpgrade();

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
        refillInkButton = new ShopButton(refillInkUpgrade.getLabel() + " (" + refillInkUpgrade.getShortcut() + ")", refillInkUpgrade.getPrice());
        addInkButton = new ShopButton(addInkUpgrade.getLabel() + " (" + addInkUpgrade.getShortcut() + ")", addInkUpgrade.getPrice());
        addPvButton = new ShopButton(addHpUpgrade.getLabel() + " (" + addHpUpgrade.getShortcut() + ")", addHpUpgrade.getPrice());
        addZoneButton = new ShopButton(extendZoneUpgrade.getLabel() + "(" + extendZoneUpgrade.getShortcut() + ")", extendZoneUpgrade.getPrice());
        mysteryButton = new ShopButton(mysteryUpgrade.getLabel() + " (" + mysteryUpgrade.getShortcut() + ")", mysteryUpgrade.getPrice());

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
        refillInkButton.addActionListener(e ->
            commandManager.executeCommand(new UpgradeCommand(Game.getInstance(), refillInkUpgrade))
        );

        addInkButton.addActionListener(e ->
            commandManager.executeCommand(new UpgradeCommand(Game.getInstance(), addInkUpgrade))
        );

        addPvButton.addActionListener(e ->
            commandManager.executeCommand(new UpgradeCommand(Game.getInstance(), addHpUpgrade))
        );

        addZoneButton.addActionListener(e ->
            commandManager.executeCommand(new UpgradeCommand(Game.getInstance(), extendZoneUpgrade))
        );

        mysteryButton.addActionListener(e ->
            commandManager.executeCommand(new UpgradeCommand(Game.getInstance(), mysteryUpgrade))
        );
    }

    // TODO trouver moyen refresh ui sur shortcut
    public void update() {
        playerGold.update();
        refreshButtons();
    }

    private void refreshButtons() {
        refillInkButton.setText(refillInkUpgrade.getLabel() + " (" + refillInkUpgrade.getShortcut() + ")");
        refillInkButton.setPrice(refillInkUpgrade.getPrice());

        addInkButton.setText(addInkUpgrade.getLabel() + " (" + addInkUpgrade.getShortcut() + ")");
        addInkButton.setPrice(addInkUpgrade.getPrice());

        addPvButton.setText(addHpUpgrade.getLabel() + " (" + addHpUpgrade.getShortcut() + ")");
        addPvButton.setPrice(addHpUpgrade.getPrice());

        addZoneButton.setText(extendZoneUpgrade.getLabel() + " (" + extendZoneUpgrade.getShortcut() + ")");
        addZoneButton.setPrice(extendZoneUpgrade.getPrice());
    }

    public Upgrade getRefillInkUpgrade() {return refillInkUpgrade;}

    public Upgrade getAddInkUpgrade() {return addInkUpgrade;}

    public Upgrade getAddHpUpgrade() {return addHpUpgrade;}

    public Upgrade getExtendZoneUpgrade() {return extendZoneUpgrade;}

    public Upgrade getMysteryUpgrade() {return mysteryUpgrade;}

    public void resetUpgrades() {
        refillInkUpgrade.reset();
        addInkUpgrade.reset();
        addHpUpgrade.reset();
        extendZoneUpgrade.reset();
        mysteryUpgrade.reset();
    }
}