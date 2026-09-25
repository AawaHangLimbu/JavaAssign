package org.maven.sample;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    startSimulation();
                }
        );
    }

    private static void startSimulation() {

        System.out.println(
                "================================="
        );

        System.out.println(
                "     SMART LAUNDRY SIMULATION"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "Washing Machines: 6"
        );

        System.out.println(
                "Dryers: 4"
        );

        System.out.println(
                "Payment Kiosks: 2"
        );

        System.out.println(
                "Customers: 50"
        );

        System.out.println(
                "================================="
        );

        // Create GUI
        LaundryGUI gui =
                new LaundryGUI();

        // Create statistics
        Statistics statistics =
                new Statistics();

        // Create laundry facility
        LaundryFacility facility =
                new LaundryFacility(
                        statistics,
                        gui
                );

        // =====================================
        // CUSTOMER ARRIVAL
        // =====================================

        Thread arrivalThread =
                new Thread(
                        () -> {

                            for (
                                    int i = 1;
                                    i <= 50;
                                    i++
                            ) {

                                Customer customer =
                                        new Customer(
                                                i,
                                                facility,
                                                gui
                                        );

                                Thread customerThread =
                                        new Thread(
                                                customer,
                                                "Customer-" + i
                                        );

                                customerThread.start();

                                try {

                                    // 0-3 seconds
                                    int arrivalTime =
                                            (int)
                                                    (
                                                            Math.random()
                                                                    * 4
                                                    );

                                    Thread.sleep(
                                            arrivalTime
                                                    * 1000L
                                    );

                                } catch (
                                        InterruptedException e
                                ) {

                                    Thread.currentThread()
                                            .interrupt();

                                    break;
                                }
                            }

                            System.out.println(
                                    "All 50 customers have arrived."
                            );

                        },
                        "Customer-Arrival"
                );

        arrivalThread.start();
    }
}