package org.maven.sample;

import javax.swing.*;
import java.awt.*;

public class LaundryGUI extends JFrame {

    private JLabel customersLabel;
    private JLabel servedLabel;
    private JLabel washerUsageLabel;
    private JLabel dryerUsageLabel;

    private JLabel[] washingLabels =
            new JLabel[6];

    private JLabel[] dryerLabels =
            new JLabel[4];

    private JLabel[] paymentLabels =
            new JLabel[2];

    private JTextArea activityArea;

    private int customersArrived = 0;
    private int customersServed = 0;

    private int busyWashers = 0;
    private int busyDryers = 0;

    public LaundryGUI() {

        setTitle("Smart Laundry");

        setSize(900, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBackground(
                new Color(245, 246, 248)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================
        // HEADER
        // =====================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Smart Laundry"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel status =
                new JLabel(
                        "● Simulation Running"
                );

        status.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        status.setForeground(
                new Color(
                        60,
                        150,
                        75
                )
        );

        headerPanel.add(
                title,
                BorderLayout.WEST
        );

        headerPanel.add(
                status,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================
        // MAIN CONTENT
        // =====================================

        JPanel contentPanel =
                new JPanel();

        contentPanel.setOpaque(false);

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // =====================================
        // WASHERS
        // =====================================

        contentPanel.add(
                createTitle(
                        "Washing Machines"
                )
        );

        JPanel washingPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
                                8,
                                8
                        )
                );

        washingPanel.setOpaque(false);

        for (int i = 0; i < 6; i++) {

            washingLabels[i] =
                    createEquipmentCard(
                            "Washer " + (i + 1)
                    );

            washingPanel.add(
                    washingLabels[i]
            );
        }

        contentPanel.add(
                washingPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(12)
        );

        // =====================================
        // DRYERS
        // =====================================

        contentPanel.add(
                createTitle(
                        "Dryers"
                )
        );

        JPanel dryerPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                8,
                                8
                        )
                );

        dryerPanel.setOpaque(false);

        for (int i = 0; i < 4; i++) {

            dryerLabels[i] =
                    createEquipmentCard(
                            "Dryer " + (i + 1)
                    );

            dryerPanel.add(
                    dryerLabels[i]
            );
        }

        contentPanel.add(
                dryerPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(12)
        );

        // =====================================
        // PAYMENT
        // =====================================

        contentPanel.add(
                createTitle(
                        "Payment Kiosks"
                )
        );

        JPanel paymentPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                8,
                                8
                        )
                );

        paymentPanel.setOpaque(false);

        for (int i = 0; i < 2; i++) {

            paymentLabels[i] =
                    createEquipmentCard(
                            "Kiosk " + (i + 1)
                    );

            paymentPanel.add(
                    paymentLabels[i]
            );
        }

        contentPanel.add(
                paymentPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(12)
        );

        // =====================================
        // STATISTICS
        // =====================================

        contentPanel.add(
                createTitle(
                        "Statistics"
                )
        );

        JPanel statisticsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                8,
                                8
                        )
                );

        statisticsPanel.setOpaque(false);

        customersLabel =
                createStatisticCard(
                        "Arrived",
                        "0 / 50"
                );

        servedLabel =
                createStatisticCard(
                        "Served",
                        "0"
                );

        washerUsageLabel =
                createStatisticCard(
                        "Washers Busy",
                        "0 / 6"
                );

        dryerUsageLabel =
                createStatisticCard(
                        "Dryers Busy",
                        "0 / 4"
                );

        statisticsPanel.add(
                customersLabel
        );

        statisticsPanel.add(
                servedLabel
        );

        statisticsPanel.add(
                washerUsageLabel
        );

        statisticsPanel.add(
                dryerUsageLabel
        );

        contentPanel.add(
                statisticsPanel
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =====================================
        // ACTIVITY LOG
        // =====================================

        JPanel activityPanel =
                new JPanel(
                        new BorderLayout()
                );

        activityPanel.setOpaque(false);

        JLabel activityTitle =
                new JLabel(
                        "Live Activity"
                );

        activityTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        activityArea =
                new JTextArea();

        activityArea.setEditable(false);

        activityArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        11
                )
        );

        activityArea.setBackground(
                Color.WHITE
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        activityArea
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        0,
                        120
                )
        );

        activityPanel.add(
                activityTitle,
                BorderLayout.NORTH
        );

        activityPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                activityPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================================
    // SECTION TITLE
    // =========================================

    private JLabel createTitle(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        2,
                        0,
                        5,
                        0
                )
        );

        return label;
    }

    // =========================================
    // EQUIPMENT CARD
    // =========================================

    private JLabel createEquipmentCard(
            String name) {

        JLabel label =
                new JLabel(
                        "<html><center>"
                                + "<b>"
                                + name
                                + "</b>"
                                + "<br>FREE"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        label.setPreferredSize(
                new Dimension(
                        120,
                        60
                )
        );

        label.setOpaque(true);

        label.setBackground(
                new Color(
                        232,
                        234,
                        237
                )
        );

        label.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                210,
                                212,
                                215
                        )
                )
        );

        return label;
    }

    // =========================================
    // STATISTIC CARD
    // =========================================

    private JLabel createStatisticCard(
            String title,
            String value) {

        JLabel label =
                new JLabel(
                        "<html><center>"
                                + title
                                + "<br>"
                                + "<b>"
                                + value
                                + "</b>"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        label.setOpaque(true);

        label.setBackground(
                Color.WHITE
        );

        label.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                220,
                                220
                        )
                )
        );

        return label;
    }

    // =========================================
    // STATUS COLORS
    // =========================================

    private void setStatusColor(
            JLabel label,
            String status) {

        if (status.equalsIgnoreCase(
                "BUSY")) {

            label.setBackground(
                    new Color(
                            190,
                            230,
                            195
                    )
            );

            label.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(
                                    80,
                                    170,
                                    90
                            ),
                            2
                    )
            );

        } else if (status.equalsIgnoreCase(
                "FREE")) {

            label.setBackground(
                    new Color(
                            232,
                            234,
                            237
                    )
            );

            label.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(
                                    210,
                                    212,
                                    215
                            )
                            )
                    );

        } else if (status.equalsIgnoreCase(
                "FAILED")) {

            label.setBackground(
                    new Color(
                            245,
                            190,
                            190
                    )
            );

            label.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(
                                    200,
                                    70,
                                    70
                            ),
                            2
                    )
            );

        } else if (status.equalsIgnoreCase(
                "WAITING")) {

            label.setBackground(
                    new Color(
                            250,
                            220,
                            170
                    )
            );

            label.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(
                                    220,
                                    150,
                                    50
                            ),
                            2
                    )
            );
        }
    }

    // =========================================
    // CUSTOMER ARRIVED
    // =========================================

    public void customerArrived() {

        SwingUtilities.invokeLater(() -> {

            customersArrived++;

            customersLabel.setText(
                    "<html><center>"
                            + "Arrived"
                            + "<br><b>"
                            + customersArrived
                            + " / 50"
                            + "</b></center></html>"
            );
        });
    }

    // =========================================
    // CUSTOMER SERVED
    // =========================================

    public void customerServed() {

        SwingUtilities.invokeLater(() -> {

            customersServed++;

            servedLabel.setText(
                    "<html><center>"
                            + "Served"
                            + "<br><b>"
                            + customersServed
                            + "</b></center></html>"
            );
        });
    }

    // =========================================
    // WASHING MACHINE
    // =========================================

    public void updateWashingMachine(
            int machineNumber,
            String status) {

        SwingUtilities.invokeLater(() -> {

            JLabel label =
                    washingLabels[
                            machineNumber - 1
                            ];

            label.setText(
                    "<html><center>"
                            + "<b>Washer "
                            + machineNumber
                            + "</b>"
                            + "<br>"
                            + status
                            + "</center></html>"
            );

            setStatusColor(
                    label,
                    status
            );

            if (status.equalsIgnoreCase(
                    "BUSY")) {

                busyWashers++;

            } else if (
                    status.equalsIgnoreCase(
                            "FREE")
                            ||
                            status.equalsIgnoreCase(
                                    "FAILED")
            ) {

                busyWashers--;

                if (busyWashers < 0) {
                    busyWashers = 0;
                }
            }

            washerUsageLabel.setText(
                    "<html><center>"
                            + "Washers Busy"
                            + "<br><b>"
                            + busyWashers
                            + " / 6"
                            + "</b></center></html>"
            );
        });
    }

    // =========================================
    // DRYER
    // =========================================

    public void updateDryer(
            int dryerNumber,
            String status) {

        SwingUtilities.invokeLater(() -> {

            JLabel label =
                    dryerLabels[
                            dryerNumber - 1
                            ];

            label.setText(
                    "<html><center>"
                            + "<b>Dryer "
                            + dryerNumber
                            + "</b>"
                            + "<br>"
                            + status
                            + "</center></html>"
            );

            setStatusColor(
                    label,
                    status
            );

            if (status.equalsIgnoreCase(
                    "BUSY")) {

                busyDryers++;

            } else if (
                    status.equalsIgnoreCase(
                            "FREE")
                            ||
                            status.equalsIgnoreCase(
                                    "FAILED")
            ) {

                busyDryers--;

                if (busyDryers < 0) {
                    busyDryers = 0;
                }
            }

            dryerUsageLabel.setText(
                    "<html><center>"
                            + "Dryers Busy"
                            + "<br><b>"
                            + busyDryers
                            + " / 4"
                            + "</b></center></html>"
            );
        });
    }

    // =========================================
    // PAYMENT
    // =========================================

    public void updatePayment(
            int paymentNumber,
            String status) {

        SwingUtilities.invokeLater(() -> {

            JLabel label =
                    paymentLabels[
                            paymentNumber - 1
                            ];

            label.setText(
                    "<html><center>"
                            + "<b>Kiosk "
                            + paymentNumber
                            + "</b>"
                            + "<br>"
                            + status
                            + "</center></html>"
            );

            setStatusColor(
                    label,
                    status
            );
        });
    }

    // =========================================
    // ACTIVITY
    // =========================================

    public void addActivity(
            String message) {

        SwingUtilities.invokeLater(() -> {

            activityArea.append(
                    message + "\n"
            );

            activityArea.setCaretPosition(
                    activityArea
                            .getDocument()
                            .getLength()
            );
        });
    }
}