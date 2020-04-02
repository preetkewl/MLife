package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.R;
import com.mlife.utils.AppUtils;
import com.mlife.utils.MahindraClappPreference;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityWelcome extends AppCompatActivity {

    MahindraClappPreference mahindraClappPreference;


    @OnClick(R.id.btn_skipWelcome)
    public void skipWelcome() {
        mahindraClappPreference.setShowWelcome(false);


//  New Login Requriments changes as on 21 March 2020 - Chnage In Login Screen
//        startActivity(new Intent(this, ActivityLogin.class));
//        finish();
        AppUtils.goToLogin(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Welcome Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Welcome Screen Android", null);
        mFirebaseAnalytics.logEvent("Welcome_Screen_Android", params);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "Welcome");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
    }
}
