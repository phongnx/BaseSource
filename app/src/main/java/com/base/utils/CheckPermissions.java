package com.base.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_BLUETOOTH_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_CAMERA_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_LOCATION_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_MICRO_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_STORAGE_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_READ_CONTACT_PERMISSIONS;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_SETTINGS_LOCATION;
import static com.base.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_WRITE_CONTACT_PERMISSIONS;


/**
 * Created by Phong on 12/13/2016.
 */

public class CheckPermissions {

    private static CheckPermissions checkPermissions;

    public interface RequestCodePermission {
        int REQUEST_CODE_WRITE_CONTACT_PERMISSIONS = 1000;
        int REQUEST_CODE_READ_CONTACT_PERMISSIONS = 1001;
        int REQUEST_CODE_GRANT_OVERLAY_PERMISSIONS = 1002;
        int REQUEST_CODE_GRANT_STORAGE_PERMISSIONS = 1003;
        int REQUEST_CODE_GRANT_LOCATION_PERMISSIONS = 1004;
        int REQUEST_CODE_SETTINGS_LOCATION = 1005;
        int REQUEST_CODE_GRANT_BLUETOOTH_PERMISSIONS = 1006;
        int REQUEST_CODE_GRANT_CAMERA_PERMISSIONS = 1007;
        int REQUEST_CODE_GRANT_MICRO_PERMISSIONS = 1008;
    }

    public static CheckPermissions getInstant() {
        if (checkPermissions == null) {
            checkPermissions = new CheckPermissions();
        }
        return checkPermissions;
    }

    /*
    * Check and Request storage permission
    *
    * <uses-permission android:name="android.permission.READ_CONTACTS" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessReadContactPermission(Context context) {
        int hasAccessReadContactPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
        if (hasAccessReadContactPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACT_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check and Request storage permission
    *
    * <uses-permission android:name="android.permission.WRITE_CONTACTS" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessWriteContactPermission(Context context) {
        int hasAccessWriteContactPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS);
        if (hasAccessWriteContactPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_WRITE_CONTACT_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check and Request storage permission
    *
    * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessStoragePermission(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        int hasAccessWriteStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasAccessReadStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasAccessWriteStoragePermission != PackageManager.PERMISSION_GRANTED || hasAccessReadStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GRANT_STORAGE_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check and Request location permission
    *
    * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessLocationPermission(Context context) {
        int hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED || hasAccessCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_GRANT_LOCATION_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Request location service on/off
    * */
    public void requestLocationTurnOn(Context context) {
        try {
            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            ((Activity) context).startActivityForResult(myIntent, REQUEST_CODE_SETTINGS_LOCATION);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), String.valueOf(e));
        }
    }

    /*
    * Check and Request bluetooth permission
    *
    * <uses-permission android:name="android.permission.BLUETOOTH" />
    * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessBluetoothPermission(Context context) {
        int hasAccessBluetoothPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        int hasAccessBluetoothAdminPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN);
        if (hasAccessBluetoothPermission != PackageManager.PERMISSION_GRANTED || hasAccessBluetoothAdminPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_CODE_GRANT_BLUETOOTH_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check and request camera permission
    *
    * <uses-permission android:name="android.permission.CAMERA" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessCameraPermission(Context context) {
        int resultCamera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (resultCamera != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_GRANT_CAMERA_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check and request micro permission
    *
    * <uses-permission android:name="android.permission.RECORD_AUDIO" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkAccessMicroPermission(Context context) {
        int resultMicro = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        if (resultMicro != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_GRANT_MICRO_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Check push notification enable or disable
    * */
    public boolean checkEnablePushNotification(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                return false;
            }
        }
        return true;
    }

    /*
    * Request push notification on/off
    * */
    public void requestPushNotificationOn(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), String.valueOf(e));
        }
    }

    /*
    * Check overlay permission
    *
    * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    * */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkOverlayPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)) {
                return false;
            }
        }
        return true;
    }

    public void requestOverlayPermission(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
            ((Activity) context).startActivityForResult(intent, RequestCodePermission.REQUEST_CODE_GRANT_OVERLAY_PERMISSIONS);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), String.valueOf(e));
        }
    }
}
