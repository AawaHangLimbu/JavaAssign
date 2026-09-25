package org.maven.sample;

public class Payment {

    private int paymentNumber;
    private boolean busy;

    public Payment(int paymentNumber) {
        this.paymentNumber = paymentNumber;
        this.busy = false;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}