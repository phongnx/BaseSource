package com.base.ui.main.presenter;


import com.base.data.models.ApiResult;
import com.base.ui.base.BasePresenter;
import com.base.ui.main.models.LoginHelper;
import com.base.ui.main.view.MainMvpView;

/**
 * Created by Phong on 2/2/2017.
 */

public class MainPresenter extends BasePresenter<MainMvpView> implements ApiResult {
    private LoginHelper loginHelper;

    public MainPresenter() {
        loginHelper = new LoginHelper(this);
    }

    public void initData() {

    }

    public void login(String email, String password, String push_key) {
        loginHelper.login(email, password, push_key);
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
