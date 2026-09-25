package org.maven.sample;

import javax.swing.*;
import java.awt.*;

public class LaundryGUI extends JFrame {

    private final LoadingCard[] washingCards = new LoadingCard[6];
    private final LoadingCard[] dryerCards = new LoadingCard[4];
    private final LoadingCard[] paymentCards = new LoadingCard[2];

    private final JLabel customersLabel = createStatisticCard("Arrived", "0 / 50");
    private final JLabel servedLabel = createStatisticCard("Served", "0");
    private final JLabel washerUsageLabel = createStatisticCard("Washers Busy", "0 / 6");
    private final JLabel dryerUsageLabel = createStatisticCard("Dryers Busy", "0 / 4");

    private final JTextArea activityArea = new JTextArea();

    private int customersArrived = 0;
    private int customersServed = 0;
    private int busyWashers = 0;
    private int busyDryers = 0;

    public LaundryGUI() {
        setTitle("Smart Laundry");
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createGUI();
        setVisible(true);
    }

    private void createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 246, 248));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        JLabel title = new JLabel("Smart Laundry");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        JLabel status = new JLabel("● Simulation Running");
        status.setFont(new Font("Arial", Font.PLAIN, 14));
        status.setForeground(new Color(60, 150, 75));
        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(status, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        addSection(contentPanel, "Washing Machines", createCards(washingCards, "Washer"));
        addSection(contentPanel, "Dryers", createCards(dryerCards, "Dryer"));
        addSection(contentPanel, "Payment Kiosks", createCards(paymentCards, "Kiosk"));
        addSection(contentPanel, "Statistics",
                customersLabel, servedLabel, washerUsageLabel, dryerUsageLabel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // ACTIVITY LOG
        JPanel activityPanel = new JPanel(new BorderLayout());
        activityPanel.setOpaque(false);
        activityPanel.add(createTitle("Live Activity"), BorderLayout.NORTH);
        activityArea.setEditable(false);
        activityArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(activityArea);
        scrollPane.setPreferredSize(new Dimension(0, 120));
        activityPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(activityPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private LoadingCard[] createCards(LoadingCard[] cards, String name) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new LoadingCard(name + " " + (i + 1));
        }
        return cards;
    }

    private void addSection(JPanel parent, String title, JComponent... items) {
        JPanel row = new JPanel(new GridLayout(1, items.length, 8, 8));
        row.setOpaque(false);
        for (JComponent item : items) row.add(item);
        parent.add(createTitle(title));
        parent.add(row);
        parent.add(Box.createVerticalStrut(12));
    }

    private JLabel createTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(2, 0, 5, 0));
        return label;
    }

    private JLabel createStatisticCard(String title, String value) {
        JLabel label = new JLabel(statText(title, value), SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        return label;
    }

    private String statText(String title, Object value) {
        return "<html><center>" + title + "<br><b>" + value + "</b></center></html>";
    }

    // Returns the new busy count after applying a status change.
    private int updateBusy(int busy, String status) {
        if (status.equalsIgnoreCase("BUSY")) return busy + 1;
        if (status.equalsIgnoreCase("FREE") || status.equalsIgnoreCase("FAILED")) return Math.max(0, busy - 1);
        return busy;
    }

    public void customerArrived() {
        SwingUtilities.invokeLater(() ->
                customersLabel.setText(statText("Arrived", ++customersArrived + " / 50")));
    }

    public void customerServed() {
        SwingUtilities.invokeLater(() ->
                servedLabel.setText(statText("Served", ++customersServed)));
    }

    public void updateWashingMachine(int machineNumber, String status) {
        SwingUtilities.invokeLater(() -> {
            washingCards[machineNumber - 1].setStatus(status);
            busyWashers = updateBusy(busyWashers, status);
            washerUsageLabel.setText(statText("Washers Busy", busyWashers + " / 6"));
        });
    }

    public void updateDryer(int dryerNumber, String status) {
        SwingUtilities.invokeLater(() -> {
            dryerCards[dryerNumber - 1].setStatus(status);
            busyDryers = updateBusy(busyDryers, status);
            dryerUsageLabel.setText(statText("Dryers Busy", busyDryers + " / 4"));
        });
    }

    public void updatePayment(int paymentNumber, String status) {
        SwingUtilities.invokeLater(() -> paymentCards[paymentNumber - 1].setStatus(status));
    }

    public void addActivity(String message) {
        SwingUtilities.invokeLater(() -> {
            activityArea.append(message + "\n");
            activityArea.setCaretPosition(activityArea.getDocument().getLength());
        });
    }
}
