package com.brands.drawdolls;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
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

    private void initDollsList() {

        FlowLayout dollsLayout = findViewById(R.id.dollsLayout);
        ArrayList<Doll> dollList = DollsFactory.dollList;

        DollViewFactory dollViewFactory = new DollViewFactory(this);

        Side side = Side.LEFT;

        for (Doll doll: dollList) {

            int dollId = doll.getDollId();

            Drawable titleDrawable = DollsFactory.getDollTitleDrawable(dollId, this);
            ImageView imageView = dollViewFactory.createImageView(titleDrawable);
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

    private Side switchSide(Side side) {
        if (side == Side.LEFT)
            return Side.RIGHT;
        else
            return Side.LEFT;
    }

}