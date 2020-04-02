package com.mlife.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.R;
import com.mlife.utils.AppUtils;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAppVersion;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ActivitySplash extends AppCompatActivity implements Observer, DataHolder {

    String version;
    Activity activity;
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.iv_splashLogo)
    ImageView iv_Logo;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getBaseContext());
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Splash Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Splash Screen Android", null);
        mFirebaseAnalytics.logEvent("Splash_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        activity = ActivitySplash.this;
        objectAppVersion.addObserver(this);

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        iv_Logo.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkUser();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
    }

    public void checkUser() {

        if (mahindraClappPreference.getShowWelcome()) {
            startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));
            finish();
        } else if (mahindraClappPreference.getData("Email").equals("")) {

            //  New Login Requriments changes as on 21 March 2020 - Chnage In Login Screen
//                    startActivity(new Intent(this, ActivityLogin.class));
//                    finish();
            AppUtils.goToLogin(this);

        } else {
            if (mahindraClappPreference.getData("Property").equals("")) {
                startActivity(new Intent(ActivitySplash.this, ActivityChangeProperty.class));
                finish();
            } else {
                startActivity(new Intent(ActivitySplash.this, ActivityHome.class));
                finish();
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ObjectAppVersion) {
            String ver = objectAppVersion.getAppVersion().getVal();
            if (ver.equals(version)) {
                checkUser();
            } else {
                checkUser();
            }
        }
        observable.deleteObservers();
    }

    private class GetKeys extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            String hostname = Constants.baseUrl;

            CertificatePinner certificatePinner = new CertificatePinner.Builder().add(hostname, "sha1/BOGUSPIN").build();

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().certificatePinner(certificatePinner).build();

            Request request = new Request.Builder().url(hostname).build();

            try {
                okHttpClient.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}