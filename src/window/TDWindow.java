package window;

import command.CommandManager;
import command.ToolSelectionCommand;
import game.Game;
import tools.ToolOption;
import window.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TDWindow {
    // Get screen size
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();
    private static final int COIN_SIZE = 50;

    private UpperMenu upperMenu;
    private ToolBar toolBar;
    private StatusBar statusBar;
    private ShopPanel shopPanel;
    private DrawingCanvas drawingCanvas;

    private CommandManager commandManager;

    public TDWindow() {
        commandManager = new CommandManager();

        Game game = Game.getInstance();

        // Main window
        JFrame frame = new JFrame("Paint Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        // Upper menu
        frame.setJMenuBar(createUpperMenu());

        // Canvas + shop
        JSplitPane splitPane = getJSplitPane();
        frame.add(splitPane, BorderLayout.CENTER);

        configureKeyBindings(frame);

        // Show window
        frame.setVisible(true);

        Timer timer = new Timer(20, (e) -> {
            game.tick();
            statusBar.update();
            drawingCanvas.update(); // Update enemy positions

            int currentWave = game.getWaveNumber();
            toolBar.unlockColor(currentWave);
        });

        timer.start();
    }

    /**
     * Configure key bindings for the main window.
     *
     * @param frame the main window frame
     */
    private void configureKeyBindings(JFrame frame) {
        JRootPane rootPane = frame.getRootPane();
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPane.getActionMap();

        // 1 -> pen
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "tool.pen");
        actionMap.put("tool.pen", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.PEN));
            }
        });

        // 2 -> select
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "tool.select");
        actionMap.put("tool.select", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.SELECT));
            }
        });

        // 3 -> black
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "tool.black");
        actionMap.put("tool.black", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLACK));
            }
        });

        // 4 -> blue
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "tool.blue");
        actionMap.put("tool.blue", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLUE));
            }
        });

        // 5 -> green
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "tool.green");
        actionMap.put("tool.green", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.GREEN));
            }
        });

        // 6 -> red
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "tool.red");
        actionMap.put("tool.red", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.RED));
            }
        });
    }

    /**
     * Create the upper menu with "Option" and "Help" menus.
     *
     * @return JMenuBar
     */
    private JMenuBar createUpperMenu() {

        upperMenu = new UpperMenu();
        return upperMenu;
    }

    /**
     * Create the shop menu containing the gold coins and the different
     * buying options. Now uses modular components.
     *
     * @return JPanel
     */
    private JPanel createShopMenu() {
        shopPanel = new ShopPanel(15);
        return shopPanel;
    }

    /**
     * Create the toolbar with different buttons.
     * For example: Pen, Select, Colors...
     *
     * @return JToolBar
     */
    private JToolBar createToolBar() {

        toolBar = new ToolBar(drawingCanvas, commandManager);
        return toolBar;
    }

    /**
     * Create the drawing canvas with a castle image and a red circle.
     *
     * @return JPanel
     */
    private JPanel createCanvas() {
        drawingCanvas = new DrawingCanvas(commandManager);
        return drawingCanvas;
    }

    /**
     * Create the status bar at the bottom of the canvas.
     *
     * @return JPanel
     */
    private JPanel createStatusBar() {
        statusBar = new StatusBar();
        return statusBar;
    }

    /**
     * Create the main split pane with the canvas and the shop.
     *
     * @return JSplitPane
     */
    private JSplitPane getJSplitPane() {

        JPanel drawingZone = createCanvas();
        JToolBar toolBar = createToolBar();
        JPanel statusBar = createStatusBar();
        JPanel shopPanel = createShopMenu();

        JPanel canvasPanel = new JPanel(new BorderLayout());
        canvasPanel.add(toolBar, BorderLayout.NORTH);
        canvasPanel.add(drawingZone, BorderLayout.CENTER);
        canvasPanel.add(statusBar, BorderLayout.SOUTH);

        // Separation
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                canvasPanel,
                shopPanel
        );
        int threeQuarterWidth = width * 3 / 4;
        splitPane.setDividerLocation(threeQuarterWidth);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);

        return splitPane;
    }

    // TODO : same comment as in statusBar, could we call update on a refresh rate ?
    public void updateStatusBar() {
        statusBar.update();
    }


}