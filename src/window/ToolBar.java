package window;

import command.CommandManager;
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
    public ToolBar(DrawingCanvas canvas) {
        setFloatable(false);
        currentColor = Color.BLACK;
        currentTool = "pen";

        initializeComponents();
        addComponentsToToolBar();

        penButton.addActionListener(e -> {
            canvas.setDrawingEnabled(true);
        });
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