package org.maven.sample;

public class Customer
        implements Runnable {

    private final int customerNumber;

    private final LaundryFacility facility;

    private final LaundryGUI gui;

    public Customer(
            int customerNumber,
            LaundryFacility facility,
            LaundryGUI gui) {

        this.customerNumber =
                customerNumber;

        this.facility =
                facility;

        this.gui =
                gui;
    }

    @Override
    public void run() {

        long startTime =
                System.currentTimeMillis();

        try {

            String threadName =
                    Thread.currentThread()
                            .getName();

            log(
                    threadName
                            + " | Customer "
                            + customerNumber
                            + " entered."
            );

            gui.customerArrived();

            wash();

            dry();

            pay();

            long endTime =
                    System.currentTimeMillis();

            long totalTime =
                    (endTime - startTime)
                            / 1000;

            facility
                    .getStatistics()
                    .customerCompleted(
                            totalTime
                    );

            log(
                    threadName
                            + " | Customer "
                            + customerNumber
                            + " completed."
            );

            gui.customerServed();

        } catch (
                InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            log(
                    "Customer "
                            + customerNumber
                            + " was interrupted."
            );
        }
    }

    // WASHING

    private void wash()
            throws InterruptedException {

        boolean washed = false;

        while (!washed) {

            WashingMachine machine =
                    facility
                            .getAvailableWashingMachine();

            log(
                    "Customer "
                            + customerNumber
                            + " washing on Washer "
                            + machine
                            .getMachineNumber()
            );

            facility
                    .getStatistics()
                    .washerStarted();

            int time =
                    facility.getWashingTime();

            Thread.sleep(
                    time * 1000L
            );

            if (
                    facility
                            .washingMachineFails()
            ) {

                log(
                        "Customer "
                                + customerNumber
                                + " - Washer "
                                + machine
                                .getMachineNumber()
                                + " FAILED."
                );

                facility
                        .getStatistics()
                        .washingFailed();

                facility
                        .getStatistics()
                        .washerFinished();

                gui.updateWashingMachine(
                        machine.getMachineNumber(),
                        "FAILED"
                );

                Thread.sleep(500);

                facility
                        .releaseWashingMachine(
                                machine
                        );

                log(
                        "Customer "
                                + customerNumber
                                + " retrying washing."
                );

                continue;
            }

            facility
                    .getStatistics()
                    .washerFinished();

            facility
                    .releaseWashingMachine(
                            machine
                    );

            log(
                    "Customer "
                            + customerNumber
                            + " finished washing."
            );

            washed = true;
        }
    }

    // DRYING

    private void dry()
            throws InterruptedException {

        Dryer dryer =
                facility.getAvailableDryer();

        log(
                "Customer "
                        + customerNumber
                        + " drying on Dryer "
                        + dryer.getDryerNumber()
        );

        facility
                .getStatistics()
                .dryerStarted();

        int time =
                facility.getDryingTime();

        Thread.sleep(
                time * 1000L
        );

        facility
                .getStatistics()
                .dryerFinished();

        facility.releaseDryer(
                dryer
        );

        log(
                "Customer "
                        + customerNumber
                        + " finished drying."
        );
    }

    // PAYMENT

    private void pay()
            throws InterruptedException {

        boolean paid = false;

        while (!paid) {

            Payment payment =
                    facility
                            .getAvailablePayment();

            log(
                    "Customer "
                            + customerNumber
                            + " paying at Kiosk "
                            + payment
                            .getPaymentNumber()
            );

            int time =
                    facility.getPaymentTime();

            Thread.sleep(
                    time * 1000L
            );

            if (
                    facility.paymentFails()
            ) {

                log(
                        "Customer "
                                + customerNumber
                                + " payment FAILED."
                );

                facility
                        .getStatistics()
                        .paymentFailed();

                gui.updatePayment(
                        payment.getPaymentNumber(),
                        "FAILED"
                );

                facility
                        .releasePayment(
                                payment
                        );

                log(
                        "Customer "
                                + customerNumber
                                + " retrying payment in 2 seconds."
                );

                Thread.sleep(2000);

                continue;
            }

            facility.releasePayment(
                    payment
            );

            log(
                    "Customer "
                            + customerNumber
                            + " payment successful."
            );

            paid = true;
        }
    }

    // LOG

    private void log(
            String message) {

        System.out.println(
                message
        );

        gui.addActivity(
                message
        );
    }
}