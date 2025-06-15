/*
 * File: Upgrade.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: Upgrade interface represents an upgrade in the game.
 * Version: 1.0
 */
package game.upgrades;

import game.Game;

/**
 * Represents an upgrade in the game.
 * Each upgrade has a label, a price, and a method to apply the upgrade to the game.
 * Upgrades can also have a shortcut for quick access and can be reset to their initial state.
 */
public interface Upgrade {
    /**
     * @return the label of the upgrade
     */
    String getLabel();

    /**
     * @return the price of the upgrade
     */
    int getPrice();

    /**
     * Applies the upgrade to the game, modifying its state accordingly.
     * This method should handle the logic for applying the upgrade, such as deducting gold and enhancing game features.
     *
     * @param game the game instance to apply the upgrade to
     */
    void apply(Game game);

    /**
     * @return the keyboard shortcut associated with this upgrade
     */
    String getShortcut();

    /**
     * Resets the upgrade to its initial state. (sets tier to 0)
     */
    void reset();
}
