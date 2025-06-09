package command;

import game.Game;
import game.upgrades.Upgrade;

public class UpgradeCommand implements Command {
    private final Game game;
    private final Upgrade upgrade;

    public UpgradeCommand(Game game, Upgrade upgrade) {
        this.game = game;
        this.upgrade = upgrade;
    }

    @Override
    public void execute() {
        upgrade.apply(game);
    }
}
