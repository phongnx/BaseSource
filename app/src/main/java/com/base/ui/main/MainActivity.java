package com.base.ui.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.base.R;
import com.base.ui.base.BaseActivity;
import com.base.utils.Utils;
import com.utility.RuntimePermissions;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainMvpView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Context context;

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
        checkPermissions();
    }

    // Check permission in android 6.0 and above
    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Check some permissions in here
        }
        mainPresenter.initData();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void closeMenu() {
        drawerLayout.closeDrawer(GravityCompat.START);
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

    @OnClick(R.id.fab)
    void login() {
        mainPresenter.login("phongnx@gmail.com", "123456", "abc");
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
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
        switch (requestCode) {
            case RuntimePermissions.RequestCodePermission.REQUEST_CODE_GRANT_STORAGE_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                } else {
                    // Permission Denied
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
