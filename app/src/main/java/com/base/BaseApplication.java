package com.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.data.ApplicationModules;
import com.utility.DebugLog;


/**
 * Created by Phong on 11/9/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.DEBUG = BuildConfig.DEBUG;
        ApplicationModules.getInstant().initModules(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
