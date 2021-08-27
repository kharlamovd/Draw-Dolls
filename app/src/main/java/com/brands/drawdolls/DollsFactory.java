package com.brands.drawdolls;

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

        if (dollList == null) {

            dollList.add(new Doll(1, 12));
            dollList.add(new Doll(2, 10));
            dollList.add(new Doll(3, 9));
            dollList.add(new Doll(4, 8));
            dollList.add(new Doll(5, 4));
            dollList.add(new Doll(6, 8));
            dollList.add(new Doll(7, 7));
            dollList.add(new Doll(8, 7));

        }

    }

    public static void saveDolls(Context context) {

        TinyDB tinydb = new TinyDB(context);

        tinydb.putDollList("dolls", dollList);

    }

    public void saveDollWithStatus(int dollNum, int step, DollStatus status) {

        Doll doll = dollList.get(dollNum);
        doll.setStatus(status);
        doll.setCurrentStep(step);

    }

    public static Drawable getDollDrawable(int dollNum, int step, Context context) {

        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(
                DOLL_NAME + dollNum + "_" + step,
                "drawable",
                context.getPackageName()
        );

        return resources.getDrawable(resourceId);

    }

    public static Integer getStepsNumByDoll(int dollNum) {
        return dollList.get(dollNum).getStepsNum();
    }

}
