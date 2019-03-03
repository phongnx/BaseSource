package com.base.ui.main;


import android.content.Context;

import com.base.data.remote.ApiResult;
import com.base.data.remote.helper.LoginHelper;
import com.base.ui.base.BasePresenter;
import com.utility.DebugLog;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Phong on 2/2/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    private CompositeDisposable mCompositeDisposable;

    MainPresenter(Context context) {
        super(context);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        super.detachView();
        mCompositeDisposable.clear();
    }

    @Override
    public void login(String email, String password) {
        DebugLog.loge("mContext: " + (mContext == null ? "NULL" : "NOT NULL"));
    }
}
