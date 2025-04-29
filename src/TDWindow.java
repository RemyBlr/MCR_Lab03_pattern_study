import javax.swing.*;
import java.awt.*;

public class TDWindow {
    private static TDWindow instance;
    private static final int width = 1000;
    private static final int quarterWidth = width / 4;
    private static final int threeQuarterWidth = quarterWidth * 3;

    private TDWindow() {
        // Main window
        JFrame frame = new JFrame("Paint Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, 700);
        frame.setLocationRelativeTo(null);

        // Upper menu
        frame.add(createUpperMenu());

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
        shopPanel.setPreferredSize(new Dimension(quarterWidth, 0));

        JLabel shopLabel = new JLabel("Empty for now");
        shopPanel.add(shopLabel);

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
