package com.brands.drawdolls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_OUT_POPUP = 2000;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTimeOut();

        DollsFactory.init(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void setTimeOut() {

        handler.postDelayed(
                () -> switchActivity(DollListActivity.class),
                TIME_OUT_POPUP
        );

    }

    private void switchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}