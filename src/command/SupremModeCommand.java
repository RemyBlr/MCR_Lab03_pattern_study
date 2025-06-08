package command;

import game.Game;
import game.enemies.SupremHardcoreModeFactory;

public class SupremModeCommand implements Command {
    private final Game game;

    public SupremModeCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        if(!game.canUseGold(999)) return;
        game.setWaveCount(999);
        game.setMode(new SupremHardcoreModeFactory());
    }
}
