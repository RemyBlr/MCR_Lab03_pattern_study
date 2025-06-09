package game.upgrades;

import game.Game;

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
        if(game.canUseGold(PRICE)) {
            game.setGold(-PRICE);
            game.refillInk();
        }
    }

    @Override
    public String getShortcut() {
        return "ctrl + 1";
    }
}
