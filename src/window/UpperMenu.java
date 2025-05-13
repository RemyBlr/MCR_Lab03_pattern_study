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
}