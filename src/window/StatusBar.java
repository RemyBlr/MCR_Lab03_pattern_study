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

//    /**
//     * Démarre le timer pour le compteur de temps.
//     */
//    private void startTimer() {
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                seconds++;
//                updateTimeLabel();
//            }
//        });
//        timer.start();
//    }
//
//    /**
//     * Met à jour l'affichage du temps.
//     */
//    private void updateTimeLabel() {
//        int minutes = seconds / 60;
//        int secs = seconds % 60;
//        timeLabel.setText(String.format("Temps: %02d:%02d", minutes, secs));
//    }
//
//    /**
//     * Met à jour le niveau d'encre.
//     *
//     * @param level Le nouveau niveau d'encre
//     */
//    public void updateInkLevel(int level) {
//        this.inkLevel = level;
//        inkLabel.setText("Encre: " + inkLevel);
//    }
//
//    /**
//     * Met à jour le numéro de vague.
//     *
//     * @param wave Le nouveau numéro de vague
//     */
//    public void updateWaveNumber(int wave) {
//        this.waveNumber = wave;
//        waveLabel.setText("Vague: " + waveNumber);
//    }
//
//    /**
//     * Réinitialise le temps.
//     */
//    public void resetTime() {
//        seconds = 0;
//        updateTimeLabel();
//    }
//
//    /**
//     * Arrête le timer.
//     */
//    public void stopTimer() {
//        if (timer != null && timer.isRunning()) {
//            timer.stop();
//        }
//    }
//
//    /**
//     * Obtient le temps écoulé en secondes.
//     *
//     * @return int Le temps écoulé
//     */
//    public int getElapsedTime() {
//        return seconds;
//    }
//
//    /**
//     * Obtient le niveau d'encre actuel.
//     *
//     * @return int Le niveau d'encre
//     */
//    public int getInkLevel() {
//        return inkLevel;
//    }
//
//    /**
//     * Obtient le numéro de vague actuel.
//     *
//     * @return int Le numéro de vague
//     */
//    public int getWaveNumber() {
//        return waveNumber;
//    }
}