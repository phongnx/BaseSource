package com.base.data;

import android.content.Context;

import com.base.data.local.preference.PreferencesHelper;
import com.base.data.remote.DataManager;
import com.base.data.remote.RemoteApiService;

import retrofit2.Retrofit;

/**
 * Created by Phong on 3/1/2017.
 */

public class ApplicationModules {
    private static ApplicationModules applicationModules;
    private Context mContext;
    private PreferencesHelper mPreferencesHelper;
    private DataManager mDataManager;

    public static ApplicationModules getInstant() {
        if (applicationModules == null) {
            applicationModules = new ApplicationModules();
        }
        return applicationModules;
    }

    public Context getContext() {
        return mContext;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    /*
    * TODO
    *Initialize modules for app
    */

    public void initModules(Context context) {
        mContext = context;

        mPreferencesHelper = new PreferencesHelper(context);
        mDataManager = provideDataManager(mPreferencesHelper);
    }

    private DataManager provideDataManager(PreferencesHelper preferencesHelper) {
        return new DataManager(
                provideRemoteApiService(),
                preferencesHelper
        );
    }

    private RemoteApiService provideRemoteApiService() {
        return provideRemoteApiServiceInstance().create(RemoteApiService.class);
    }

    private Retrofit provideRemoteApiServiceInstance() {
        return RemoteApiService.Creator.newRetrofitInstance();
    }

}
