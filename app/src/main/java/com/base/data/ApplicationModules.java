package com.base.data;

import android.content.Context;

import com.base.data.local.preference.PreferencesHelper;
import com.base.data.local.realm.RealmHelper;
import com.base.data.network.DataManager;
import com.base.data.network.RemoteApiService;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

/**
 * Created by Phong on 3/1/2017.
 */

public class ApplicationModules {
    private static ApplicationModules applicationModules;
    private PreferencesHelper preferencesHelper;
    private RealmHelper realmHelper;
    private Realm realm;
    private DataManager dataManager;

    public static ApplicationModules getInstant() {
        if (applicationModules == null) {
            applicationModules = new ApplicationModules();
        }
        return applicationModules;
    }

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    public RealmHelper getRealmHelper() {
        return realmHelper;
    }

    public Realm getRealm() {
        return realm;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    /*
    * TODO
    *Initialize modules for app
    */

    public void initModules(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        preferencesHelper = providePreferencesHelper(context);
        realm = provideRealm();
        realmHelper = provideRealmHelper(context, realm);
        dataManager = provideDataManager(preferencesHelper);
    }

    private Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    private RealmHelper provideRealmHelper(Context context, Realm realm) {
        return new RealmHelper(context, realm);
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

    private PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }
}
