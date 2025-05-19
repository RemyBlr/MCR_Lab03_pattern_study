import game.Game;
import window.TDWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TDWindow::new);
    }
}