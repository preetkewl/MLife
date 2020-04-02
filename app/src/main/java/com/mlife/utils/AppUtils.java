package com.mlife.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mlife.activities.ActivityLoginWithOTP;

public class AppUtils {


    public static void goToLogin(Context context){


        context.startActivity(new Intent(context, ActivityLoginWithOTP.class));
        ((Activity)context).finishAffinity();

    }

}
