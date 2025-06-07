package command;

import game.Game;
import game.enemies.SupremModeFactory;

public class SupremModeCommand implements Command {
    private final Game game;

    public SupremModeCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.setWaveCount(999);
        game.setMode(new SupremModeFactory());
    }
}
