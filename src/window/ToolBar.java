package window;

import command.CommandManager;
import command.ToolSelectionCommand;
import tools.ToolChangeListener;
import tools.ToolManager;
import tools.ToolOption;
import window.components.ButtonIcon;
import javax.swing.border.Border;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Classe représentant la barre d'outils de l'application Paint Tower Defense.
 * Contient les boutons pour dessiner, sélectionner et changer de couleur.
 */
public class ToolBar extends JToolBar implements ToolChangeListener {

    private final Map<ToolOption, JButton> buttonMap = new EnumMap<>(ToolOption.class);
    private JButton currentSelected;
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.BLUE, 3);
    private static final Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(3,3,3,3);

    private JButton penButton;
    private JButton selectButton;
    private JButton blackButton;
    private JButton blueButton;
    private JButton greenButton;
    private JButton redButton;

    private final CommandManager commandManager;

    /**
     * Constructeur de la classe ToolBar.
     */
    public ToolBar(DrawingCanvas canvas, CommandManager commandManager) {
        this.commandManager = commandManager;

        setFloatable(false);
        initializeComponents();
        addComponentsToToolBar();

        // register the toolbar as a listener for tool changes
        ToolManager.getInstance().addListener(this);

        blackButton.setVisible(true);
        greenButton.setVisible(false);
        redButton.setVisible(false);
        blueButton.setVisible(false);

        // fill the button map with tool options and their corresponding buttons
        buttonMap.put(ToolOption.PEN, penButton);
        buttonMap.put(ToolOption.SELECT, selectButton);
        buttonMap.put(ToolOption.BLACK, blackButton);
        buttonMap.put(ToolOption.BLUE, blueButton);
        buttonMap.put(ToolOption.GREEN, greenButton);
        buttonMap.put(ToolOption.RED, redButton);

        // pre-set colors
        toolChanged(ToolManager.getInstance().getCurrentTool());
    }

    /**
     * Initialise les composants de la barre d'outils.
     */
    private void initializeComponents() {
        // Je savais pas que c'était possible de faire ça, ça m'arrangeait que ça fonctionne donc j'ai essayé
        final List<JButton> buttonList = Arrays.asList(
            penButton = new ButtonIcon("./img/pen.png", "Pen", "1"),
            selectButton = new ButtonIcon("./img/select.png", "Select", "2"),
            blackButton = new ButtonIcon("./img/black.png", "Black", "3"),
            blueButton = new ButtonIcon("./img/blue.png", "Blue", "4"),
            greenButton = new ButtonIcon("./img/green.png", "Green", "5"),
            redButton = new ButtonIcon("./img/red.png", "Red", "6")
        );

        for (JButton b : buttonList) {
            b.setOpaque(true);
            b.setBackground(null);
            b.setBorder(UNSELECTED_BORDER);
            b.setBorderPainted(true);
            b.setContentAreaFilled(true);
            b.setFocusPainted(false);
        }

        // Fill map
        buttonMap.put(ToolOption.PEN,    penButton);
        buttonMap.put(ToolOption.SELECT, selectButton);

        // Listeners
        penButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.PEN))
        );
        selectButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.SELECT))
        );
        blackButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLACK))
        );
        blueButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLUE))
        );
        greenButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.GREEN))
        );
        redButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.RED))
        );
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
     * Called by ToolManager when the current tool changes.
     * @param option the new ToolOption selected
     */
    public void toolChanged(ToolOption option) {
        // deselect previous selection
        if (currentSelected != null) {
            currentSelected.setBorder(UNSELECTED_BORDER);
            currentSelected.setBackground(null);
        }

        // select new button
        JButton btn = buttonMap.get(option);
        if (btn != null) {
            btn.setBorder(SELECTED_BORDER);
            btn.setBackground(Color.WHITE);
            currentSelected = btn;
        }
    }

    /**
     * Unlocks the color buttons based on the current wave.
     * @param wave the current wave number
     */
    public void unlockColor(int wave) {
        if (wave >= 2) blueButton.setVisible(true);
        if (wave >= 3) greenButton.setVisible(true);
        if (wave >= 4) redButton.setVisible(true);
        revalidate();
        repaint();
    }
}