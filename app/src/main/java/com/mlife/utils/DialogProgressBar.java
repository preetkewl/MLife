package com.mlife.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.mlife.R;


/**
 * Created by milagro on 6/27/2017.
 */

public class DialogProgressBar {

    private Dialog dialog;

    public void showProgressBar(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_progress_view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void hideProgressBar() {
        try {
            dialog.dismiss();
        }catch (Exception e){
        }
    }
}
