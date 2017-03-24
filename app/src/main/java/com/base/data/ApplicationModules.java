package com.base.data;

import com.base.data.local.preference.PreferencesHelper;

/**
 * Created by Phong on 3/1/2017.
 */

public class ApplicationModules {
    private static ApplicationModules applicationModules;
    private PreferencesHelper preferencesHelper;
    private DataManager dataManager;

    public static ApplicationModules getInstant(){
        if (applicationModules == null) {
            applicationModules = new ApplicationModules();
        }
        return applicationModules;
    }

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    public void setPreferencesHelper(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
