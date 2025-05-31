package command;

import game.Game;

public class AddHpCommand implements Command{
    private final Game game;
    private final int price;

    public AddHpCommand(Game game, int price) {
        this.game = game;
        this.price = price;
    }

    @Override
    public void execute() {
        System.out.println("Adding 100 HP for " + price + " gold.");
        if (!game.canUseGold(price)) return;
        game.setGold(-price);
        game.addBaseHp(100);
    }
}
