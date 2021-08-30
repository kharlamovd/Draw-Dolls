package com.brands.drawdolls;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.brands.drawdolls.doll.Doll;
import com.brands.drawdolls.doll.DollStatus;
import com.brands.drawdolls.doll.DollsFactory;

public class DollActivity extends BackButtonActivity {

    private Doll doll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll);

        Intent intent = getIntent();
        this.doll = (Doll) intent.getSerializableExtra("doll");

        ImageButton settingsButton = findViewById(R.id.backButton);
        settingsButton.setOnClickListener(view -> onBackPressed());

        TextView header = findViewById(R.id.headerText);
        header.setText(doll.getDollTitleStringId());

        populateStepLayout();

        Button nextButton = findViewById(R.id.nextButton);
        setOnNextButtonClick(nextButton);
        Button doneButton = findViewById(R.id.doneButton);
        setOnDoneButtonClick(doneButton);

    }

    private void populateStepLayout() {

        LinearLayout stepsLayout = findViewById(R.id.stepsLayout);

        int stepsNum = doll.getStepsNum();
        int currentStep = doll.getCurrentStep();

        Button firstStep = createStepButton(1, getDrawable(R.drawable.step_circle), Color.WHITE);
        setStepButtonOnClick(firstStep);
        stepsLayout.addView(firstStep);

        for (int i = 2; i <= stepsNum; i++) {
            Button newBtn = createStepButton(i, null, Color.BLACK);
            setStepButtonOnClick(newBtn);
            stepsLayout.addView(newBtn);

            if (i == currentStep)
                newBtn.callOnClick();
        }

        update();

    }

    private Button createStepButton(int num, Drawable background, int textColor) {

        Button stepButton = new Button(this);

        int size = (int) getResources().getDimension(R.dimen.step_circle_size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);

        int margin = (int) getResources().getDimension(R.dimen.step_layout_margin);
        layoutParams.setMargins(0, 0, margin, 0);

        stepButton.setLayoutParams(layoutParams);
        stepButton.setText(Integer.toString(num));
        stepButton.setTextColor(textColor);

        float textSize = getResources().getDimension(R.dimen.step_button_text_size);
        stepButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        if (background != null)
            stepButton.setBackground(background);
        else
            stepButton.setBackgroundColor(Color.TRANSPARENT);

        return stepButton;

    }

    private void setStepButtonOnClick(Button stepButton) {

        stepButton.setOnClickListener(view -> {

            int clickedStep = Integer.parseInt(stepButton.getText().toString());
            doll.setCurrentStep(clickedStep);
            update();

        });

    }

    private void updateStepsLayout() {

        LinearLayout stepsLayout = findViewById(R.id.stepsLayout);

        int currentStep = doll.getCurrentStep();
        doll.setCurrentStep(currentStep);
        int childCount = stepsLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View v = stepsLayout.getChildAt(i);
            if (v instanceof Button) {
                int stepNum = Integer.parseInt(((Button) v).getText().toString());
                if (stepNum < currentStep) {
                    v.setBackgroundResource(R.drawable.step_done_circle);
                    ((Button) v).setTextScaleX(0);
                    //((Button) v).setTextSize(0);
                } else if (stepNum > currentStep) {
                    v.setBackgroundColor(Color.TRANSPARENT);
                    ((Button) v).setTextColor(Color.BLACK);
                    ((Button) v).setTextScaleX(1);
                    /*float textSize = getResources().getDimension(R.dimen.step_button_text_size);
                    ((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);*/
                } else {
                    ((Button) v).setTextColor(Color.WHITE);
                    v.setBackgroundResource(R.drawable.step_circle);
                    ((Button) v).setTextScaleX(1);

                    scrollToStepButton((Button) v);
                    /*float textSize = getResources().getDimension(R.dimen.step_button_text_size);
                    ((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);*/
                }
            }
        }

    }

    private void updateStepImage() {

        ImageView imageView = findViewById(R.id.stepImageView);
        Drawable stepDrawable = doll.getDollDrawable(doll.getCurrentStep(), this);
        imageView.setImageDrawable(stepDrawable);

    }

    private void updateButton() {

        Button nextButton = findViewById(R.id.nextButton);
        Button doneButton = findViewById(R.id.doneButton);

        if (doll.getCurrentStep() == doll.getStepsNum()) {
            nextButton.setVisibility(View.GONE);
            doneButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.GONE);
        }



    }

    private void setOnNextButtonClick(Button button) {

        button.setOnClickListener(view -> {

            this.doll.setCurrentStep(doll.getCurrentStep() + 1);
            update();

        });

    }

    private void update() {

        updateStepsLayout();
        updateStepImage();
        updateButton();

    }

    private void scrollToStepButton(Button button) {

        HorizontalScrollView stepScroll = findViewById(R.id.stepScroll);
        stepScroll.scrollTo(button.getLeft(), 0);

    }

    private void setOnDoneButtonClick(Button button) {

        button.setOnClickListener(view -> {

            DollsFactory.saveDolls(this);
            this.finish();

        });

    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.save_prog)
                .setPositiveButton(R.string.yes, (dialog, id) -> {

                    doll.setSaveProgress(true);
                    doll.setStatus(DollStatus.IN_PROGRESS);
                    DollsFactory.dollList.set(doll.getDollId(), doll);
                    DollsFactory.saveDolls(this);
                    this.finish();

                })
                .setNegativeButton(R.string.no, (dialog, id) -> {

                    doll.setCurrentStep(1);
                    doll.setStatus(DollStatus.NONE);
                    DollsFactory.dollList.set(doll.getDollId(), doll);
                    DollsFactory.saveDolls(this);
                    this.finish();

                })
                .setNeutralButton(R.string.cancel, (dialog, id) -> {

                    dialog.cancel();

                });

        Dialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {

        if (doll.isSaveProgress())
            DollsFactory.saveDolls(this);
        else
            showDialog();

    }
}