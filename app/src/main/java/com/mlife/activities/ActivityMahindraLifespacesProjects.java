package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.fragments.Fragment_ProjectAmenities;
import com.mlife.adapter.PageAdapter;
import com.mlife.fragments.Fragment_AboutProject;
import com.mlife.fragments.Fragment_MLDL_Updatse;
import com.mlife.fragments.Fragment_OffersAndSchemes;
import com.mlife.fragments.Fragment_ProjectGallery;
import com.mlife.fragments.Fragment_ProjectLocation;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMahindraLifespacesProjects extends AppCompatActivity {

    Intent intent;
    String callback = "";
    PageAdapter pagerAdapter;
    public static String projectId;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;

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
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "MLDL Projects Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "MLDL Projects Screen Android", null);
        mFirebaseAnalytics.logEvent("MLDL_Projects_Screen_Android", params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about_project);
        ButterKnife.bind(this);
        intent = getIntent();

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "MLDL Projects");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        projectId = intent.getStringExtra("ProjectId");
        callback = intent.getStringExtra("callBack");

        fragments.add(new Fragment_AboutProject());
        fragments.add(new Fragment_ProjectAmenities());
        fragments.add(new Fragment_ProjectGallery());
        fragments.add(new Fragment_MLDL_Updatse());
        fragments.add(new Fragment_ProjectLocation());
        fragments.add(new Fragment_OffersAndSchemes());

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        vp_Pager.setAdapter(pagerAdapter);

        if (callback.equals("Property")) {
            vp_Pager.setCurrentItem(0);
        } else if (callback.equals("Offers")) {
            vp_Pager.setCurrentItem(5);
        } else {
            vp_Pager.setCurrentItem(0);
        }

        tb_Layout.setupWithViewPager(vp_Pager);

    }
}