package com.brands.drawdolls;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

public class DollsDictionary {

    //public final static Map<Integer, Integer> DOLLS = new HashMap<>();
    public final static Map<Integer, Integer> STEPS_NUM_BY_DOLLS = new HashMap<>();

    public static final String DOLL_NAME = "doll";

    public static void init(Context context) {

        STEPS_NUM_BY_DOLLS.put(1, 12);
        STEPS_NUM_BY_DOLLS.put(2, 10);
        STEPS_NUM_BY_DOLLS.put(3, 9);
        STEPS_NUM_BY_DOLLS.put(4, 8);
        STEPS_NUM_BY_DOLLS.put(5, 4);
        STEPS_NUM_BY_DOLLS.put(6, 8);
        STEPS_NUM_BY_DOLLS.put(7, 7);
        STEPS_NUM_BY_DOLLS.put(8, 7);

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
        return STEPS_NUM_BY_DOLLS.get(dollNum);
    }

}
