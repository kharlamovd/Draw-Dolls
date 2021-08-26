package com.brands.drawdolls;

import android.os.Bundle;
import android.widget.ImageButton;

import com.brands.drawdolls.nav_btns_listeners.BackOnClick;
import com.brands.drawdolls.nav_btns_listeners.BaseActivity;

public class BackButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBackButton();
    }

    protected void initBackButton() {
        ImageButton settingsButton = findViewById(R.id.backButton);
        settingsButton.setOnClickListener(new BackOnClick(this));
    }


}
