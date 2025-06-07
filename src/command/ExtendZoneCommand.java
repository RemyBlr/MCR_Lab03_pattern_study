package command;

import game.Game;

public class ExtendZoneCommand implements Command {
    private final Game game;
    private final int price;

    public ExtendZoneCommand(Game game, int price) {
        this.game = game;
        this.price = price;
    }

    @Override
    public void execute() {
        if (!game.canUseGold(price)) return;
        game.setGold(-price);
        game.getCastle().increaseDefenseRadius(20);
    }
}
