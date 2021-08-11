package com.brands.drawdolls;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class DrawDollsApp extends Application {

    private static final String API_KEY = "";

    @Override
    public void onCreate() {
        super.onCreate();
        // Creating an extended library configuration.
        /*YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_KEY).build();
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(getApplicationContext(), config);
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this);*/
    }

}
