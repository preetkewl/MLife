package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.adapter.NotificationGetterSetter;
import com.mlife.adapter.Notification_Adapter;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.web.holder.Response.ObjectNotificationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityNotification extends AppCompatActivity implements Observer, DataHolder {

    @BindView(R.id.rv_Notification)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataNotification)
    TextView tv_NoDataNotification;

    Notification_Adapter notification_adapter;
    MahindraClappPreference mahindraClappPreference;
    List<NotificationGetterSetter> list = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Notification Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Notification Screen Android", null);
        mFirebaseAnalytics.logEvent("Notification_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__notification);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "Notification");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        new Service().notificationList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", "0");
        objectNotificationList.addObserver(this);
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectNotificationList) {
            if (objectNotificationList.getNotificationListResponse().getSuccess()) {

                if (objectNotificationList.getNotificationListResponse().getData().size() == 0) {
                    tv_NoDataNotification.setVisibility(View.VISIBLE);
                }
                notification_adapter = new Notification_Adapter(objectNotificationList.getNotificationListResponse().getData(), ActivityNotification.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(notification_adapter);
            }
        }
    }
}