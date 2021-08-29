package com.brands.drawdolls.doll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.brands.drawdolls.extras.TinyDB;

import java.util.ArrayList;

public class DollsFactory {

    /*public final static Map<Integer, Integer> DOLLS_STATUS = new HashMap<>();
    public final static Map<Integer, Integer> STEPS_NUM_BY_DOLLS = new HashMap<>();*/

    public static ArrayList<Doll> dollList;
    public static final String DOLL_NAME = "doll";

    public static void init(Context context) {

        TinyDB tinydb = new TinyDB(context);

        dollList = tinydb.getDollsList("dolls");

        if (dollList == null || dollList.size() == 0)
            createNewDollsList();

    }

    public static void saveDolls(Context context) {

        TinyDB tinydb = new TinyDB(context);

        tinydb.putDollList("dolls", dollList);

    }

    public void saveDollWithStatus(int dollId, int step, DollStatus status) {

        Doll doll = dollList.get(dollId);
        doll.setStatus(status);
        doll.setCurrentStep(step);

    }

    public static Drawable getDollDrawable(int dollId, int step, Context context) {

        Resources resources = context.getResources();
        String name = DOLL_NAME + (dollId + 1) + "_" + step;
        final int resourceId = resources.getIdentifier(
                name,
                "drawable",
                context.getPackageName()
        );

        return resources.getDrawable(resourceId);

    }

    public static Drawable getDollTitleDrawable(int dollId, Context context) {

        int step = dollList.get(dollId).getStepsNum();
        return getDollDrawable(dollId, step, context);

    }

    public static Integer getStepsNumByDoll(int dollNum) {
        return dollList.get(dollNum).getStepsNum();
    }

    private static void createNewDollsList() {

        dollList.add(new Doll(0, 12));
        dollList.add(new Doll(1, 10));
        dollList.add(new Doll(2, 9));
        dollList.add(new Doll(3, 8));
        dollList.add(new Doll(4, 4));
        dollList.add(new Doll(5, 8));
        dollList.add(new Doll(6, 7));
        dollList.add(new Doll(7, 7));

    }

}
