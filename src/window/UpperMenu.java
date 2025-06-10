package window;

import command.RestartGameCommand;
import command.TogglePauseCommand;

import javax.swing.*;

/**
 * Classe représentant le menu supérieur de l'application Paint Tower Defense.
 * Contient les menus Option et Help.
 */
public class UpperMenu extends JMenuBar {

    private JMenu menuOption;
    private JMenu menuHelp;
    private JMenuItem pauseItem;
    private JMenuItem resetItem;
    private JMenuItem quitItem;
    private JMenuItem howToPlayItem;

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

        pauseItem = new JMenuItem("Pause / Resume (p)");
        pauseItem.addActionListener(e ->{
            new TogglePauseCommand().execute();
        });
        resetItem = new JMenuItem("Reset");
        resetItem.addActionListener(e ->{
            new RestartGameCommand().execute();
        });
        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(e -> {
            System.exit(0);
        });
        howToPlayItem = new JMenuItem("How to play");
        howToPlayItem.addActionListener(e -> {
            showHowToPlayDialog();
        });
    }

    /**
     * Ajoute les composants au menu.
     */
    private void addComponentsToMenu() {
        menuOption.add(pauseItem);
        menuOption.add(resetItem);
        menuOption.add(quitItem);

        menuHelp.add(howToPlayItem);

        this.add(menuOption);
        this.add(menuHelp);
    }

    private void showHowToPlayDialog() {
        String msg = "1 : Outil de sélection\n" +
                "2 à 6 : Stylos (noir, bleu, vert, rouge, doré)\n" +
                "ctrl + 1 à ctrl + 4 : Achats (recharge d'encre, augmentation encre, ajout Pv, plus grande zone)\n" +
                "ctrl + z : Undo de la dernière commande\n" +
                "p : Mise en pause ou reprise de la partie\n\n" +
                "Les murs qui sont dessinés consomment de l'encre proportionnellement à la longueur du trait.\n" +
                "Les améliorations permettent d'augmenter le conteneur d'encre, la taille de la zone de dessin, d'augmenter les pv max du chateau ou encore de recharger complètement la barre d'encre.\n" +
                "Les différentes couleurs permettent d'arrêter les différents ennemis qui arrivent au fur et à mesure de la partie.\n" +
                "Plus on avance et plus les ennemis sont variés (couleur, vitesse, trajectoire), il faut adapter la couleur du stylo à la couleur de l'ennemi afin de le détruire.\n" +
                "Un ennemi tué rapport de l'argent pour devenir plus fort.";
        JOptionPane.showMessageDialog(this, msg, "How to play", JOptionPane.INFORMATION_MESSAGE);
    }
}