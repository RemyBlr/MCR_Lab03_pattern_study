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

    private JButton selectButton;
    private JButton blackButton;
    JButton blueButton;
    JButton greenButton;
    JButton redButton;
    JButton goldButton;

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
        // pre-set colors
        toolChanged(ToolManager.getInstance().getCurrentTool());
    }

    /**
     * Initialise les composants de la barre d'outils.
     */
    private void initializeComponents() {
        // Je savais pas que c'était possible de faire ça, ça m'arrangeait que ça fonctionne donc j'ai essayé
        final List<JButton> buttonList = Arrays.asList(
            selectButton = new ButtonIcon("./img/select.png", "Select", "1"),
            blackButton = new ButtonIcon("./img/black.png", "Black", "2"),
            blueButton = new ButtonIcon("./img/blue.png", "Blue", "3"),
            greenButton = new ButtonIcon("./img/green.png", "Green", "4"),
            redButton = new ButtonIcon("./img/red.png", "Red", "5"),
            goldButton = new ButtonIcon("./img/gold.png", "Gold", "6")
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
        buttonMap.put(ToolOption.SELECT, selectButton);
        buttonMap.put(ToolOption.BLACK_PEN, blackButton);
        buttonMap.put(ToolOption.BLUE_PEN, blueButton);
        buttonMap.put(ToolOption.GREEN_PEN, greenButton);
        buttonMap.put(ToolOption.RED_PEN, redButton);
        buttonMap.put(ToolOption.GOLD_PEN, goldButton);

        // Listeners
        selectButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.SELECT))
        );
        blackButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLACK_PEN))
        );
        blueButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLUE_PEN))
        );
        greenButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.GREEN_PEN))
        );
        redButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.RED_PEN))
        );

        goldButton.addActionListener(e ->
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.GOLD_PEN))
        );

        blackButton.setVisible(true);
        greenButton.setVisible(false);
        redButton.setVisible(false);
        blueButton.setVisible(false);
        goldButton.setVisible(false);
    }

    /**
     * Ajoute les composants à la barre d'outils.
     */
    private void addComponentsToToolBar() {
        add(selectButton);
        add(Box.createHorizontalStrut(10));
        add(blackButton);
        add(Box.createHorizontalStrut(10));
        add(blueButton);
        add(Box.createHorizontalStrut(10));
        add(greenButton);
        add(Box.createHorizontalStrut(10));
        add(redButton);
        add(Box.createHorizontalStrut(10));
        add(goldButton);
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
        if (wave >= 5) greenButton.setVisible(true);
        if (wave >= 10) redButton.setVisible(true);
        if (wave >= 999) goldButton.setVisible(true);
        revalidate();
        repaint();
    }
}