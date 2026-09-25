package org.maven.sample;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen
        extends JFrame {

    private JProgressBar progressBar;

    private JLabel statusLabel;

    public LoadingScreen(
            Runnable onComplete) {

        setTitle(
                "Smart Laundry"
        );

        setSize(
                500,
                320
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        createGUI();

        setVisible(true);

        startLoading(
                onComplete
        );
    }

    private void createGUI() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory
                        .createEmptyBorder(
                                45,
                                50,
                                40,
                                50
                        )
        );

        // =================================
        // ICON / LABEL
        // =================================

        JLabel iconLabel =
                new JLabel(
                        "LAUNDRY"
                );

        iconLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================
        // TITLE
        // =================================

        JLabel titleLabel =
                new JLabel(
                        "SMART LAUNDRY"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================
        // SUBTITLE
        // =================================

        JLabel subtitleLabel =
                new JLabel(
                        "Facility Simulator"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================
        // STATUS
        // =================================

        statusLabel =
                new JLabel(
                        "Preparing facility..."
                );

        statusLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================
        // PROGRESS BAR
        // =================================

        progressBar =
                new JProgressBar();

        progressBar.setMinimum(0);

        progressBar.setMaximum(100);

        progressBar.setValue(0);

        progressBar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =================================
        // ADD COMPONENTS
        // =================================

        panel.add(
                iconLabel
        );

        panel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        panel.add(
                titleLabel
        );

        panel.add(
                subtitleLabel
        );

        panel.add(
                Box.createVerticalStrut(
                        35
                )
        );

        panel.add(
                statusLabel
        );

        panel.add(
                Box.createVerticalStrut(
                        10
                )
        );

        panel.add(
                progressBar
        );

        add(panel);
    }

    // =========================================
    // LOADING ANIMATION
    // =========================================

    private void startLoading(
            Runnable onComplete) {

        Timer timer =
                new Timer(
                        40,
                        null
                );

        timer.addActionListener(
                e -> {

                    int value =
                            progressBar
                                    .getValue()
                                    + 2;

                    progressBar.setValue(
                            value
                    );

                    if (value < 30) {

                        statusLabel.setText(
                                "Initializing washing machines..."
                        );

                    } else if (value < 60) {

                        statusLabel.setText(
                                "Preparing dryers..."
                        );

                    } else if (value < 85) {

                        statusLabel.setText(
                                "Connecting payment kiosks..."
                        );

                    } else {

                        statusLabel.setText(
                                "Starting simulation..."
                        );
                    }

                    if (value >= 100) {

                        timer.stop();

                        dispose();

                        onComplete.run();
                    }
                }
        );

        timer.start();
    }
}