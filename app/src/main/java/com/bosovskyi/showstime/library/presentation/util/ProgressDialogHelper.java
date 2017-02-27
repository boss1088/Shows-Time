package com.bosovskyi.showstime.library.presentation.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.bosovskyi.showstime.R;

/**
 * Created by boss1088 on 2/17/17.
 */

public class ProgressDialogHelper {

    private Dialog dialog;

    private volatile int progressesCount = 0;

    public void showProgress(Context context) {
//        if (context == null) {
//            return;
//        }
//
//        if (!inProgress()) {
//            dialog = new Dialog(context);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.progress_layout);
//            dialog.setCancelable(false);
//            dialog.show();
//        }
//
//        progressesCount++;
    }

    public void hideProgress() {
        progressesCount--;
        if (progressesCount <= 0) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            progressesCount = 0;
        }
    }

    private boolean inProgress() {
        return dialog != null && dialog.isShowing();
    }
}
