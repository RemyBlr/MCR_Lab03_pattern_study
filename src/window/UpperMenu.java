package window;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant le menu supérieur de l'application Paint Tower Defense.
 * Contient les menus Option et Help.
 */
public class UpperMenu extends JMenuBar {

    private JMenu menuOption;
    private JMenu menuHelp;
    private JMenuItem resetItem;
    private JMenuItem quitItem;
    private JMenuItem aboutItem;

    /**
     * Constructeur de la classe UpperMenu.
     */
    public UpperMenu() {
        initializeComponents();
        addComponentsToMenu();
    }

    /**
     * Initialise les composants du menu.
     */
    private void initializeComponents() {
        menuOption = new JMenu("Option");
        menuHelp = new JMenu("Help");

        resetItem = new JMenuItem("Reset");
        quitItem = new JMenuItem("Quit");
        aboutItem = new JMenuItem("About us");
    }

    /**
     * Ajoute les composants au menu.
     */
    private void addComponentsToMenu() {
        menuOption.add(resetItem);
        menuOption.add(quitItem);

        menuHelp.add(aboutItem);

        this.add(menuOption);
        this.add(menuHelp);
    }

//    /**
//     * Ajoute les écouteurs d'événements aux éléments du menu.
//     */
//    private void addListeners() {
//        resetItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Logique pour réinitialiser le jeu
//                System.out.println("Jeu réinitialisé");
//            }
//        });
//
//        quitItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//        aboutItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null,
//                        "Paint Tower Defense\nVersion 1.0\n© 2025 Votre équipe",
//                        "À propos de nous",
//                        JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//    }

    public static class DrawingCanvas extends JPanel {
        private static final int CASTLE_WIDTH = 200;
        private static final int CASTLE_HEIGHT = 200;
        private static int DEFENSE_RADIUS = 200;
        private Image castleImage;

        /**
         * Constructeur de la classe Canvas.
         */
        public DrawingCanvas() {
            ImageIcon castleIcon = new ImageIcon("./img/castle.png");
            castleImage = castleIcon.getImage(); // Utilisation de l'attribut de classe
            castleImage = castleImage.getScaledInstance(CASTLE_WIDTH, CASTLE_HEIGHT, Image.SCALE_SMOOTH);

            this.setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Center coordinates for circle
            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2;

            // Red drawing zone
            Color transparentRed = new Color(255, 0, 0, 27);
            g2d.setColor(transparentRed);
            g2d.fillOval(centerX - DEFENSE_RADIUS, centerY - DEFENSE_RADIUS, DEFENSE_RADIUS * 2, DEFENSE_RADIUS * 2);

            // Castle
            int imgX = centerX - CASTLE_WIDTH / 2;
            int imgY = centerY - CASTLE_HEIGHT / 2;
            g2d.drawImage(castleImage, imgX, imgY, this);

            g2d.dispose();
        }
    }
}