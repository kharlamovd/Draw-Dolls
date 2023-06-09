package com.brands.drawdolls;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.brands.drawdolls.doll.Doll;
import com.brands.drawdolls.doll.DollViewFactory;
import com.brands.drawdolls.doll.DollsFactory;
import com.brands.drawdolls.doll.Side;
import com.brands.drawdolls.nav_btns_listeners.BaseActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class DollListActivity extends BaseActivity {

    public static int FACTOR/* = getResources().getDisplayMetrics().density*/;
    private Side side = Side.LEFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_list);

        FACTOR = (int) getResources().getDisplayMetrics().density;

        new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("60D1E2B4C3056ED6C64AF12841F0E182"));
        loadAndShowNativeAd();

        loadInterstitial();
    }

    @Override
    public void onBackPressed() {
        showInterstitial();

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        clearDollListLayout();
        initDollsList();
    }

    private void clearDollListLayout() {

        FlowLayout dollsLayout = findViewById(R.id.dollsLayout);
        dollsLayout.removeAllViewsInLayout();

    }

    private void initDollsList() {

        ArrayList<Doll> dollList = DollsFactory.dollList;

        DollViewFactory dollViewFactory = new DollViewFactory(this);

        ArrayList<CardView> dollsInProgList = new ArrayList<>();
        ArrayList<CardView> dollsDoneList = new ArrayList<>();
        ArrayList<CardView> dollsNewList = new ArrayList<>();
        ArrayList<CardView> dollsNoneList = new ArrayList<>();

        if (dollList != null)
        for (Doll doll: dollList) {

            Drawable titleDrawable = doll.getDollTitleDrawable(this);
            ImageView imageView = dollViewFactory.createImageView(titleDrawable);
            dollViewFactory.setTitleImageOnClick(imageView, doll.getDollId());

            ImageView statusImageView = dollViewFactory.createStatusImageView(
                    doll.getStatus(), imageView.getId()
            );

            CardView cardView = dollViewFactory.createCardView();

            RelativeLayout cardLayout = dollViewFactory.createCardLayout();
            cardLayout.addView(imageView);
            cardLayout.addView(statusImageView);
            if (doll.isReward()) {
                ImageView rewardImageView = dollViewFactory.createRewardLabel(imageView.getId());
                cardLayout.addView(rewardImageView);
            }

            cardView.addView(cardLayout);

            switch (doll.getStatus()) {
                case NEW:
                    dollsNewList.add(cardView);
                    break;
                case DONE:
                    dollsDoneList.add(cardView);
                    break;
                case IN_PROGRESS:
                    dollsInProgList.add(cardView);
                    break;
                default:
                    dollsNoneList.add(cardView);
            }

        }

        addListToLayout(dollsInProgList);
        addListToLayout(dollsDoneList);
        addListToLayout(dollsNewList);
        addListToLayout(dollsNoneList);

    }

    private void addListToLayout(ArrayList<CardView> list) {

        DollViewFactory dollViewFactory = new DollViewFactory(this);
        FlowLayout dollsLayout = findViewById(R.id.dollsLayout);

        for (CardView dollView : list) {
            LinearLayout.LayoutParams newParams = dollViewFactory.setDollViewMargins(
                    (LinearLayout.LayoutParams) dollView.getLayoutParams(),
                    side
            );
            dollView.setLayoutParams(newParams);

            dollsLayout.addView(dollView);
            side = switchSide();
        }

        list.clear();

    }

    private Side switchSide() {
        if (side == Side.LEFT)
            return Side.RIGHT;
        else
            return Side.LEFT;
    }



}