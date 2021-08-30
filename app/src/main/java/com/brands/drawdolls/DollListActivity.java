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
        Side side = Side.LEFT;

        for (CardView dollView : list) {
            dollViewFactory.setDollViewMargins(
                    (LinearLayout.LayoutParams) dollView.getLayoutParams(),
                    side
            );
            dollsLayout.addView(dollView);
            side = switchSide(side);
        }

        list.clear();

    }

    private Side switchSide(Side side) {
        if (side == Side.LEFT)
            return Side.RIGHT;
        else
            return Side.LEFT;
    }

}