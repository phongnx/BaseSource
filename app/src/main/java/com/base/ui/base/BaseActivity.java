package com.base.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
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
import com.base.ui.base.subview.BaseSubView;
import com.base.ui.base.subview.LifeCycle;
import com.base.ui.base.subview.SubViewLifeCycleHelper;
import com.base.utils.Utils;
import com.utility.DebugLog;


/**
 * Created by Phong on 11/9/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView, BaseFragment.Callback {
    private MaterialDialog mProgressDialog;
    private MaterialDialog mAlertDialog;
    private SubViewLifeCycleHelper mSubViewLifeCycleHelper;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        try {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if ( v instanceof EditText) {
                    Rect outRect = new Rect();
                    v.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                        v.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        assert imm != null;
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
            return super.dispatchTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    public void attachSubView(BaseSubView baseSubView) {
        if (mSubViewLifeCycleHelper == null) {
            mSubViewLifeCycleHelper = new SubViewLifeCycleHelper();
        }
        mSubViewLifeCycleHelper.attach(baseSubView);
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
    protected void onStart() {
        super.onStart();
        updateLifeCycleForSubViews(LifeCycle.ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLifeCycleForSubViews(LifeCycle.ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateLifeCycleForSubViews(LifeCycle.ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateLifeCycleForSubViews(LifeCycle.ON_STOP);
    }

    @Override
    protected void onDestroy() {
        hideLoading();
        hideAlertDialog();
        mAlertDialog = null;
        mProgressDialog = null;
        updateLifeCycleForSubViews(LifeCycle.ON_DESTROY);
        super.onDestroy();
    }

    private void updateLifeCycleForSubViews(LifeCycle lifeCycle) {
        if (mSubViewLifeCycleHelper != null) {
            mSubViewLifeCycleHelper.onLifeCycle(lifeCycle);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
    }

}
