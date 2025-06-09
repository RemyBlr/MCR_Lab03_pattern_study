package command;

import game.Game;
import game.upgrades.Upgrade;

/**
 * UpgradeCommand is responsible for applying an upgrade to the game.
 * It checks if the player can afford the upgrade and applies it if possible.
 * This command can also be forced, bypassing the gold check. (for the mystery upgrade)
 */
public class UpgradeCommand implements Command {
    private final Game game;
    private final Upgrade upgrade;
    private final boolean force;

    /**
     * Constructor for UpgradeCommand.
     * @param game the game instance where the upgrade will be applied
     * @param upgrade the upgrade to be applied
     */
    public UpgradeCommand(Game game, Upgrade upgrade) {
        this(game, upgrade, false);
    }

    /**
     * Constructor for UpgradeCommand with forced upgrade.
     * @param game the game instance where the upgrade will be applied
     * @param upgrade the upgrade to be applied
     * @param force whether to force the upgrade regardless of gold availability
     */
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
