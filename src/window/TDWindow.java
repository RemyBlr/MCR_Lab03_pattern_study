package window;

import javax.swing.*;
import java.awt.*;

public class TDWindow {
    private static TDWindow instance;
    private static final int width = 1500;

    private TDWindow() {
        // Main window
        JFrame frame = new JFrame("Paint Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, 700);
        frame.setLocationRelativeTo(null);

        // Upper menu
        frame.add(createUpperMenu(), BorderLayout.NORTH);

        // Left menu
        JPanel shop = createShopMenu();
        frame.add(shop);

        // Canvas
        frame.add(getJSplitPane(shop));

        // Show window
        frame.setVisible(true);
    }

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

    private JPanel createShopMenu() {
        JPanel shopPanel = new JPanel();
        shopPanel.setBackground(new Color(230, 230, 250));

        JLabel shopLabel = new JLabel("Gold: 0.9£");
        shopPanel.add(shopLabel);

        // Add coin image
        JLabel gold_coin = new JLabel(new ImageIcon("./img/gold-coin.jpg"));
        gold_coin.setPreferredSize(new Dimension(20, 20));
        //gold_coin.setSize(new Dimension(20, 20));
        shopPanel.add(gold_coin);

        return shopPanel;
    }

    private static JSplitPane getJSplitPane(JPanel shop) {
        JPanel canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Drawing zone", 10, 20);
            }
        };
        canvasPanel.setBackground(Color.WHITE);

        // Separation
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                canvasPanel,
                shop
        );
        int threeQuarterWidth = width * 3 / 4;
        splitPane.setDividerLocation(threeQuarterWidth);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);

        return splitPane;
    }

    public static TDWindow getInstance() {
        if(instance == null)
            instance = new TDWindow();
        return instance;
    }
}
