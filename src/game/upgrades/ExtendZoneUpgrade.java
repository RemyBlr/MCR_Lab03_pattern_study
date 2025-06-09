package game.upgrades;

import game.Game;

/**
 * Upgrade to extend the defense zone of the castle.
 * This upgrade increases the radius of the defense zone by a fixed amount at each tier.
 */
public class ExtendZoneUpgrade implements Upgrade {
    private static final int[] PRICES = {75,100,125,150};
    private static final int RANGE_AMOUNT = 20;
    private int tier = 0;

    @Override
    public String getLabel() {return "Zone +" + RANGE_AMOUNT + " (Tier " + (tier + 1) + ")";}

    @Override
    public int getPrice() {
        if (tier >= PRICES.length) return PRICES[PRICES.length - 1];
        return PRICES[tier];
    }

    @Override
    public void apply(Game game) {
        int price = getPrice();
        game.setGold(-price);
        game.getCastle().increaseDefenseRadius(RANGE_AMOUNT);
        tier++;
    }

    @Override
    public String getShortcut() {return "ctrl + 4";}

    @Override
    public void reset() {tier = 0;}
}
