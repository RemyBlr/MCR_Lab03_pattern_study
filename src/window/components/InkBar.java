/*
 * File: InkBar.java
 * Author: Bleuer Rémy, Changanaqui Yoann & Richard Aurélien
 * Date: 15.06.2025
 * Description: InkBar class represents a bar that displays the current amount of ink in the game.
 * Version: 1.0
 */
package window.components;

import game.Game;

import javax.swing.*;
import java.awt.*;

public class InkBar extends JComponent {
    private static final int BAR_WIDTH  = 120;
    private static final int BAR_HEIGHT = 10;

    public InkBar() {
        Dimension d = new Dimension(BAR_WIDTH, BAR_HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Game game = Game.getInstance();

        int ink = game.getInk();
        int maxInk = game.getMaxInk();

        double ratio = maxInk > 0 ? (double)ink / maxInk : 0;
        ratio = Math.max(0, Math.min(1, ratio));

        int fillW = (int)(getWidth() * ratio);

        Graphics2D g2 = (Graphics2D) g.create();
        // background
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, getWidth(), getHeight());
        // ink
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, fillW, getHeight());
        // border
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
        // text
        String text = ink + "/" + maxInk;
        FontMetrics metrics = g2.getFontMetrics();
        g2.setColor(Color.WHITE);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();
        int textX = (getWidth() - textWidth) / 2;
        int textY = (getHeight() + textHeight) / 2 - 2;
        g2.drawString(text, textX, textY);

        g2.dispose();
    }
}
