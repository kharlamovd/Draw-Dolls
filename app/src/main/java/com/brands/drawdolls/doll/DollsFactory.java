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

    private static void createNewDollsList() {

        dollList.add(new Doll(0, R.string.lol_pink, 12));
        dollList.add(new Doll(1, R.string.lol_band, 10));
        dollList.add(new Doll(2, R.string.lol_boots, 9));
        dollList.add(new Doll(3, R.string.winx, 8));
        dollList.add(new Doll(4, R.string.simple_doll, 4));
        dollList.add(new Doll(5, R.string.girl_doll, 8));
        dollList.add(new Doll(6, R.string.old_dress_doll, 7));
        dollList.add(new Doll(7, R.string.ballerina, 7));

    }

}
