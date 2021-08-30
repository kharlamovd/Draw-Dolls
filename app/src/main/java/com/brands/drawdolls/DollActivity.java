package com.brands.drawdolls;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brands.drawdolls.doll.Doll;
import com.brands.drawdolls.doll.DollsFactory;

public class DollActivity extends BackButtonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll);

        initBackButton();

        Intent intent = getIntent();
        Doll doll = (Doll) intent.getSerializableExtra("doll");

        TextView header = findViewById(R.id.headerText);
        header.setText(doll.getDollTitleStringId());

        populateStepLayout(doll);

        Button nextButton = findViewById(R.id.nextButton);
        setOnNextButtonClick(nextButton, doll);
        Button doneButton = findViewById(R.id.doneButton);
        setOnDoneButtonClick(doneButton);

    }

    private void populateStepLayout(Doll doll) {

        LinearLayout stepsLayout = findViewById(R.id.stepsLayout);

        int stepsNum = doll.getStepsNum();

        Button firstStep = createStepButton(1, getDrawable(R.drawable.step_circle), Color.WHITE);
        setStepButtonOnClick(firstStep, doll);
        stepsLayout.addView(firstStep);

        for (int i = 2; i <= stepsNum; i++) {
            Button newBtn = createStepButton(i, null, Color.BLACK);
            setStepButtonOnClick(newBtn, doll);
            stepsLayout.addView(newBtn);
        }

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

        if (background != null)
            stepButton.setBackground(background);
        else
            stepButton.setBackgroundColor(Color.TRANSPARENT);

        return stepButton;

    }

    private void setStepButtonOnClick(Button stepButton, Doll doll) {

        stepButton.setOnClickListener(view -> {

            int clickedStep = Integer.parseInt(stepButton.getText().toString());
            doll.setCurrentStep(clickedStep);

            updateStepsLayout(doll);
            updateStepImage(doll);
            updateButton(doll);

        });

    }

    private void updateStepsLayout(Doll doll) {

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
                    //((Button) v).setTextScaleX(0);
                    ((Button) v).setTextSize(0);
                } else if (stepNum > currentStep) {
                    v.setBackgroundColor(Color.TRANSPARENT);
                    ((Button) v).setTextColor(Color.BLACK);
                    //((Button) v).setTextScaleX(1);
                    float textSize = getResources().getDimension(R.dimen.step_button_text_size);
                    ((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                } else {
                    ((Button) v).setTextColor(Color.WHITE);
                    v.setBackgroundResource(R.drawable.step_circle);
                    //((Button) v).setTextScaleX(1);
                    float textSize = getResources().getDimension(R.dimen.step_button_text_size);
                    ((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                }
            }
        }

    }

    private void updateStepImage(Doll doll) {

        ImageView imageView = findViewById(R.id.stepImageView);
        Drawable stepDrawable = doll.getDollDrawable(doll.getCurrentStep(), this);
        imageView.setImageDrawable(stepDrawable);

    }

    private void updateButton(Doll doll) {

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

    private void setOnNextButtonClick(Button button, Doll doll) {

        button.setOnClickListener(view -> {

            doll.setCurrentStep(doll.getCurrentStep() + 1);

            updateStepsLayout(doll);
            updateStepImage(doll);
            updateButton(doll);

        });

    }

    private void setOnDoneButtonClick(Button button) {

        button.setOnClickListener(view -> {

            DollsFactory.saveDolls(this);
            this.finish();

        });

    }

}