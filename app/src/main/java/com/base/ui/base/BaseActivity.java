package com.base.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.base.R;
import com.base.utils.Utils;
import com.utility.DebugLog;


/**
 * Created by Phong on 11/9/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView, BaseFragment.Callback {
    private MaterialDialog mProgressDialog;
    private MaterialDialog mAlertDialog;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
            }
        }
        return ret;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        createAlertDialog();
    }

    private void createAlertDialog() {
        mAlertDialog = Utils.createAlertDialog(this);
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
    }

    @Override
    public void showLoading() {
        hideLoading();
        try {
            mProgressDialog = new MaterialDialog.Builder(this)
                    .content(R.string.lbl_please_wait)
                    .progress(true, 0)
                    .show();
        } catch (Exception e) {
            DebugLog.loge(e);
        }
    }

    @Override
    public void showLoading(String message) {
        hideLoading();
        try {
            mProgressDialog = new MaterialDialog.Builder(this)
                    .content(message)
                    .progress(true, 0)
                    .show();
        } catch (Exception e) {
            DebugLog.loge(e);
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showAlertDialog(String message) {
        hideAlertDialog();
        if (message == null || message.trim().isEmpty()) {
            return;
        }
        mAlertDialog.setContent(message);
        mAlertDialog.show();
    }

    @Override
    public void hideAlertDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        hideAlertDialog();
        mAlertDialog = null;
        mProgressDialog = null;
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
    }
}
