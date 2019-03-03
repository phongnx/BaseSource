package com.base;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.base.data.ApplicationModules;
import com.base.ui.main.MainActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.utility.DebugLog;
import com.utility.SharedPreference;

import androidx.multidex.MultiDexApplication;


/**
 * Created by Phong on 11/9/2016.
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.DEBUG = BuildConfig.DEBUG;
        ApplicationModules.getInstant().initModules(getApplicationContext());

        if (!BuildConfig.DEBUG) {
            initCrash();
        }
    }

    @SuppressLint("MissingPermission")
    private void initCrash() {
        CrashUtils.init((crashInfo, e) -> {
            DebugLog.loge(e);
            restartApp();
        });
    }

    private void restartApp() {
        if (!shouldAutoRestartApp()) {
            SharedPreference.setInt(this, AUTO_RESTART, 0);
            killApp();
            return;
        }
        setFlagAutoRestartApp();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restartIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        if (manager == null) return;
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 1, restartIntent);
        killApp();
    }

    private void killApp() {
        ActivityUtils.finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void setFlagAutoRestartApp() {
        int currentCount = SharedPreference.getInt(this, AUTO_RESTART, 0);
        SharedPreference.setInt(this, AUTO_RESTART, currentCount + 1);
    }

    private static final String AUTO_RESTART = "AUTO_RESTART";

    private boolean shouldAutoRestartApp() {
        return SharedPreference.getInt(this, AUTO_RESTART, 0) < 3;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
