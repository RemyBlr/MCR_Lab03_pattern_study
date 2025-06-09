package app;

import window.TDWindow;

import javax.swing.*;
import game.Game;

public class Main {
    public static TDWindow mainWindow;

    public static void main(String[] args) {
        int ink = 200;
        int hp = 20;
        int gold = 0;

        if (args.length >= 3) {
            try {
                ink = Integer.parseInt(args[0]);
                hp = Integer.parseInt(args[1]);
                gold = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid parameters, using default values.");
            }
        }

        Game.init(ink, hp, gold);

        SwingUtilities.invokeLater(() -> {
            mainWindow = new TDWindow();
        });
    }
}