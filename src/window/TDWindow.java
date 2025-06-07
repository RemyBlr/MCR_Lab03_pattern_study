package window;

import command.*;
import game.Game;
import game.GameObserver;
import tools.ToolOption;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TDWindow {
    // Get screen size
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth() - 100;
    private final int height = (int) screenSize.getHeight() - 100;

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

        game.addObserver(statusBar);
        game.addObserver(drawingCanvas);

        Timer timer = new Timer(20, (e) -> {
            game.tick();
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

        // 1 -> select
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "tool.select");
        actionMap.put("tool.select", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.SELECT));
            }
        });

        // 2 -> black
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "tool.black");
        actionMap.put("tool.black", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLACK_PEN));
            }
        });

        // 3 -> blue
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "tool.blue");
        actionMap.put("tool.blue", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toolBar.blueButton.isVisible())
                    commandManager.executeCommand(new ToolSelectionCommand(ToolOption.BLUE_PEN));
            }
        });

        // 4 -> green
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "tool.green");
        actionMap.put("tool.green", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toolBar.greenButton.isVisible())
                    commandManager.executeCommand(new ToolSelectionCommand(ToolOption.GREEN_PEN));
            }
        });

        // 5 -> red
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "tool.red");
        actionMap.put("tool.red", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toolBar.redButton.isVisible())
                    commandManager.executeCommand(new ToolSelectionCommand(ToolOption.RED_PEN));
            }
        });

        // ctrl + 1 -> refill ink
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), "shop.refillInk");
        actionMap.put("shop.refillInk", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new RefillInkCommand(Game.getInstance(), ShopPanel.REFILL_INK_PRICE));
            }
        });

        // ctrl + 2 -> add ink
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), "shop.addInk");
        actionMap.put("shop.addInk", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new AddInkCapacityCommand(Game.getInstance(), ShopPanel.ADD_INK_PRICE));
            }
        });

        // ctrl + 3 -> add hp
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK), "shop.addHp");
        actionMap.put("shop.addHp", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new AddHpCommand(Game.getInstance(), ShopPanel.ADD_PV_PRICE));
            }
        });

        // ctrl + 4 -> add zone
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK), "shop.addZone");
        actionMap.put("shop.addZone", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                commandManager.executeCommand(new ExtendZoneCommand(Game.getInstance(), ShopPanel.ADD_ZONE_PRICE));
            }
        });

        // ctrl + 5 -> mystery item

        // ctrl + z -> undo
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "util.undo");
        actionMap.put("util.undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandManager.undo();
                drawingCanvas.updateWalls();
            }
        });
    }

    /**
     * Create the upper menu with "Option" and "Help" menus.
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
    /*private JPanel createShopMenu() {
        shopPanel = new ShopPanel(game.Game.getInstance(), drawingCanvas, commandManager);
        return shopPanel;
    }*/

    /**
     * Create the toolbar with different buttons.
     * For example: Pen, Select, Colors...
     * @return JToolBar
     */
    private JToolBar createToolBar() {

        toolBar = new ToolBar(drawingCanvas, commandManager);
        return toolBar;
    }

    /**
     * Create the drawing canvas with a castle image and a red circle.
     * @return JPanel
     */
    private JPanel createCanvas() {
        drawingCanvas = new DrawingCanvas(commandManager);
        return drawingCanvas;
    }

    /**
     * Create the status bar at the bottom of the canvas.
     * @return JPanel
     */
    private JPanel createStatusBar() {
        statusBar = new StatusBar();
        return statusBar;
    }

    /**
     * Create the main split pane with the canvas and the shop.
     * @return JSplitPane
     */
    private JSplitPane getJSplitPane() {

        JPanel drawingZone = createCanvas();
        JToolBar toolBar = createToolBar();
        JPanel statusBar = createStatusBar();
        JPanel shopPanel = new ShopPanel(game.Game.getInstance(), commandManager);
        // TODO : why is not stored in TDWIndow anymore ? Why do we recreate this one instead of updating an existing one ?
        // TODO : We could update prices of things, like each extension is more expenseive
        Game.getInstance().addObserver((GameObserver) shopPanel); // Added as listener, to respect order

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
}