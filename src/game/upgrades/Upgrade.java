package game.upgrades;

import game.Game;

public interface Upgrade {
    String getLabel();
    int getPrice();
    void apply(Game game);
    String getShortcut();
    void reset();
}
