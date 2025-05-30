package command;

import game.Game;

public class AddInkCapacityCommand implements Command{
    private final Game game;
    private final int price;

    public AddInkCapacityCommand(Game game, int price) {
        this.game = game;
        this.price = price;
    }

    @Override
    public void execute() {
        if (!game.canUseGold(price)) return;
        game.setGold(-price);
        game.increaseMaxInk(50);
    }
}
