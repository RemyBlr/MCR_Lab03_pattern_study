package command;

import game.Game;
import game.enemies.SupremHardcoreModeFactory;

public class SupremModeCommand implements Command {
    private final Game game;

    public SupremModeCommand(Game game) {
        this.game = game;
    }

    // TODO: When CTRL + H don't need money
    @Override
    public void execute() {
        game.getEnemyManager().reset();
        game.setWaveCount(999);
        game.setMode(new SupremHardcoreModeFactory());
    }
}
