package org.maven.sample;

public class Statistics {

    private int customersServed = 0;
    private long totalCustomerTime = 0;

    private int currentWashers = 0;
    private int maxWashers = 0;

    private int currentDryers = 0;
    private int maxDryers = 0;

    private int washingFailures = 0;
    private int paymentFailures = 0;

    public synchronized void customerCompleted(
            long customerTime) {

        customersServed++;
        totalCustomerTime += customerTime;
    }

    public synchronized void washerStarted() {

        currentWashers++;

        if (currentWashers > maxWashers) {
            maxWashers = currentWashers;
        }
    }

    public synchronized void washerFinished() {

        currentWashers--;

        if (currentWashers < 0) {
            currentWashers = 0;
        }
    }

    public synchronized void dryerStarted() {

        currentDryers++;

        if (currentDryers > maxDryers) {
            maxDryers = currentDryers;
        }
    }

    public synchronized void dryerFinished() {

        currentDryers--;

        if (currentDryers < 0) {
            currentDryers = 0;
        }
    }

    public synchronized void washingFailed() {
        washingFailures++;
    }

    public synchronized void paymentFailed() {
        paymentFailures++;
    }

    public synchronized int getCustomersServed() {
        return customersServed;
    }

    public synchronized void printStatistics() {

        double averageTime = 0;

        if (customersServed > 0) {

            averageTime =
                    (double) totalCustomerTime
                            / customersServed;
        }

        System.out.println();
        System.out.println("========== FINAL STATISTICS ==========");

        System.out.println(
                "Customers served: "
                        + customersServed
        );

        System.out.println(
                "Average customer time: "
                        + String.format(
                        "%.2f",
                        averageTime
                )
                        + " seconds"
        );

        System.out.println(
                "Maximum washers in use: "
                        + maxWashers
        );

        System.out.println(
                "Maximum dryers in use: "
                        + maxDryers
        );

        System.out.println(
                "Washing machine failures: "
                        + washingFailures
        );

        System.out.println(
                "Payment failures: "
                        + paymentFailures
        );

        System.out.println(
                "======================================"
        );
    }
}