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

    // TODO : we dont store this info in a panel
    private int inkLevel;
    private int waveNumber;
    private int seconds;
    private Timer timer;

    /**
     * Constructeur de la classe StatusBar.
     */
    public StatusBar() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        inkLevel = 100; // TODO : same here, we should use the game info
        waveNumber = 1;
        seconds = 0;

        initializeComponents();
        addComponentsToPanel();
//        startTimer();
    }

    /**
     * Initialise les composants de la barre d'état.
     */
    private void initializeComponents() {
        Game game = Game.getInstance();
        inkLabel = new JLabel("Encre: " + game.getInk());
        waveLabel = new JLabel("Vague: " + waveNumber);
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
        // Update the 3 jlabels using the game
        inkLabel.setText("Encre: " + game.getInk());

    }
}