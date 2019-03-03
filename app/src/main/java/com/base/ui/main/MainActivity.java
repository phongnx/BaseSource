package com.base.ui.main;

import android.content.Context;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import com.base.R;
import com.base.ui.base.BaseActivity;
import com.base.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainMvpView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Context mContext;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        // Initialize Presenter
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);

        // --
        initView();
    }

    public void initView() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void closeMenu() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void loginSuccess() {
        // Do somethings
        Utils.showToast(mContext, "Login success");
    }

    @Override
    public void loginFailed(String message) {
        Utils.showToast(mContext, message);
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

}
