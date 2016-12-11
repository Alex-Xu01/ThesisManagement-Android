package edu.gisi.magic.thesismanagement;

import android.app.Application;
import android.content.Context;

/**
 * Created by AlexXu on 2016/12/10.
 */

public class App extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

}

