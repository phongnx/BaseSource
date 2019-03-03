package com.base.ui.base;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Phong on 11/9/2016.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mvpView;
    protected Context mContext;
    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mvpView = null;
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected boolean isViewAttached() {
        return mvpView != null;
    }

    protected void showLoading() {
        if (isViewAttached()) {
            ((BaseMvpView) mvpView).showLoading();
        }
    }

    protected void showLoading(String message) {
        if (isViewAttached()) {
            ((BaseMvpView) mvpView).showLoading(message);
        }
    }

    protected void hideLoading() {
        if (isViewAttached()) {
            ((BaseMvpView) mvpView).hideLoading();
        }
    }

    protected void showAlertDialog(String message) {
        if (isViewAttached()) {
            ((BaseMvpView) mvpView).showAlertDialog(message);
        }
    }

    protected void hideAlertDialog() {
        if (isViewAttached()) {
            ((BaseMvpView) mvpView).hideAlertDialog();
        }
    }


    public V getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call MvpPresenter.attachView(MvpView) before requesting data to presenter");
        }
    }
}
