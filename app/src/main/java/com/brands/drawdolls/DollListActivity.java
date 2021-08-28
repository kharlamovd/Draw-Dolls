package com.brands.drawdolls;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

import com.brands.drawdolls.nav_btns_listeners.BaseActivity;
import com.nex3z.flowlayout.FlowLayout;

import java.io.IOException;
import java.io.InputStream;
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

        for (Doll doll: dollList) {

            int dollId = doll.getDollId();

            Drawable titleDrawable = DollsFactory.getDollTitleDrawable(dollId, this);
            ImageView imageView = createImageView(titleDrawable);

            CardView cardView = createCardView();
            cardView.addView(imageView);

            dollsLayout.addView(cardView);

        }

    }

    private CardView createCardView() {

        CardView cardView = new CardView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int cardMargin = getCardMargin();
        int cardMarginBottom = (int) getResources().getDimension(R.dimen.doll_list_item_margin);
        layoutParams.setMargins(cardMargin, 0, cardMargin, cardMarginBottom / 2);

        cardView.setLayoutParams(layoutParams);
        cardView.setCardElevation(
                getResources().getDimension(R.dimen.card_elevation)
        );
        cardView.setRadius(
                getResources().getDimension(R.dimen.card_corner_radius)
        );
        cardView.setPreventCornerOverlap(true);
        cardView.setUseCompatPadding(true);

        return cardView;

    }

    private ImageView createImageView(Drawable titleDrawable) {

        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) getResources().getDimension(R.dimen.doll_card_width),
                (int) getResources().getDimension(R.dimen.doll_card_height)
        );
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(titleDrawable);

        return imageView;

    }

    private int getCardMargin() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;

        int imageViewWidthPx = (int) getResources().getDimensionPixelSize(R.dimen.doll_card_width);

        return (width - 2 * imageViewWidthPx) / 4;
    }

}