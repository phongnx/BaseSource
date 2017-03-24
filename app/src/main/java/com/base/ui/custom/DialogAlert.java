package com.base.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.base.R;


/**
 * Created by Phong on 10/20/2016.
 */

public final class DialogAlert {
    private static final String TAG = "DialogAlert";
    public Context context;
    public Dialog dialog;
    public String title = "";
    public String message = "";
    public String negativeText = "";
    public String positiveText = "";
    public int colorButton = 0;
    public int colorTitle = 0;
    public int colorMessage = 0;
    public boolean cancelable = true;
    public boolean dialogTypeSystemWindow = false;

    public Runnable onNegativeListener;
    public Runnable onPositiveListener;
    public Runnable onDismissListener;
    // --
    private TextView tvTitle, tvMessage;
    private TextView btnNegative, btnPositive;

    public DialogAlert(Context context) {
        this.context = context;
        setDefaultValue();
    }

    private void setDefaultValue() {
        if (context == null) {
            return;
        }
        colorButton = Color.parseColor("#3F51B5");
        colorTitle = Color.parseColor("#00000");
        colorMessage = Color.parseColor("#B2000000");
    }

    public DialogAlert create() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(cancelable);
        dialog.setContentView(R.layout.dialog_alert);

        // Initialize views
        tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);
        btnNegative = (TextView) dialog.findViewById(R.id.btnNegative);
        btnPositive = (TextView) dialog.findViewById(R.id.btnPositive);

        // Set text
        tvTitle.setText(title);
        tvMessage.setText(message);
        btnNegative.setText(negativeText);
        btnPositive.setText(positiveText);
        // Set color
        tvTitle.setTextColor(colorTitle);
        tvMessage.setTextColor(colorMessage);
        btnNegative.setTextColor(colorButton);
        btnPositive.setTextColor(colorButton);
        // Set
        if (title.isEmpty()) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }
        if (message.isEmpty()) {
            tvMessage.setVisibility(View.GONE);
        } else {
            tvMessage.setVisibility(View.VISIBLE);
        }
        if (negativeText.isEmpty()) {
            btnNegative.setVisibility(View.GONE);
        } else {
            btnNegative.setVisibility(View.VISIBLE);
        }
        if (positiveText.isEmpty()) {
            btnPositive.setVisibility(View.GONE);
        } else {
            btnPositive.setVisibility(View.VISIBLE);
        }
        // Set action for button
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.run();
                }
                dialog.dismiss();
                dialog = null;
            }
        });
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.run();
                }
                dialog.dismiss();
                dialog = null;
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (onDismissListener != null) {
                    onDismissListener.run();
                }
            }
        });
        // Set type system window for dialog (always on top)
        if (dialogTypeSystemWindow) {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        return this;
    }

    public void show() {
        try {
            if (dialog == null) {
                return;
            }
            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, String.valueOf(e));
        }
    }

    public static class Builder {
        private DialogAlert dialogAlert;

        public Builder(Context context) {
            dialogAlert = new DialogAlert(context);
        }

        public Builder title(String title) {
            dialogAlert.title = title;
            return this;
        }

        public Builder message(String message) {
            dialogAlert.message = message;
            return this;
        }

        public Builder negativeText(String textCancel) {
            dialogAlert.negativeText = textCancel;
            return this;
        }

        public Builder positiveText(String textOK) {
            dialogAlert.positiveText = textOK;
            return this;
        }

        public Builder colorButton(int color) {
            dialogAlert.colorButton = color;
            return this;
        }

        public Builder colorTitle(int color) {
            dialogAlert.colorTitle = color;
            return this;
        }

        public Builder colorMessage(int color) {
            dialogAlert.colorMessage = color;
            return this;
        }

        public Builder dialogTypeSystemWindow(boolean typeSystemWindow) {
            dialogAlert.dialogTypeSystemWindow = typeSystemWindow;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            dialogAlert.cancelable = cancelable;
            return this;
        }

        public Builder onNegativeListener(Runnable onActionCancel) {
            dialogAlert.onNegativeListener = onActionCancel;
            return this;
        }

        public Builder onPositiveListener(Runnable onActionOK) {
            dialogAlert.onPositiveListener = onActionOK;
            return this;
        }

        public Builder onDismissDialogListener(Runnable onDismissListener) {
            dialogAlert.onDismissListener = onDismissListener;
            return this;
        }

        public DialogAlert build() {
            return dialogAlert;
        }
    }

}
