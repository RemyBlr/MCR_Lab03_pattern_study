/*
 * File: AddInkUpgrade.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: AddInkUpgrade class represents an upgrade that increases the maximum ink capacity in the game.
 * Version: 1.0
 */
package game.upgrades;

import game.Game;

/**
 * Upgrade to increase the maximum ink capacity in the game.
 * This upgrade allows players to increase their ink supply by a fixed amount at each tier.
 */
public class AddInkUpgrade implements Upgrade {
    private static final int[] PRICES = {8,12,16,20};
    private static final int INK_AMOUNT = 50;
    private int tier = 0;

    @Override
    public String getLabel() {return "Encre +" + INK_AMOUNT + " (Tier " + (tier + 1) + ")";}

    @Override
    public int getPrice() {
        if (tier >= PRICES.length) return PRICES[PRICES.length - 1];
        return PRICES[tier];
    }

    @Override
    public void apply(Game game) {
        int price = getPrice();
        game.setGold(-price);
        game.increaseMaxInk(INK_AMOUNT);
        tier++;
    }

    @Override
    public String getShortcut() {return "ctrl + 2";}

    @Override
    public void reset() {tier = 0;}
}
