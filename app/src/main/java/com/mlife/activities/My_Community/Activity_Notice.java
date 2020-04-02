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
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.ActivityLogin;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectNoticeList;
import com.mlife.adapter.NoticeAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Notice extends AppCompatActivity implements DataHolder, Observer {


    NoticeAdapter apapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_Notice)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataFound)
    TextView tv_NoDataFound;

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



    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Notice Listing Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Notice Listing Screen Android", null);
        mFirebaseAnalytics.logEvent("Notice_Listing_Screen_Android", params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "Notice Listing");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        progressBar.showProgressBar(Activity_Notice.this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        new Service().noticeList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
        objectNoticeList.addObserver(this);

        recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(getApplicationContext(), recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(Activity_Notice.this, Activity_NoticeDetails.class).putExtra("Heading",objectNoticeList.getNoticeListResponse().getData().get(position).getNoticeTitle()).putExtra("Date",objectNoticeList.getNoticeListResponse().getData().get(position).getDate()).putExtra("Detail",objectNoticeList.getNoticeListResponse().getData().get(position).getNoticeDesc()));
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    @Override
    public void update(Observable o, Object arg) {

        progressBar.hideProgressBar();

        if (o instanceof ObjectNoticeList) {

            if (objectNoticeList.getNoticeListResponse().getSuccess()) {

                if (objectNoticeList.getNoticeListResponse().getData().size()==0){tv_NoDataFound.setVisibility(View.VISIBLE);}
                apapter = new NoticeAdapter(objectNoticeList.getNoticeListResponse().getData(), this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(apapter);
            }else if (!objectNoticeList.getNoticeListResponse().getSuccess()){
                if (!objectNoticeList.getNoticeListResponse().getAction().equals("")){
                    Toast.makeText(this, objectNoticeList.getNoticeListResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Activity_Notice.this, ActivityLoginWithOTP.class));
                    finish();
                }
            }
        }
    }
}