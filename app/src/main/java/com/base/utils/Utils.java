package com.base.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.base.R;
import com.base.ui.base.glide.GlideApp;
import com.bumptech.glide.Glide;

import java.util.Random;


/**
 * Created by Phong on 11/9/2016.
 */

public class Utils {
    private static MaterialDialog progressDialog;

    public static void showProgress(Context context, String message) {
        dismissCurrentDialog();
        progressDialog = new MaterialDialog.Builder(context)
                .content((message == null || message.isEmpty()) ? context.getString(R.string.lbl_please_wait) : message)
                .progress(true, 0)
                .show();
    }

    public static void dismissCurrentDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
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

    public static void loadImageWithGlide(Context context, Object model, int place_holder, ImageView target) {
        if (model == null || context == null) {
            return;
        }
        GlideApp.with(context)
                .load(model)
                .placeholder(place_holder)
                .error(place_holder)
                .centerCrop()
                .into(target);
    }

}
