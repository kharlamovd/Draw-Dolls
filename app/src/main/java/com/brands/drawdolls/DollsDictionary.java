package com.brands.drawdolls;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class DollsDictionary {

    //public final static Map<Integer, Integer> DOLLS = new HashMap<>();
    public final static Map<Integer, Integer> STEPS_NUM_BY_DOLLS = new HashMap<>();

    public static void init(Context context) {

        STEPS_NUM_BY_DOLLS.put(1, 12);
        STEPS_NUM_BY_DOLLS.put(2, 10);
        STEPS_NUM_BY_DOLLS.put(3, 10);
        STEPS_NUM_BY_DOLLS.put(4, 8);
        STEPS_NUM_BY_DOLLS.put(5, 4);
        STEPS_NUM_BY_DOLLS.put(6, 8);
        STEPS_NUM_BY_DOLLS.put(7, 7);
        STEPS_NUM_BY_DOLLS.put(8, 7);

    }

}
