package org.maven.sample;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

// Circular loading indicator used for washers, dryers and kiosks.
// BUSY / WAITING spin, FREE shows an idle ring, FAILED shows a red ring.
public class LoadingCard extends JComponent {

    private static final Color TRACK = new Color(225, 228, 232);
    private static final Color IDLE = new Color(170, 175, 182);
    private static final Color BUSY = new Color(60, 150, 75);
    private static final Color WAITING = new Color(220, 150, 50);
    private static final Color FAILED = new Color(200, 70, 70);

    private final String name;

    private String status = "FREE";

    private int angle = 0;

    private final Timer timer;

    public LoadingCard(String name) {

        this.name = name;

        setPreferredSize(
                new Dimension(120, 110)
        );

        timer = new Timer(
                16,
                e -> {
                    angle = (angle + 6) % 360;
                    repaint();
                }
        );
    }

    public void setStatus(String status) {

        this.status = status.toUpperCase();

        if (isSpinning()) {
            timer.start();
        } else {
            timer.stop();
        }

        repaint();
    }

    private boolean isSpinning() {
        return status.equals("BUSY")
                || status.equals("WAITING");
    }

    private Color statusColor() {
        switch (status) {
            case "BUSY":
                return BUSY;
            case "WAITING":
                return WAITING;
            case "FAILED":
                return FAILED;
            default:
                return IDLE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );

        int w = getWidth();
        int h = getHeight();

        Font nameFont = new Font("Arial", Font.BOLD, 12);
        Font statusFont = new Font("Arial", Font.BOLD, 10);

        int nameHeight = g2.getFontMetrics(nameFont).getHeight();

        float stroke = 6f;
        int size = Math.max(
                20,
                Math.min(w, h - nameHeight - 8) - (int) stroke - 4
        );

        double x = (w - size) / 2.0;
        double y = 4 + stroke / 2;

        Color color = statusColor();

        g2.setStroke(
                new BasicStroke(
                        stroke,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                )
        );

        // track
        g2.setColor(TRACK);
        g2.draw(new Ellipse2D.Double(x, y, size, size));

        // indicator
        g2.setColor(color);

        if (isSpinning()) {

            g2.draw(
                    new Arc2D.Double(
                            x, y, size, size,
                            -angle, 100,
                            Arc2D.OPEN
                    )
            );

        } else if (status.equals("FAILED")) {

            g2.draw(new Ellipse2D.Double(x, y, size, size));
        }

        // status text inside the ring
        g2.setFont(statusFont);
        FontMetrics sm = g2.getFontMetrics();
        g2.drawString(
                status,
                (int) (w - sm.stringWidth(status)) / 2,
                (int) (y + size / 2.0 + sm.getAscent() / 2.0 - 1)
        );

        // name below the ring
        g2.setFont(nameFont);
        g2.setColor(new Color(50, 50, 55));
        FontMetrics nm = g2.getFontMetrics();
        g2.drawString(
                name,
                (w - nm.stringWidth(name)) / 2,
                (int) (y + size + stroke / 2 + 4 + nm.getAscent())
        );

        g2.dispose();
    }
}
