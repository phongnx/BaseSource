package com.base.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.base.data.local.preference.PreferenceKeys.USER_ID;

/**
 * Created by Phong on 11/9/2016.
 */

public class PreferencesHelper {
    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveUserId(String user_id){
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(USER_ID, user_id);
        e.commit();
    }
}
