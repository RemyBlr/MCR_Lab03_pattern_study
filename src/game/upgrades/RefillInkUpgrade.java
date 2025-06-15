/*
 * File: RefillInkUpgrade.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: RefillInkUpgrade class represents an upgrade that refills the ink in the game.
 * Version: 1.0
 */
package game.upgrades;

import game.Game;

/**
 * Upgrade to refill the ink in the game.
 * This upgrade allows players to refill their ink supply at a cost.
 */
public class RefillInkUpgrade implements Upgrade {
    private static final int PRICE = 100;

    @Override
    public String getLabel() {return "Remplir encre";}

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void apply(Game game) {
        game.setGold(-PRICE);
        game.refillInk();
    }

    @Override
    public String getShortcut() {return "ctrl + 1";}

    @Override
    public void reset() {}// No specific reset logic needed for this upgrade
}
