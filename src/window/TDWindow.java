package window;

import command.CommandManager;
import window.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class TDWindow {
    private static TDWindow instance;

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

    private TDWindow() {
        // Main window
        JFrame frame = new JFrame("Paint Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        commandManager = new CommandManager();

        // Upper menu
        frame.setJMenuBar(createUpperMenu());

        // Canvas + shop
        JSplitPane splitPane = getJSplitPane();
        frame.add(splitPane, BorderLayout.CENTER);

        // Show window
        frame.setVisible(true);
    }

    /**
     * Create the upper menu with "Option" and "Help" menus.
     *
     * @return JMenuBar
     */
    private JMenuBar createUpperMenu() {
//        JMenuBar menuBar = new JMenuBar();
//
//        JMenu menuOption = new JMenu("Option");
//        menuOption.add(new JMenuItem("Reset"));
//        menuOption.add(new JMenuItem("Quit"));
//
//        JMenu menuHelp = new JMenu("Help");
//        menuHelp.add(new JMenuItem("About us"));
//
//        menuBar.add(menuOption);
//        menuBar.add(menuHelp);

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
//        JToolBar toolBar = new JToolBar();
//        toolBar.setFloatable(false); // can't move the toolbar
//
//        JButton penButton = new ButtonIcon("./img/pen.png", "Stylo");
//        JButton selectButton = new ButtonIcon("./img/select.png", "Sélection");
//        JButton blackButton = new ButtonIcon("./img/black.png", "Noir");
//        JButton blueButton = new ButtonIcon("./img/blue.png", "Bleu");
//        JButton greenButton = new ButtonIcon("./img/green.png", "Vert");
//        JButton redButton = new ButtonIcon("./img/red.png", "Rouge");
//
//        toolBar.add(penButton);
//        toolBar.add(Box.createHorizontalStrut(10));
//        toolBar.add(selectButton);
//        toolBar.add(Box.createHorizontalStrut(10));
//        toolBar.add(blackButton);
//        toolBar.add(Box.createHorizontalStrut(10));
//        toolBar.add(blueButton);
//        toolBar.add(Box.createHorizontalStrut(10));
//        toolBar.add(greenButton);
//        toolBar.add(Box.createHorizontalStrut(10));
//        toolBar.add(redButton);

        toolBar = new ToolBar(drawingCanvas);
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
//        JPanel statusBar = new JPanel(new BorderLayout());
//        statusBar.setBackground(new Color(245, 245, 245));
//        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5)); // marges fines
//
//        JLabel inkLabel = new JLabel("Encre: 100");
//        JLabel waveLabel = new JLabel("Vague: 1");
//        JLabel timeLabel = new JLabel("Temps: 00:00");
//
//        JPanel centerPanel = new JPanel();
//        centerPanel.setOpaque(false);
//        centerPanel.add(waveLabel);
//
//        statusBar.add(inkLabel, BorderLayout.WEST);
//        statusBar.add(centerPanel, BorderLayout.CENTER);
//        statusBar.add(timeLabel, BorderLayout.EAST);

        statusBar = new StatusBar();
        return statusBar;
    }

    /**
     * Create the main split pane with the canvas and the shop.
     *
     * @return JSplitPane
     */
    private JSplitPane getJSplitPane() {

        JToolBar toolBar = createToolBar();
        JPanel drawingZone = createCanvas();
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


//    /**
//     * Create a button with an icon and a tooltip.
//     *
//     * @param iconPath Path to the icon image
//     * @param tooltip  Tooltip text
//     * @return JButton
//     */
//    private JButton createIconButton(String iconPath, String tooltip) {
//        ImageIcon icon = new ImageIcon(iconPath);
//
//        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//
//        JButton button = new JButton(scaledIcon);
//        button.setToolTipText(tooltip);
//
//        button.setBorderPainted(false);
//        button.setFocusPainted(false);
//        button.setContentAreaFilled(false);
//
//        return button;
//    }

    /**
     * Get the singleton instance of TDWindow.
     *
     * @return TDWindow instance
     */
    public static TDWindow getInstance() {
        if(instance == null)
            instance = new TDWindow();
        return instance;
    }
}