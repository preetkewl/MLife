package com.mlife.activities.My_Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.web.api.Service;
import com.mlife.adapter.ClubHouseAdapter;
import com.mlife.adapter.ClubhouseEventGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectChangeBookingStatus;
import com.mlife.web.holder.Response.ObjectClubHouseList;
import com.mlife.web.model.ClubHouseListData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ClubHouse_Listing extends AppCompatActivity implements DataHolder, Observer {

    ClubHouseAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<ClubhouseEventGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_ClubhouseBooking)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Club House Listing Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Club House Listing Screen Android", null);
        mFirebaseAnalytics.logEvent("Club_House_Listing_Screen_Android", params);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__club_house_booking);
        ButterKnife.bind(this);
        objectChangeBookingStatus.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
    }


    @Override
    protected void onStart() {
        super.onStart();
        objectClubHouseList.addObserver(this);
        progressBar.showProgressBar(this);
        new Service().clubhouseList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "");
    }

    @OnClick(R.id.fab_BookClub)
    public void bookClub() {
        startActivity(new Intent(Activity_ClubHouse_Listing.this, Activity_ClubHouse_BookClubHouse.class));
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectClubHouseList) {
            if (objectClubHouseList.getClubHouseListResponse().getSuccess()) {

                if (objectClubHouseList.getClubHouseListResponse().getData().size() == 0) {
                    tv_NoData.setVisibility(View.VISIBLE);
                }

                progressBar.hideProgressBar();
                adapter = new ClubHouseAdapter(objectClubHouseList.getClubHouseListResponse().getData(), this, new ClubHouseAdapter.OnItemclick() {
                    @Override
                    public void onItemClick(View v, ClubHouseListData detail) {
                        progressBar.showProgressBar(Activity_ClubHouse_Listing.this);
                        new Service().changeBookingStatus(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getClubhouseBookingId(), "2");

                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }else  if (o instanceof ObjectChangeBookingStatus) {
            if (objectChangeBookingStatus.getChangeBookingStatusResponse().getSuccess()) {

            //    progressBar.showProgressBar(Activity_ClubHouse_Listing.this);
                new Service().clubhouseList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "");
                Toast.makeText(this, objectChangeBookingStatus.getChangeBookingStatusResponse().getMessage(), Toast.LENGTH_SHORT).show();
               // recyclerView.setAdapter(adapter);
               // adapter.notifyDataSetChanged();

            }else {
                progressBar.hideProgressBar();
            }
        }
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