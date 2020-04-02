package com.mlife.activities.My_Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.activities.My_Community.Classified.Activity_ClassifiedsAndOffers;
import com.mlife.activities.My_Community.Groups.Activity_Groups;
import com.mlife.adapter.MyCommunityGetterSetter;
import com.mlife.adapter.MyCommunnity_Adapter;
//import com.mlife.googleAnalytics.AnalyticsApplication;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_MyCommunity extends AppCompatActivity {

    List<MyCommunityGetterSetter> list = new ArrayList<>();
    MyCommunnity_Adapter myCommunnity_adapter;
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_myCommunity)
    RecyclerView recyclerView;
    private int PLACE_PICKER_REQUEST = 007;



    @BindView(R.id.tv_projectName)
    TextView tv_projectName;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "My Community Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "My Community Screen Android", null);
        mFirebaseAnalytics.logEvent("My_Community_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_community);
        ButterKnife.bind(this);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-MyCommunity");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        prepairData();
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        tv_projectName.setText("My Property: " + mahindraClappPreference.getData("name"));
        recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(getApplicationContext(), recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MyCommunityGetterSetter myCommunityGetterSetter = list.get(position);
                String Id = myCommunityGetterSetter.getTitle();

                if (Id.equals("GROUPS")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_Groups.class));
                } else if (Id.equals("NOTICES")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_Notice.class));
                } else if (Id.equals("EVENTS")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_Events.class));
                } else if (Id.equals("RESIDENTS & MANAGMENT COMMITTEE DIRECTORY")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_Directory.class));
                } else if (Id.equals("CLUBHOUSE BOOKING")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_ClubHouse_Listing.class));
                } else if (Id.equals("CLASSIFIED AND OFFERS")) {
                    startActivity(new Intent(Activity_MyCommunity.this, Activity_ClassifiedsAndOffers.class));
                } else if (Id.equals("KEY LOCATIONS")) {

                    Double lat = Double.valueOf(mahindraClappPreference.getData("latitude")) + 0.01;
                    Double lng = Double.valueOf(mahindraClappPreference.getData("longitude")) + 0.01;
                    Double lats = Double.valueOf(mahindraClappPreference.getData("latitude")) - 0.01;
                    Double lngs = Double.valueOf(mahindraClappPreference.getData("longitude")) - 0.01;

                    try {
                        LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(new LatLng(lats,lngs), new LatLng(lat,lng));
                        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                        intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                        Intent intent = intentBuilder.build(Activity_MyCommunity.this);
                        startActivityForResult (intent, PLACE_PICKER_REQUEST);

                        //    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        //     startActivityForResult(builder.build(Activity_MyCommunity.this), PLACE_PICKER_REQUEST);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }

//                        startActivity(new Intent(Activity_MyCommunity.this, Activity_KeyLocation.class));
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    public void prepairData() {
        MyCommunityGetterSetter myCommunityGetterSetter = new MyCommunityGetterSetter("GROUPS", R.mipmap.groups);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("NOTICES", R.mipmap.notices);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("EVENTS", R.mipmap.events);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("RESIDENTS & MANAGMENT COMMITTEE DIRECTORY", R.mipmap.residencemanagmentdirectory);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("CLUBHOUSE BOOKING", R.mipmap.clubhousebooking);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("CLASSIFIED AND OFFERS", R.mipmap.classifiedoffers);
        list.add(myCommunityGetterSetter);

        myCommunityGetterSetter = new MyCommunityGetterSetter("KEY LOCATIONS", R.mipmap.keylocations);
        list.add(myCommunityGetterSetter);

        myCommunnity_adapter = new MyCommunnity_Adapter(list, Activity_MyCommunity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myCommunnity_adapter);

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