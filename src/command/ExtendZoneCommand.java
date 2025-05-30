package command;

import game.Game;
import window.DrawingCanvas;

public class ExtendZoneCommand implements Command {
    private final Game game;
    private final DrawingCanvas canvas; // TODO: remove when refactoring everything into game
    private final int price;

    // TODO enlever drawing canvas quand refacto tout dans game
    public ExtendZoneCommand(Game game, DrawingCanvas canvas, int price) {
        this.game = game;
        this.canvas = canvas; // TODO: remove when refactoring everything into game
        this.price = price;
    }

    @Override
    public void execute() {
        if (!game.canUseGold(price)) return;
        game.setGold(-price);
        canvas.increaseDefenseRadius(20); // TODO: remove when refactoring everything into game
    }
}
