package org.maven.sample;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// Rounded equipment card with an animated striped loading bar.
public class LoadingCard extends JComponent {

    private final String name;
    private String status = "FREE";
    private int offset = 0;
    private final Timer timer = new Timer(30, e -> { offset = (offset + 1) % 16; repaint(); });

    public LoadingCard(String name) {
        this.name = name;
        setPreferredSize(new Dimension(120, 70));
    }

    public void setStatus(String status) {
        this.status = status.toUpperCase();
        if (isActive()) timer.start(); else timer.stop();
        repaint();
    }

    private boolean isActive() {
        return status.equals("BUSY") || status.equals("WAITING");
    }

    private Color color() {
        switch (status) {
            case "BUSY": return new Color(60, 150, 75);
            case "WAITING": return new Color(220, 150, 50);
            case "FAILED": return new Color(200, 70, 70);
            default: return new Color(170, 175, 182);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth() - 1, h = getHeight() - 1;
        Color c = color();

        // card
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, w, h, 14, 14);
        g2.setColor(isActive() || status.equals("FAILED") ? c : new Color(220, 222, 226));
        g2.drawRoundRect(0, 0, w, h, 14, 14);

        // name + status dot
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.setColor(new Color(50, 50, 55));
        g2.drawString(name, 12, 22);
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.setColor(c);
        g2.fillOval(12, 31, 8, 8);
        g2.drawString(status, 25, 39);

        // loading bar
        int bx = 12, by = h - 18, bw = w - 24, bh = 8;
        Shape bar = new RoundRectangle2D.Float(bx, by, bw, bh, bh, bh);
        g2.setColor(new Color(235, 237, 240));
        g2.fill(bar);
        if (!status.equals("FREE")) {
            g2.setClip(bar);
            g2.setColor(c);
            g2.fill(bar);
            if (isActive()) {
                g2.setColor(new Color(255, 255, 255, 90));
                for (int x = bx - 16 + offset; x < bx + bw; x += 16) {
                    g2.fillPolygon(new int[]{x, x + 8, x + 16, x + 8}, new int[]{by + bh, by, by, by + bh}, 4);
                }
            }
        }
        g2.dispose();
    }
}
