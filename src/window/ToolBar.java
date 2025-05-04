package window;

import window.components.ButtonIcon;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la barre d'outils de l'application Paint Tower Defense.
 * Contient les boutons pour dessiner, sélectionner et changer de couleur.
 */
public class ToolBar extends JToolBar {

    private JButton penButton;
    private JButton selectButton;
    private JButton blackButton;
    private JButton blueButton;
    private JButton greenButton;
    private JButton redButton;

    private Color currentColor;
    private String currentTool;

    /**
     * Constructeur de la classe ToolBar.
     */
    public ToolBar() {
        setFloatable(false);
        currentColor = Color.BLACK;
        currentTool = "pen";

        initializeComponents();
        addComponentsToToolBar();
    }

    /**
     * Initialise les composants de la barre d'outils.
     */
    private void initializeComponents() {
        penButton = new ButtonIcon("./img/pen.png", "Stylo");
        selectButton = new ButtonIcon("./img/select.png", "Sélection");
        blackButton = new ButtonIcon("./img/black.png", "Noir");
        blueButton = new ButtonIcon("./img/blue.png", "Bleu");
        greenButton = new ButtonIcon("./img/green.png", "Vert");
        redButton = new ButtonIcon("./img/red.png", "Rouge");
    }

    /**
     * Ajoute les composants à la barre d'outils.
     */
    private void addComponentsToToolBar() {
        add(penButton);
        add(Box.createHorizontalStrut(10));
        add(selectButton);
        add(Box.createHorizontalStrut(10));
        add(blackButton);
        add(Box.createHorizontalStrut(10));
        add(blueButton);
        add(Box.createHorizontalStrut(10));
        add(greenButton);
        add(Box.createHorizontalStrut(10));
        add(redButton);
    }

//    /**
//     * Ajoute les écouteurs d'événements aux boutons.
//     */
//    private void addListeners() {
//        penButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentTool = "pen";
//                System.out.println("Outil stylo sélectionné");
//            }
//        });
//
//        selectButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentTool = "select";
//                System.out.println("Outil sélection sélectionné");
//            }
//        });
//
//        blackButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentColor = Color.BLACK;
//                System.out.println("Couleur noire sélectionnée");
//            }
//        });
//
//        blueButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentColor = Color.BLUE;
//                System.out.println("Couleur bleue sélectionnée");
//            }
//        });
//
//        greenButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentColor = Color.GREEN;
//                System.out.println("Couleur verte sélectionnée");
//            }
//        });
//
//        redButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                currentColor = Color.RED;
//                System.out.println("Couleur rouge sélectionnée");
//            }
//        });
//    }

    /**
     * Obtient la couleur actuellement sélectionnée.
     *
     * @return Color La couleur actuelle
     */
    public Color getCurrentColor() {
        return currentColor;
    }

    /**
     * Obtient l'outil actuellement sélectionné.
     *
     * @return String L'outil actuel
     */
    public String getCurrentTool() {
        return currentTool;
    }
}