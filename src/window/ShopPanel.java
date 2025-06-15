/*
 * File: ShopPanel.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: ShopPanel class represents the panel for the shop in Paint Tower Defense.
 * Version: 1.0
 */
package window;

import command.*;
import game.GameObserver;
import window.components.GoldCoinIcon;
import window.components.ShopButton;
import game.Game;
import game.upgrades.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom panel for the shop in Paint Tower Defense.
 * Contains gold display, purchase buttons and item grid.
 */
public class ShopPanel extends JPanel implements GameObserver {
    private final GoldCoinIcon playerGold;
    private final CommandManager commandManager;

    private final Map<Upgrade, Action> upgradeActions = new LinkedHashMap<>();
    private final Map<Upgrade, ShopButton> upgradeButtons = new LinkedHashMap<>();

    private final Upgrade refillInkUpgrade = new RefillInkUpgrade();
    private final Upgrade addInkUpgrade = new AddInkUpgrade();
    private final Upgrade addHpUpgrade = new AddHpUpgrade();
    private final Upgrade extendZoneUpgrade = new ExtendZoneUpgrade();
    private final Upgrade mysteryUpgrade = new MysteryUpgrade();

    /**
     * Creates a new shop panel with all components
     */
    public ShopPanel(CommandManager commandManager) {
        this.commandManager = commandManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 230, 250));

        // Add player gold
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

        Upgrade[] upgrades = {
            refillInkUpgrade,
            addInkUpgrade,
            addHpUpgrade,
            extendZoneUpgrade,
            mysteryUpgrade
        };

        for(Upgrade upgrade : upgrades) {
            Action action = new AbstractAction(upgrade.getLabel() + " (" + upgrade.getShortcut() + ")") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // execute the command with the game and upgrade
                    commandManager.executeCommand(new UpgradeCommand(upgrade));
                }
            };
            // add the action to the map
            upgradeActions.put(upgrade, action);

            // create buttons
            ShopButton btn = new ShopButton(action.getValue(Action.NAME).toString(), upgrade.getPrice());
            btn.addActionListener(action::actionPerformed);
            upgradeButtons.put(upgrade, btn);

            // listen for property changes
            action.addPropertyChangeListener((PropertyChangeEvent evt) -> {
                switch (evt.getPropertyName()) {
                    // update label and price
                    case Action.NAME -> {
                            btn.setText(action.getValue(Action.NAME).toString());
                            btn.setPrice(upgrade.getPrice());
                    }
                    // update enabled state
                    case "enabled" ->
                            btn.setEnabled(action.isEnabled());
                }
            });

            buttonsContainer.add(btn);
            buttonsContainer.add(Box.createVerticalStrut(10));
        }

        add(buttonsContainer);
    }

    // TODO trouver moyen refresh ui sur shortcut
    public void update() {
        // update gold
        playerGold.update();

        // update every action
        for (Map.Entry<Upgrade,Action> entry : upgradeActions.entrySet()) {
            Upgrade upgrade = entry.getKey();
            Action action = entry.getValue();
            // label + enabled
            action.putValue(Action.NAME, upgrade.getLabel() + " (" + upgrade.getShortcut() + ")");
            action.setEnabled(Game.getInstance().canUseGold(upgrade.getPrice()));
        }
    }

    public void resetUpgrades() {
        refillInkUpgrade.reset();
        addInkUpgrade.reset();
        addHpUpgrade.reset();
        extendZoneUpgrade.reset();
        mysteryUpgrade.reset();
    }

    public Action getRefillInkAction() {return upgradeActions.get(refillInkUpgrade);}
    public Action getAddInkAction() {return upgradeActions.get(addInkUpgrade);}
    public Action getAddHpAction() {return upgradeActions.get(addHpUpgrade);}
    public Action getExtendZoneAction() {return upgradeActions.get(extendZoneUpgrade);}

    private final Action mysteryForceAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // bypass gold check
            commandManager.executeCommand(new UpgradeCommand(mysteryUpgrade, true));
        }
    };
    public Action getMysteryAction() {return mysteryForceAction;}
}