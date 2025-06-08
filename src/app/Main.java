package app;

import window.TDWindow;

import javax.swing.*;

public class Main {
    public static TDWindow mainWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainWindow = new TDWindow();
        });
    }
}