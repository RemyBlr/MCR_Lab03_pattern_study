package window;

import command.CommandManager;
import command.ToolSelectionCommand;
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

    /**
     * Constructeur de la classe ToolBar.
     */
    public ToolBar(DrawingCanvas canvas, CommandManager commandManager) {
        setFloatable(false);
        initializeComponents();
        addComponentsToToolBar();

        penButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(canvas, "Pen"))
        );
        selectButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(canvas, "Select"))
        );
    }

    /**
     * Initialise les composants de la barre d'outils.
     */
    private void initializeComponents() {
        penButton = new ButtonIcon("./img/pen.png", "Pen");
        selectButton = new ButtonIcon("./img/select.png", "Select");
        blackButton = new ButtonIcon("./img/black.png", "Black");
        blueButton = new ButtonIcon("./img/blue.png", "Blue");
        greenButton = new ButtonIcon("./img/green.png", "Green");
        redButton = new ButtonIcon("./img/red.png", "Red");
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
}