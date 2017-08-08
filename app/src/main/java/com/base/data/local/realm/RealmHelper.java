package com.base.data.local.realm;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by Phong on 3/24/2017.
 */

public class RealmHelper {
    private final Context mContext;
    private final Realm mRealm;

    public RealmHelper(Context context, Realm realm) {
        this.mContext = context;
        this.mRealm = realm;
    }
}
