package command;

import game.Game;
import game.upgrades.Upgrade;

/**
 * UpgradeCommand is responsible for applying an upgrade to the game.
 * It checks if the player can afford the upgrade and applies it if possible.
 * This command can also be forced, bypassing the gold check. (for the mystery upgrade)
 */
public class UpgradeCommand implements Command {
    private final Upgrade upgrade;
    private final boolean force;

    /**
     * Constructor for UpgradeCommand.
     * @param upgrade the upgrade to be applied
     */
    public UpgradeCommand(Upgrade upgrade) {
        this(upgrade, false);
    }

    /**
     * Constructor for UpgradeCommand with forced upgrade.
     * @param upgrade the upgrade to be applied
     * @param force whether to force the upgrade regardless of gold availability
     */
    public UpgradeCommand(Upgrade upgrade, boolean force) {
        this.upgrade = upgrade;
        this.force = force;
    }

    @Override
    public void execute() {
        Game game = Game.getInstance();
        if (force || game.canUseGold(upgrade.getPrice())) {
            upgrade.apply(game);
            game.notifyObserversNow();
        }
    }
}
