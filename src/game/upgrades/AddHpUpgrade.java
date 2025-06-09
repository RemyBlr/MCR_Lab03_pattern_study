package game.upgrades;

import game.Game;

public class AddHpUpgrade implements Upgrade {
    private static final int[] PRICES = {50,75,100,150};
    private static final int HP_AMOUNT = 20;
    private int tier = 0;

    @Override
    public String getLabel() {return "PV +" + HP_AMOUNT + " (Tier " + (tier + 1) + ")";}

    @Override
    public int getPrice() {
        if (tier >= PRICES.length) return PRICES[PRICES.length - 1];
        return PRICES[tier];
    }

    @Override
    public void apply(Game game) {
        int price = getPrice();
        game.setGold(-price);
        game.getCastle().addHp(HP_AMOUNT);
        tier++;

    }

    @Override
    public String getShortcut() {return "ctrl + 3";}

    @Override
    public void reset() {tier = 0;}
}
