package com.mlife.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.fragments.Fragment_Enquiry_Close;
import com.mlife.fragments.Fragment_Enquiry_Open;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogRequestCallback;
import com.mlife.web.holder.DataHolder;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mlife.utils.Constants.postSales;

public class ActivityMyRequestListing extends AppCompatActivity implements Observer, DataHolder {

    DialogRequestCallback requestCallback = new DialogRequestCallback();
    public static String userType;
    MahindraClappPreference mahindraClappPreference;

    @OnClick(R.id.fab)
    public void onClickDrop() {
        if (userType.equals(postSales)) {
            startActivity(new Intent(ActivityMyRequestListing.this, ActivityMyRequestNewTicket.class).putExtra("type", "1"));
        } else {
            startActivity(new Intent(ActivityMyRequestListing.this, ActivityMyRequestNewTicket.class).putExtra("type", "2"));
        }
    }

    @BindView(R.id.ll_Outer)
    LinearLayout ll_Outer;

    @OnClick(R.id.fabcallback)
    public void onClickCallBack() {requestCallback.Dialogbox(this);}

    @BindView(R.id.tv_OpenTicket)
    TextView tvopenTickek;

    @BindView(R.id.tv_CloseTicket)
    TextView tvcloseTickek;

    @OnClick(R.id.tv_OpenTicket)
    public void changeColorOpen() {
        tvopenTickek.setTextColor(Color.parseColor("#ffffff"));
        tvopenTickek.setBackgroundColor(Color.parseColor("#cc0033"));
        tvcloseTickek.setTextColor(Color.parseColor("#cc0033"));
        tvcloseTickek.setBackgroundColor(Color.parseColor("#ffffff"));

        tvcloseTickek.setBackgroundResource(R.drawable.shape_box);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameEnq, new Fragment_Enquiry_Open());
        transaction.commit();
    }

    @OnClick(R.id.tv_CloseTicket)
    public void changeColorClose() {
        tvcloseTickek.setTextColor(Color.parseColor("#ffffff"));
        tvcloseTickek.setBackgroundColor(Color.parseColor("#cc0033"));
        tvopenTickek.setTextColor(Color.parseColor("#cc0033"));
        tvopenTickek.setBackgroundColor(Color.parseColor("#ffffff"));


        tvopenTickek.setBackgroundResource(R.drawable.shape_box);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameEnq, new Fragment_Enquiry_Close());
        transaction.commit();

    }

    @BindView(R.id.frameEnq)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_request);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "My Request Listing");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        userType = mahindraClappPreference.getData("UserType");
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameEnq, new Fragment_Enquiry_Open());
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "My Request Listing Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "My Request Listing Screen Android", null);
        mFirebaseAnalytics.logEvent("My_Request_Listing_Screen_Android", params);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

}