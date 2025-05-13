package window;

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

        inkLevel = 100;
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
        inkLabel = new JLabel("Encre: " + inkLevel);
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
}