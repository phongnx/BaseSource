package com.base.data.local.realm;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by Phong on 3/24/2017.
 */

public class RealmHelper {
    private final Context context;
    private final Realm realm;

    public RealmHelper(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }
}
