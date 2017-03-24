package com.base.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.base.BuildConfig;
import com.base.R;

import java.util.Random;


/**
 * Created by Phong on 11/9/2016.
 */

public class Utils {
    private static ProgressDialog progressDialog;

    public static void showProgress(Context context, String message) {
        dismissCurrentDialog();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissCurrentDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.layout_progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static ProgressDialog showLoadingDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    public static MaterialDialog createAlertDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .canceledOnTouchOutside(true)
                .positiveText(context.getString(R.string.lbl_ok))
                .build();
    }

    public static void showToast(Context context, String message) {
        if (!message.isEmpty()) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(17, 0, 230);
            toast.show();
        }
    }

    public static boolean isDebuggable() {
        boolean debuggable = false;
        try {
            debuggable = BuildConfig.DEBUG;
        } catch (Exception e) {
        }
        return debuggable;
    }

    @NonNull
    public static String getRandomId() {
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        long currentTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder(String.valueOf(currentTime));
        builder.append("_");
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            builder.append(possible.charAt(random.nextInt(possible.length())));
        }
        return builder.toString();
    }

}
