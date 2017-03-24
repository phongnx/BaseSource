package com.base.ui.main.view;


import com.base.ui.base.BaseMvpView;

/**
 * Created by Phong on 2/2/2017.
 */

public interface MainMvpView extends BaseMvpView {

    void loginSuccess();

    void loginFailed(String message);

}
