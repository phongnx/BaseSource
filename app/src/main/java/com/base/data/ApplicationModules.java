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
    private Context mContext;
    private PreferencesHelper mPreferencesHelper;
    private RealmHelper mRealmHelper;
    private Realm mRealm;
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

    public RealmHelper getRealmHelper() {
        return mRealmHelper;
    }

    public Realm getRealm() {
        return mRealm;
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
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .schemaVersion(1) // Must be bumped when the schema changes
//                .migration(new MyMigration()) // Migration to run instead of throwing an exception
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        mPreferencesHelper = new PreferencesHelper(context);
        mRealm = Realm.getDefaultInstance();
        mRealmHelper = new RealmHelper(context, mRealm);
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
