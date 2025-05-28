package window;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe représentant la barre d'état de l'application Paint Tower Defense.
 * Affiche le niveau d'encre, la vague actuelle et le temps écoulé.
 */
public class StatusBar extends JPanel {

    private JLabel inkLabel;
    private JLabel waveLabel;
    private JLabel timeLabel;

    /**
     * Constructeur de la classe StatusBar.
     */
    public StatusBar() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        initializeComponents();
        addComponentsToPanel();
    }

    /**
     * Initialise les composants de la barre d'état.
     */
    private void initializeComponents() {
        Game game = Game.getInstance();
        inkLabel = new JLabel("Encre: " + game.getInk());
        waveLabel = new JLabel("Vague: " + game.getWaveNumber());
        timeLabel = new JLabel("Temps: 00:00");
    }

    /**
     * Ajoute les composants au panneau.
     */
    private void addComponentsToPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(waveLabel);

        add(inkLabel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(timeLabel, BorderLayout.EAST);
    }

    // TODO : for now called by the game, but could have a refresh rate on its own no ?
    public void update(){
        Game game = Game.getInstance();

        // Ink
        inkLabel.setText("Encre: " + game.getInk());

        // Time
        long elapsedNanos = game.getTimeElapsed();
        long elapsedSeconds = elapsedNanos / 1_000_000_000L;
        int minutes = (int)(elapsedSeconds / 60);
        int seconds = (int)(elapsedSeconds % 60);

        // Format
        String time = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText("Temps: " + time);

        // Wave
        waveLabel.setText("Vague: " + game.getWaveNumber());
    }
}