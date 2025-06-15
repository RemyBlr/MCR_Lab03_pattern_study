/*
 * File: MysteryUpgrade.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: MysteryUpgrade class represents an upgrade that applies a mystery effect in the game. (Suprem Mode .)
 * Version: 1.0
 */
package game.upgrades;

import game.enemies.SupremeHardcoreModeFactory;

/**
 * Upgrade that applies a mystery effect in the game.
 * This upgrade resets the enemy manager, sets the wave count to 999,
 * and changes the game mode to Supreme Hardcore Mode.
 */
public class MysteryUpgrade implements Upgrade {
    private final static int PRICE = 999;

    @Override
    public String getLabel() {
        return "???";
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public void apply(game.Game game) {
        if(game.canUseGold(PRICE)) game.setGold(-PRICE);
        game.getEnemyManager().reset();
        game.setWaveCount(999);
        game.setMode(new SupremeHardcoreModeFactory());
    }

    @Override
    public String getShortcut() {return "???";}

    @Override
    public void reset() {}// No specific reset logic needed for this upgrade
}
