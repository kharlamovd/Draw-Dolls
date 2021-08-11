package com.brands.drawdolls.nav_btns_listeners;

import android.app.Activity;
import android.view.View;

public class BackOnClick implements View.OnClickListener {

    private Activity activity;

    public BackOnClick(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.onBackPressed();
    }

}
