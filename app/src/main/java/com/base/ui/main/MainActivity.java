package com.base.ui.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.base.R;
import com.base.ui.base.BaseActivity;
import com.base.utils.CheckPermissions;
import com.base.utils.Utils;

import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_STORAGE_PERMISSIONS;

public class MainActivity extends BaseActivity implements MainMvpView, View.OnClickListener{
    private Context context;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        // Initialize Presenter
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);

        // --
        initView();
        initializeControls();
        checkPermissions();

        //
        fab.setOnClickListener(this);
    }

    // Check permission in android 6.0 and above
    public void checkPermissions() {
        if (CheckPermissions.getInstant().checkAccessStoragePermission(context)) {
            mainPresenter.initData();
        }
    }

    public void initView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initializeControls() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void closeMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void loginSuccess() {
        // Do somethings
        Utils.showToast(context, "Login success");
    }

    @Override
    public void loginFailed(String message) {
        Utils.showToast(context, message);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int grantResult = 0;
        try {
            grantResult = grantResults[0];
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), String.valueOf(e));
        }
        switch (requestCode) {
            case REQUEST_CODE_GRANT_STORAGE_PERMISSIONS:
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    mainPresenter.initData();
                } else {
                    // Permission Denied
                    Utils.showToast(context, getApplicationContext().getString(R.string.lbl_alert_storage_permission_denied));
                    finish();
                }
                break;

            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                mainPresenter.login("phongnx@gmail.com", "123456", "abc");
                break;

            default:
                break;
        }
    }
}
