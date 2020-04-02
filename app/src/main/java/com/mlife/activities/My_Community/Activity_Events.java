package com.mlife.activities.My_Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.activities.Extras.Activity_Feedback;
import com.mlife.activities.ActivityNotification;

import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.adapter.EventsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectEventList;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Events extends AppCompatActivity implements DataHolder, Observer {

    EventsAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_events)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataEvents)
    TextView tv_NoDataEvents;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Events Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Events Screen Android", null);
        mFirebaseAnalytics.logEvent("Events_Screen_Android", params);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "Events Listing");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        progressBar.showProgressBar(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        new Service().eventList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
        objectEventList.addObserver(this);
        recyclerView.addOnItemTouchListener(new Activity_Feedback.RecyclerTouchListener(getApplicationContext(), recyclerView, new Activity_Feedback.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(Activity_Events.this, Activity_EventsDetails.class).putExtra("Heading", objectEventList.getEventListResponse().getData().get(position).getEventTitle()).putExtra("Date", objectEventList.getEventListResponse().getData().get(position).getEventDate()).putExtra("Location", objectEventList.getEventListResponse().getData().get(position).getEventLoc()).putExtra("Detail", objectEventList.getEventListResponse().getData().get(position).getEventDesc()).putExtra("Id", objectEventList.getEventListResponse().getData().get(position).getUserEventId()).putExtra("Check", objectEventList.getEventListResponse().getData().get(position).getUserEventInterest()));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectEventList) {

            if (objectEventList.getEventListResponse().getSuccess()) {
                progressBar.hideProgressBar();
                if (objectEventList.getEventListResponse().getData().size() == 0) {
                    tv_NoDataEvents.setVisibility(View.VISIBLE);
                }
                adapter = new EventsAdapter(objectEventList.getEventListResponse().getData(), this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else if (!objectEventList.getEventListResponse().getSuccess()) {
                if (!objectEventList.getEventListResponse().getAction().equals("")) {
                    startActivity(new Intent(Activity_Events.this, ActivityLoginWithOTP.class));
                    finish();
                }
            }
        }
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

}
