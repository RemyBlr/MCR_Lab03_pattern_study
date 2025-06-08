package command;
import app.Main;

public class RestartGameCommand implements Command {

    public RestartGameCommand() {
    }

    @Override
    public void execute() {
        if (Main.mainWindow != null) {
            Main.mainWindow.restartGame();
        }
    }
}
