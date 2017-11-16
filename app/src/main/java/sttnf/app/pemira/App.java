package sttnf.app.pemira;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class App extends Application {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = this;
        MultiDex.install(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public static Context getContext() {
        return context;
    }
}
