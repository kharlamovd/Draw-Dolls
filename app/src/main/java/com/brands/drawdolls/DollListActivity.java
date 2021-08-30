package com.brands.drawdolls;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;

import com.brands.drawdolls.doll.Doll;
import com.brands.drawdolls.doll.DollViewFactory;
import com.brands.drawdolls.doll.DollsFactory;
import com.brands.drawdolls.doll.Side;
import com.brands.drawdolls.nav_btns_listeners.BaseActivity;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;

public class DollListActivity extends BaseActivity {

    public static int FACTOR/* = getResources().getDisplayMetrics().density*/;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_list);

        FACTOR = (int) getResources().getDisplayMetrics().density;

        initDollsList();

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateDollsStatus();
    }

    private void initDollsList() {

        FlowLayout dollsLayout = findViewById(R.id.dollsLayout);
        ArrayList<Doll> dollList = DollsFactory.dollList;

        DollViewFactory dollViewFactory = new DollViewFactory(this);

        Side side = Side.LEFT;

        for (Doll doll: dollList) {

            Drawable titleDrawable = doll.getDollTitleDrawable(this);
            ImageView imageView = dollViewFactory.createImageView(titleDrawable);
            dollViewFactory.setTitleImageOnClick(imageView, doll.getDollId());

            ImageView statusImageView = dollViewFactory.createStatusImageView(
                    doll.getStatus(), imageView.getId()
            );

            CardView cardView = dollViewFactory.createCardView(side);
            side = switchSide(side);

            RelativeLayout cardLayout = dollViewFactory.createCardLayout();
            cardLayout.addView(imageView);
            cardLayout.addView(statusImageView);

            cardView.addView(cardLayout);

            dollsLayout.addView(cardView);

        }

    }

    private void updateDollsStatus() {

        LinearLayout stepsLayout = findViewById(R.id.stepsLayout);

        int childCount = stepsLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View v = stepsLayout.getChildAt(i);

            if (v instanceof CardView) {

            }
        }

    }

    private Side switchSide(Side side) {
        if (side == Side.LEFT)
            return Side.RIGHT;
        else
            return Side.LEFT;
    }

}