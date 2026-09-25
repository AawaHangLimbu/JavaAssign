package org.maven.sample;

public class Dryer {

    private int dryerNumber;
    private boolean busy;

    public Dryer(int dryerNumber) {
        this.dryerNumber = dryerNumber;
        this.busy = false;
    }

    public int getDryerNumber() {
        return dryerNumber;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}