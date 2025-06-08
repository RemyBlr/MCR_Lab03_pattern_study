package command;

import game.Game;
import game.State;

public class TogglePauseCommand implements Command {

    @Override
    public void execute() {
        Game game = Game.getInstance();
        if (game.getState() == State.RUNNING) {
            game.pause();
        } else if (game.getState() == State.PAUSED) {
            game.resume();
        }
    }
}
