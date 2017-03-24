package com.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.data.ApplicationModules;
import com.base.data.DataManager;
import com.base.data.local.preference.PreferencesHelper;
import com.base.data.remote.RemoteApiService;

import retrofit2.Retrofit;


/**
 * Created by Phong on 11/9/2016.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initModules() {
        PreferencesHelper preferencesHelper = providePreferencesHelper(this);
        DataManager dataManager = provideDataManager(preferencesHelper);
        ApplicationModules.getInstant().setDataManager(dataManager);
        ApplicationModules.getInstant().setPreferencesHelper(preferencesHelper);
    }

    private DataManager provideDataManager(PreferencesHelper preferencesHelper) {
        return new DataManager(provideRemoteApiService(provideRetrofitInstance()), preferencesHelper);
    }

    private RemoteApiService provideRemoteApiService(Retrofit retrofit) {
        return retrofit.create(RemoteApiService.class);
    }

    private Retrofit provideRetrofitInstance() {
        return RemoteApiService.Creator.newRetrofitInstance();
    }

    private PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }

}
