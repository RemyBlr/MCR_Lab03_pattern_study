package game.upgrades;

import game.enemies.SupremHardcoreModeFactory;

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
        game.setMode(new SupremHardcoreModeFactory());
    }

    @Override
    public String getShortcut() {return "???";}

    @Override
    public void reset() {}// No specific reset logic needed for this upgrade
}
