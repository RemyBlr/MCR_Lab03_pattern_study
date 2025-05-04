package window;

import javax.swing.*;
import java.awt.*;

public class TDWindow {
    private static TDWindow instance;
    // Get screen size
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) screenSize.getWidth();
    private final int height = (int) screenSize.getHeight();
    private static final int COIN_SIZE = 40;

    private TDWindow() {
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

        // Show window
        frame.setVisible(true);
    }

    /**
     * Create the upper menu with "Option" and "Help" menus.
     *
     * @return JMenuBar
     */
    private JMenuBar createUpperMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuOption = new JMenu("Option");
        menuOption.add(new JMenuItem("Reset"));
        menuOption.add(new JMenuItem("Quit"));

        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(new JMenuItem("About us"));

        menuBar.add(menuOption);
        menuBar.add(menuHelp);

        return menuBar;
    }

    /**
     * Create the shop menu containing the gold coins and the different
     * buying options.
     *
     * @return JPanel
     */
    private JPanel createShopMenu() {
        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BoxLayout(shopPanel, BoxLayout.Y_AXIS));
        shopPanel.setBackground(new Color(230, 230, 250));

        // Gold display panel
        JPanel goldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        goldPanel.setOpaque(false);

        JLabel shopLabel = new JLabel("Gold : 15");
        shopLabel.setFont(new Font("Arial", Font.BOLD, 16));
        goldPanel.add(shopLabel);

        // Add coin image
        ImageIcon goldCoinImg = new ImageIcon("./img/gold-coin.png");
        java.awt.Image scaledImage = goldCoinImg.getImage().getScaledInstance(COIN_SIZE, COIN_SIZE, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel goldCoin = new JLabel(resizedIcon);
        goldCoin.setPreferredSize(new Dimension(COIN_SIZE, COIN_SIZE));
        goldPanel.add(goldCoin);

        // Add gold display to main panel with some padding
        shopPanel.add(Box.createVerticalStrut(20));
        shopPanel.add(goldPanel);
        shopPanel.add(Box.createVerticalStrut(40));

        // Container for buttons with margins
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        buttonsContainer.setOpaque(false);
        buttonsContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Add shop buttons
        buttonsContainer.add(createShopButton("refill ink", 100, new Color(216, 181, 207)));
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(createShopButton("+ ink", 8, new Color(211, 84, 50)));
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(createShopButton("+ pv", 20, new Color(162, 208, 183)));
        buttonsContainer.add(Box.createVerticalStrut(20));
        buttonsContainer.add(createShopButton("+ zone", 75, new Color(183, 187, 208)));
        buttonsContainer.add(Box.createVerticalStrut(40));

        // Mystery item
        buttonsContainer.add(createShopButton("???", 999, new Color(234, 180, 167)));

        shopPanel.add(buttonsContainer);
        shopPanel.add(Box.createVerticalStrut(50));

        // Item grid
        JPanel gridContainer = new JPanel();
        gridContainer.setOpaque(false);
        gridContainer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        gridContainer.setLayout(new GridLayout(2, 2, 5, 5));
        gridContainer.setPreferredSize(new Dimension(200, 150));
        gridContainer.setMaximumSize(new Dimension(200, 150));

        // Add 4 item buttons
        for (int i = 1; i <= 4; i++) {
            JButton itemButton = new JButton("Item " + i);
            itemButton.setBackground(new Color(200, 200, 200));
            itemButton.setFocusPainted(false);
            itemButton.setHorizontalAlignment(SwingConstants.CENTER);
            itemButton.setBorderPainted(false);
            gridContainer.add(itemButton);
        }

        // Center the grid container
        JPanel gridWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gridWrapper.setOpaque(false);
        gridWrapper.add(gridContainer);
        shopPanel.add(gridWrapper);

        // Add bottom padding
        shopPanel.add(Box.createVerticalStrut(20));
        shopPanel.add(Box.createVerticalGlue());

        return shopPanel;
    }

    /**
     * Create a shop button with text, price, and color
     *
     * @param text Button text
     * @param price Item price
     * @param bgColor Background color
     * @return JPanel containing the button and price
     */
    private JPanel createShopButton(String text, int price, Color bgColor) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        buttonPanel.setOpaque(false);

        // Create button
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        buttonPanel.add(button, BorderLayout.WEST);

        // Create price label with coin
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        pricePanel.setOpaque(false);

        JLabel priceLabel = new JLabel(String.valueOf(price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));

        ImageIcon coinIcon = new ImageIcon("./img/gold-coin.png");
        Image scaledCoin = coinIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel coinLabel = new JLabel(new ImageIcon(scaledCoin));

        pricePanel.add(priceLabel);
        pricePanel.add(coinLabel);

        buttonPanel.add(pricePanel, BorderLayout.EAST);

        return buttonPanel;
    }

    /**
     * Create the toolbar with different buttons.
     * For example: Pen, Select, Colors...
     *
     * @return JToolBar
     */
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // can't move the toolbar

        JButton penButton = createIconButton("./img/pen.png", "Stylo");
        JButton selectButton = createIconButton("./img/select.png", "Sélection");
        JButton blackButton = createIconButton("./img/black.png", "Noir");
        JButton blueButton = createIconButton("./img/blue.png", "Bleu");
        JButton greenButton = createIconButton("./img/green.png", "Vert");
        JButton redButton = createIconButton("./img/red.png", "Rouge");

        toolBar.add(penButton);
        toolBar.add(Box.createHorizontalStrut(10));
        toolBar.add(selectButton);
        toolBar.add(Box.createHorizontalStrut(10));
        toolBar.add(blackButton);
        toolBar.add(Box.createHorizontalStrut(10));
        toolBar.add(blueButton);
        toolBar.add(Box.createHorizontalStrut(10));
        toolBar.add(greenButton);
        toolBar.add(Box.createHorizontalStrut(10));
        toolBar.add(redButton);

        return toolBar;
    }

    /**
     * Create the drawing canvas with a castle image and a red circle.
     *
     * @return JPanel
     */
    private JPanel createCanvas() {
        ImageIcon castleIcon = new ImageIcon("./img/castle.png");
        Image castleImage = castleIcon.getImage();
        int castleWidth = 100;
        int castleHeight = 100;
        Image scaledCastleImage = castleImage.getScaledInstance(castleWidth, castleHeight, Image.SCALE_SMOOTH);

        JPanel canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Center coordinates for circle
                int width = getWidth();
                int height = getHeight();
                int centerX = width / 2;
                int centerY = height / 2;

                // Red drawing zone
                int radius = 200;
                Color transparentRed = new Color(255, 0, 0, 27);
                g2d.setColor(transparentRed);
                g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

                // Castle
                int imgX = centerX - castleWidth / 2;
                int imgY = centerY - castleHeight / 2;
                g2d.drawImage(scaledCastleImage, imgX, imgY, this);

                g2d.dispose();
            }
        };
        canvasPanel.setBackground(Color.WHITE);

        return canvasPanel;
    }

    /**
     * Create the status bar at the bottom of the canvas.
     *
     * @return JPanel
     */
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(new Color(245, 245, 245));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5)); // marges fines

        JLabel inkLabel = new JLabel("Encre: 100");
        JLabel waveLabel = new JLabel("Vague: 1");
        JLabel timeLabel = new JLabel("Temps: 00:00");

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(waveLabel);

        statusBar.add(inkLabel, BorderLayout.WEST);
        statusBar.add(centerPanel, BorderLayout.CENTER);
        statusBar.add(timeLabel, BorderLayout.EAST);

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


    /**
     * Create a button with an icon and a tooltip.
     *
     * @param iconPath Path to the icon image
     * @param tooltip  Tooltip text
     * @return JButton
     */
    private JButton createIconButton(String iconPath, String tooltip) {
        ImageIcon icon = new ImageIcon(iconPath);

        Image scaledImage = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setToolTipText(tooltip);

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }

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
