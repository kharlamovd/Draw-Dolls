package com.brands.drawdolls.doll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Doll implements Serializable {

    public static final String DOLL_NAME = "doll";

    private int dollId;
    private int dollTitleStringId;
    private int stepsNum;
    private int currentStep;
    private boolean saveProgress;

    private DollStatus status;

    public Doll(int dollId, int dollTitleStringId, int stepsNum, DollStatus status) {
        this.dollId = dollId;
        this.dollTitleStringId = dollTitleStringId;
        this.stepsNum = stepsNum;
        this.currentStep = 1;

        this.status = status;
    }

    public Doll(int dollId, int dollTitleStringId, int stepsNum) {
        this.dollId = dollId;
        this.dollTitleStringId = dollTitleStringId;
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

    public int getDollTitleStringId() {
        return dollTitleStringId;
    }

    public void setDollTitleStringId(int dollTitleStringId) {
        this.dollTitleStringId = dollTitleStringId;
    }

    public Drawable getDollDrawable(int step, Context context) {

        Resources resources = context.getResources();
        String name = DOLL_NAME + (this.dollId + 1) + "_" + step;
        final int resourceId = resources.getIdentifier(
                name,
                "drawable",
                context.getPackageName()
        );

        return resources.getDrawable(resourceId);

    }

    public Drawable getDollTitleDrawable(Context context) {

        int step = this.getStepsNum();
        return getDollDrawable(step, context);

    }

    public boolean isSaveProgress() {
        return saveProgress;
    }

    public void setSaveProgress(boolean saveProgress) {
        this.saveProgress = saveProgress;
    }
}
