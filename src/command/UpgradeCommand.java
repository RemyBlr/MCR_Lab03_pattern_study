package command;

import game.Game;
import game.upgrades.Upgrade;

public class UpgradeCommand implements Command {
    private final Game game;
    private final Upgrade upgrade;
    private final boolean force;

    public UpgradeCommand(Game game, Upgrade upgrade) {
        this(game, upgrade, false);
    }

    // Constructor for forced upgrades, e.g., MysteryUpgrade
    public UpgradeCommand(Game game, Upgrade upgrade, boolean force) {
        this.game = game;
        this.upgrade = upgrade;
        this.force = force;
    }

    @Override
    public void execute() {
        if (force || game.canUseGold(upgrade.getPrice())) {
            upgrade.apply(game);
            game.notifyObserversNow();
        }
    }
}
