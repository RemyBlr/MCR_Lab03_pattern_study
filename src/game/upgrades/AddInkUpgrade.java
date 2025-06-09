package game.upgrades;

import game.Game;

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
        if(game.canUseGold(price)) {
            game.setGold(-price);
            game.increaseMaxInk(INK_AMOUNT);
            tier++;
        }
    }

    @Override
    public String getShortcut() {
        return "ctrl + 2";
    }
}
