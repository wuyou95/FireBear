package com.wuqi.dev.sqlite.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Risky57 on 2017/4/13.
 */

public class BearApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate () {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext () {
        return sContext;
    }
}
