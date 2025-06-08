package window;

import command.RestartGameCommand;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    public GameOverPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0, 0, 0, 180));
        setOpaque(true);

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel waveLabel = new JLabel("You reached wave " + game.Game.getInstance().getWaveCount());
        waveLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        waveLabel.setForeground(Color.WHITE);
        waveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton retryButton = new JButton("Retry");
        retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        retryButton.addActionListener(e -> {
            new RestartGameCommand().execute();
        });

        add(Box.createVerticalGlue());
        add(gameOverLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(waveLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(retryButton);
        add(Box.createVerticalGlue());
    }
}
