package com.base.ui.main;

import com.base.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void login(String email, String password);
}
