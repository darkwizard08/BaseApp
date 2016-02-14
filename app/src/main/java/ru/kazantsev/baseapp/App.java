package ru.kazantsev.baseapp;

import android.app.Application;
import android.content.res.Configuration;
import ru.kazantsev.baseapp.domain.Constants;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Rufim on 03.07.2015.
 */
public class App extends Application {

    private static App singleton;

    public static App getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.Assets.ROBOTO_FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
