package com.base.ui.main;


import com.base.data.remote.ApiResult;
import com.base.data.remote.helper.LoginHelper;
import com.base.ui.base.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Phong on 2/2/2017.
 */

public class MainPresenter extends BasePresenter<MainMvpView> implements ApiResult {
    private LoginHelper mLoginHelper;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter() {
        mCompositeDisposable = new CompositeDisposable();
        mLoginHelper = new LoginHelper(mCompositeDisposable, this);
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
        super.detachView();
    }

    public void initData() {

    }

    public void login(String email, String password, String push_key) {
        mLoginHelper.login(email, password, push_key);
    }

    /*
    * ApiResult Callback
    * */
    @Override
    public void onSuccess(String result) {
        getMvpView().loginSuccess();
    }

    /*
    * ApiResult Callback
    * */
    @Override
    public void onError(String error) {
        getMvpView().loginFailed(error);
    }
}
