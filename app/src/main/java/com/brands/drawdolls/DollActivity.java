package com.brands.drawdolls;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DollActivity extends BackButtonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll);

        initBackButton();

        Intent intent = getIntent();
        String headerText = intent.getStringExtra("header");

        TextView header = findViewById(R.id.headerText);
        header.setText(headerText);

    }

}