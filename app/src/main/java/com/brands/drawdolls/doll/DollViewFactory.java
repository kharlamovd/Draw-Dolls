package com.brands.drawdolls.doll;

import static com.brands.drawdolls.doll.DollsFactory.dollList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.brands.drawdolls.DollActivity;
import com.brands.drawdolls.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class DollViewFactory {

    private Context context;

    public DollViewFactory(Context context) {
        this.context = context;
    }

    public CardView createCardView() {

        CardView cardView = new CardView(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        cardView.setLayoutParams(layoutParams);
        cardView.setCardElevation(
                context.getResources().getDimension(R.dimen.card_elevation)
        );
        cardView.setRadius(
                context.getResources().getDimension(R.dimen.card_corner_radius)
        );
        cardView.setPreventCornerOverlap(true);
        cardView.setUseCompatPadding(true);

        return cardView;

    }

    public LinearLayout.LayoutParams setDollViewMargins(
            LinearLayout.LayoutParams layoutParams,
            Side side
    ) {

        int cardMargin = getCardMargin();

        if (side == Side.LEFT)
            layoutParams.setMargins(cardMargin, 0, 0, 0);
        else
            layoutParams.setMargins(0, 0, cardMargin, 0);

        return layoutParams;
    }

    public RelativeLayout createCardLayout() {

        RelativeLayout relativeLayout = new RelativeLayout(context);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        relativeLayout.setLayoutParams(layoutParams);

        return relativeLayout;
    }

    public ImageView createImageView(Drawable titleDrawable) {

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                (int) context.getResources().getDimension(R.dimen.doll_card_width),
                (int) context.getResources().getDimension(R.dimen.doll_card_height)
        );
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(titleDrawable);

        imageView.setId(View.generateViewId());

        return imageView;

    }

    public void setTitleImageOnClick(ImageView titleImage, int idx) {

        titleImage.setOnClickListener(view -> {

            Intent intent = new Intent(context, DollActivity.class);
            intent.putExtra("doll", dollList.get(idx));

            context.startActivity(intent);

        });


    }

    public ImageView createStatusImageView(DollStatus status, int mainImageId) {

        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        switch (status) {
            case NEW:
                imageView.setImageResource(R.drawable.state_new);
                break;
            case DONE:
                imageView.setImageResource(R.drawable.state_done);
                break;
            case IN_PROGRESS:
                imageView.setImageResource(R.drawable.state_inprog);
                break;
        }

        if (status != DollStatus.NONE) {
            rlp.addRule(RelativeLayout.BELOW, mainImageId);

            int marginTop = (int) context.getResources().getDimension(R.dimen.status_margin_top);
            int marginStart = (int) context.getResources().getDimension(R.dimen.status_margin_start);

            rlp.setMargins(marginStart, marginTop, 0, 0);
            imageView.setLayoutParams(rlp);
        }

        return imageView;

    }

    public ImageView createRewardLabel(int mainImageId) {

        ImageView rewardImageView = new ImageView(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        rewardImageView.setImageResource(R.drawable.ad_label);

        rlp.addRule(RelativeLayout.BELOW, mainImageId);
        int marginTop = (int) context.getResources().getDimension(R.dimen.reward_margin_top);
        int marginStart = (int) context.getResources().getDimension(R.dimen.status_margin_start);

        rlp.setMargins(marginStart, marginTop, 0, 0);
        rewardImageView.setLayoutParams(rlp);

        return rewardImageView;

    }

    private int getCardMargin() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;

        int imageViewWidthPx = (int) context.getResources().getDimensionPixelSize(R.dimen.doll_card_width);

        return (width - 2 * imageViewWidthPx) / 4;
    }

}
