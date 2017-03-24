package com.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.data.ApplicationModules;
import com.base.data.network.DataManager;
import com.base.data.local.preference.PreferencesHelper;
import com.base.data.network.RemoteApiService;
import com.base.utils.Utils;
import com.utility.DebugLog;

import retrofit2.Retrofit;


/**
 * Created by Phong on 11/9/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.DEBUG = Utils.isDebuggable();
        ApplicationModules.getInstant().initModules(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
