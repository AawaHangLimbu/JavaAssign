package org.maven.sample;

public class WashingMachine {

    private int machineNumber;
    private boolean busy;

    public WashingMachine(int machineNumber) {
        this.machineNumber = machineNumber;
        this.busy = false;
    }

    public int getMachineNumber() {
        return machineNumber;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}