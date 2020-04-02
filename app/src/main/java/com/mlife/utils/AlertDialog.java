package com.mlife.utils;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * Created by milagro on 7/31/2017.
 */

public class AlertDialog {

    android.app.AlertDialog.Builder dialog;

    public  void AlertDialogWithMessage(Activity activity, String message, String title)
    {
        dialog=new android.app.AlertDialog.Builder(activity);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public  void AlertDialogFinishActivity(final Activity activity,String message,String title)
    {
        dialog=new android.app.AlertDialog.Builder(activity);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                activity.finish();
            }
        });
        dialog.show();
    }
}
