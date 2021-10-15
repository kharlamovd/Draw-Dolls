package com.brands.drawdolls.doll;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.brands.drawdolls.R;
import com.brands.drawdolls.extras.TinyDB;

import java.util.ArrayList;

public class DollsFactory {

    /*public final static Map<Integer, Integer> DOLLS_STATUS = new HashMap<>();
    public final static Map<Integer, Integer> STEPS_NUM_BY_DOLLS = new HashMap<>();*/

    public static ArrayList<Doll> dollList;

    public static void init(Context context) {

        TinyDB tinydb = new TinyDB(context);

        dollList = tinydb.getDollsList("dolls");

        if (dollList == null || dollList.size() == 0) {
            dollList = new ArrayList<>();
            createNewDollsList(context);
        }

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

    private static void createNewDollsList(Context context) {

        dollList.add(new Doll(0, context.getString(R.string.lol_pink), 12, true));
        dollList.add(new Doll(1, context.getString(R.string.lol_band), 10, false));
        dollList.add(new Doll(2, context.getString(R.string.lol_boots), 9, false));
        dollList.add(new Doll(3, context.getString(R.string.winx), 8, true));
        dollList.add(new Doll(4, context.getString(R.string.simple_doll), 4, false));
        dollList.add(new Doll(5, context.getString(R.string.girl_doll), 8, false));
        dollList.add(new Doll(6, context.getString(R.string.old_dress_doll), 7, false));
        dollList.add(new Doll(7, context.getString(R.string.ballerina), 7, false));

    }

}
