package com.mlife.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.focpc.mahindralifespaces.utils.SharedPreferencesHelper;
import com.mlife.activities.ActivityLogin;
import com.mlife.R;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectLogout;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by milagro on 4/25/2017.
 */

public class DilogLogout implements DataHolder, Observer {

    Activity activ;
    Button btn_Yes, btn_No;
    DialogProgressBar progressBar = new DialogProgressBar();
    private MahindraClappPreference mahindraClappPreference;

    public void Dialogbox(final Activity activity) {

        final Dialog dialog = new Dialog(activity);
        this.activ = activity;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_logout_user);
        dialog.setCanceledOnTouchOutside(false);
        mahindraClappPreference = MahindraClappPreference.getInstance(activity);
        objectLogout.addObserver(this);
        dialog.show();

        btn_Yes = (Button) dialog.findViewById(R.id.btn_Logout_Yes);
        btn_No = (Button) dialog.findViewById(R.id.btn_Logout_No);

        btn_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.showProgressBar(activ);
                new Service().logout(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
            }
        });


        btn_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    public void update(Observable observable, Object o) {

        if (observable instanceof ObjectLogout) {
            progressBar.hideProgressBar();
            if (objectLogout.getLogoutResponse().getSuccess()) {
                mahindraClappPreference.clearData();
//                Logout of loyalie module
                SharedPreferencesHelper.clearPreferences(activ);
                activ.startActivity(new Intent(activ, ActivityLoginWithOTP.class));
                activ.finishAffinity();
            } else {
                Toast.makeText(activ, objectLogout.getLogoutResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}