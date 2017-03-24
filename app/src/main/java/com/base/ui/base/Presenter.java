package com.base.ui.base;

/**
 * Created by Phong on 11/9/2016.
 */

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
