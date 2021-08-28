package com.brands.drawdolls;

public class Doll {

    private int dollId;
    private int stepsNum;
    private int currentStep;

    private DollStatus status;

    public Doll(int dollId, int stepsNum, DollStatus status) {
        this.dollId = dollId;
        this.stepsNum = stepsNum;
        this.currentStep = 1;

        this.status = status;
    }

    public Doll(int dollId, int stepsNum) {
        this.dollId = dollId;
        this.stepsNum = stepsNum;
        this.currentStep = 1;

        status = DollStatus.NEW;
    }

    public DollStatus getStatus() {
        return status;
    }

    public void setStatus(DollStatus status) {
        this.status = status;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getStepsNum() {
        return stepsNum;
    }

    public void setStepsNum(int stepsNum) {
        this.stepsNum = stepsNum;
    }

    public int getDollId() {
        return dollId;
    }

    public void setDollId(int dollId) {
        this.dollId = dollId;
    }
}
