package org.maven.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LaundryFacility {

    private final List<WashingMachine>
            washingMachines =
            new ArrayList<>();

    private final List<Dryer>
            dryers =
            new ArrayList<>();

    private final List<Payment>
            payments =
            new ArrayList<>();

    private final Statistics statistics;

    private final LaundryGUI gui;

    private final Random random =
            new Random();

    public LaundryFacility(
            Statistics statistics,
            LaundryGUI gui) {

        this.statistics = statistics;

        this.gui = gui;

        for (int i = 1; i <= 6; i++) {

            washingMachines.add(
                    new WashingMachine(i)
            );
        }

        for (int i = 1; i <= 4; i++) {

            dryers.add(
                    new Dryer(i)
            );
        }

        for (int i = 1; i <= 2; i++) {

            payments.add(
                    new Payment(i)
            );
        }
    }

    // =========================================
    // WASHING MACHINE
    // =========================================

    public synchronized WashingMachine
    getAvailableWashingMachine()
            throws InterruptedException {

        while (true) {

            for (
                    WashingMachine machine :
                    washingMachines
            ) {

                if (!machine.isBusy()) {

                    machine.setBusy(true);

                    gui.updateWashingMachine(
                            machine.getMachineNumber(),
                            "BUSY"
                    );

                    return machine;
                }
            }

            System.out.println(
                    "All washing machines are busy. Customer waiting."
            );

            gui.addActivity(
                    "Customer waiting for washing machine..."
            );

            wait();
        }
    }

    public synchronized void
    releaseWashingMachine(
            WashingMachine machine) {

        machine.setBusy(false);

        gui.updateWashingMachine(
                machine.getMachineNumber(),
                "FREE"
        );

        notifyAll();
    }

    // =========================================
    // DRYER
    // =========================================

    public synchronized Dryer
    getAvailableDryer()
            throws InterruptedException {

        while (true) {

            for (
                    Dryer dryer :
                    dryers
            ) {

                if (!dryer.isBusy()) {

                    dryer.setBusy(true);

                    gui.updateDryer(
                            dryer.getDryerNumber(),
                            "BUSY"
                    );

                    return dryer;
                }
            }

            System.out.println(
                    "All dryers are busy. Customer waiting."
            );

            gui.addActivity(
                    "Customer waiting for dryer..."
            );

            wait();
        }
    }

    public synchronized void
    releaseDryer(
            Dryer dryer) {

        dryer.setBusy(false);

        gui.updateDryer(
                dryer.getDryerNumber(),
                "FREE"
        );

        notifyAll();
    }

    // =========================================
    // PAYMENT
    // =========================================

    public synchronized Payment
    getAvailablePayment()
            throws InterruptedException {

        while (true) {

            for (
                    Payment payment :
                    payments
            ) {

                if (!payment.isBusy()) {

                    payment.setBusy(true);

                    gui.updatePayment(
                            payment.getPaymentNumber(),
                            "BUSY"
                    );

                    return payment;
                }
            }

            System.out.println(
                    "All payment kiosks are busy. Customer waiting."
            );

            gui.addActivity(
                    "Customer waiting for payment kiosk..."
            );

            wait();
        }
    }

    public synchronized void
    releasePayment(
            Payment payment) {

        payment.setBusy(false);

        gui.updatePayment(
                payment.getPaymentNumber(),
                "FREE"
        );

        notifyAll();
    }

    // =========================================
    // RANDOM TIMES
    // =========================================

    public int getWashingTime() {

        return random.nextInt(3) + 4;
    }

    public int getDryingTime() {

        return random.nextInt(3) + 3;
    }

    public int getPaymentTime() {

        return random.nextInt(2) + 1;
    }

    // =========================================
    // FAILURE
    // =========================================

    public boolean washingMachineFails() {

        return random.nextInt(100) < 5;
    }

    public boolean paymentFails() {

        return random.nextInt(100) < 5;
    }

    // =========================================
    // STATISTICS
    // =========================================

    public Statistics getStatistics() {

        return statistics;
    }
}