package com.mlife.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.focpc.mahindralifespaces.MainActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mlife.activities.Edit_Profile.ActivityEditProfile;
import com.mlife.activities.Extras.Activity_OurProjects;
import com.mlife.activities.My_Community.Activity_MyCommunity;
import com.mlife.adapter.Dashboard_Adapter;
import com.mlife.adapter.Navigation_Adapter;
//import com.mlife.googleAnalytics.AnalyticsApplication;
import com.mlife.utils.Constants;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectHomeBannerList;
import com.mlife.adapter.NavigationGetterSetter;
import com.mlife.adapter.ViewPagerAdapter_NavigationDashboard;
import com.mlife.R;
import com.mlife.utils.DilogLogout;
import com.mlife.utils.MahindraClappPreference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class ActivityHome extends AppCompatActivity implements DataHolder, Observer {

    String profileImage = "";
    private int currentPage = 0;
    public static String userType = "";
    private Dashboard_Adapter dashboard_adapter;
    private Navigation_Adapter navigation_adapter;
    MahindraClappPreference mahindraClappPreference;
    private ArrayList<String> bannerList = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private List<NavigationGetterSetter> dashboardList = new ArrayList<>();
    private List<NavigationGetterSetter> navigationList = new ArrayList<>();

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.rv_Dashboard)
    RecyclerView rv_Dashboard;

    @BindView(R.id.rv_NavigationDrawer)
    RecyclerView rv_NavigationDrawer;

    @BindView(R.id.customTextView)
    TextView customTextView;

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.iv_UserImage)
    CircularImageView iv_UserImage;

    @OnClick(R.id.iv_Navigation)
    public void onNavigationClick(View view) {
        drawer.openDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.tv_logout)
    public void logoutUser(View view) {
        DilogLogout dilog_logout = new DilogLogout();
        dilog_logout.Dialogbox(ActivityHome.this);
    }

    @OnClick(R.id.iv_Notification)
    public void onNotificationClick(View view) {
        startActivity(new Intent(ActivityHome.this, ActivityNotification.class));
    }

    @OnClick(R.id.iv_EditProfile)
    public void onEditProfileClick(View view) {
        startActivity(new Intent(ActivityHome.this, ActivityNotificationSettings.class));
    }

    @OnClick(R.id.tv_ChangeProperty)
    public void changeProperty() {
        startActivity(new Intent(ActivityHome.this, ActivityChangeProperty.class));
        finish();
    }

    @OnClick(R.id.iv_EditProfileLogo)
    public void onEditProfileClick() {
        startActivity(new Intent(ActivityHome.this, ActivityEditProfile.class));
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!mahindraClappPreference.getProfileImage().isEmpty()) {
            if (!profileImage.equals(mahindraClappPreference.getProfileImage())) {
                Picasso.with(this).load(mahindraClappPreference.getProfileImage()).placeholder(R.mipmap.placeholdertwo).into(iv_UserImage);
            }
        }

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Dashboard Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Dashboard Screen Android", null);
        mFirebaseAnalytics.logEvent("Dashboard_Screen_Android", params);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__navigation__dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-Dashboard");
//        mTracker.enableAdvertisingIdCollection(true);
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        userType = mahindraClappPreference.getData("UserType");
        tvUserName.setText(mahindraClappPreference.getData("Name"));

        if (!mahindraClappPreference.getProfileImage().isEmpty()) {
            profileImage = mahindraClappPreference.getProfileImage();
            Picasso.with(this).load(mahindraClappPreference.getProfileImage()).placeholder(R.mipmap.placeholderone).into(iv_UserImage);
        }else {
            Picasso.with(this).load(R.mipmap.placeholderone).placeholder(R.mipmap.placeholderone).into(iv_UserImage);
        }


        if (mahindraClappPreference.isPostSaleGuide() && userType.equals(Constants.postSales)) {
            startActivity(new Intent(this, ActivityGuideScreen.class).putExtra("url", R.mipmap.postsales));
            mahindraClappPreference.setPostSaleGuide(false);
        } else if (mahindraClappPreference.isPostHandoOverGuide() && !userType.equals(Constants.postSales)) {
            startActivity(new Intent(this, ActivityGuideScreen.class).putExtra("url", R.mipmap.posthandover));
            mahindraClappPreference.setPostHandoOverGuide(false);
        }

        if (ContextCompat.checkSelfPermission(ActivityHome.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityHome.this, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(ActivityHome.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS, Manifest.permission.LOCATION_HARDWARE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

        customTextView.setText(mahindraClappPreference.getData("name"));

        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityHome.this, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(ActivityHome.this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

        initilizeNavigationDrawer();
        initilizeDashboard();

        rv_Dashboard.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_Dashboard, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                NavigationGetterSetter navigationGetterSetter = dashboardList.get(position);
                int Id = navigationGetterSetter.getId();
                if (userType.equals(Constants.postSales)) {

                    if (Id == 1) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyPropertyDetails.class));
                    } else if (Id == 2) {
                        startActivity(new Intent(ActivityHome.this, ActivitySiteVisitListing.class));
                    } else if (Id == 3) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyPaymentsListing.class));
                    } else if (Id == 4) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyRequestListing.class));
                    } else if (Id == 5) {
                        startActivity(new Intent(ActivityHome.this, MainActivity.class));
                    } else if (Id == 8) {
                        startActivity(new Intent(ActivityHome.this, ActivityValueAddedService.class));
                    }

                } else {

                    if (Id == 1) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyPropertyDetails.class));
                    } else if (Id == 2) {
                        startActivity(new Intent(ActivityHome.this, ActivitySiteVisitListing.class));
                    } else if (Id == 3) {
//                        startActivity(new Intent(Activity_Navigation_Dashboard.this, Activity_Payments.class));
                        Toast.makeText(ActivityHome.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                    } else if (Id == 4) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyRequestListing.class));
                    } else if (Id == 5) {
                        startActivity(new Intent(ActivityHome.this, ActivityMyPaymentsListing.class));
                    } else if (Id == 6) {
                        startActivity(new Intent(ActivityHome.this, MainActivity.class));
                    } else if (Id == 7) {
                        startActivity(new Intent(ActivityHome.this, Activity_MyCommunity.class));
                    } else if (Id == 8) {
                        startActivity(new Intent(ActivityHome.this, ActivityValueAddedService.class));
                    }
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }

        }));

        rv_NavigationDrawer.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_NavigationDrawer, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                NavigationGetterSetter navigationGetterSetter = navigationList.get(position);
                int Id = navigationGetterSetter.getId();
                if (Id == 2) {
                    startActivity(new Intent(ActivityHome.this, Activity_OurProjects.class));
                } else {
                    startActivity(new Intent(ActivityHome.this, ActivityWebView.class).putExtra("Title", navigationGetterSetter.getTitle()));
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }

        }));

        new Service().homebannerList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
        objectHomeBannerList.addObserver(this);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectHomeBannerList) {
            if (objectHomeBannerList.getHomeBannerListResponse().getSuccess()) {

                for (int i = 0; i < objectHomeBannerList.getHomeBannerListResponse().getData().size(); i++) {
                    bannerList.add(objectHomeBannerList.getHomeBannerListResponse().getData().get(i).getBanner());
                }

                viewPager.setAdapter(new ViewPagerAdapter_NavigationDashboard(ActivityHome.this, bannerList));
                CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
                indicator.setViewPager(viewPager);

                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (currentPage == bannerList.size()) {
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                    }
                };

                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, 1000, 5000);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                return;
            }
        }
    }

    private void navigationItems() {

        NavigationGetterSetter navigationGetterSetter = new NavigationGetterSetter(1, "About Mahindra Lifespaces", R.mipmap.aboutmahindra);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(2, "MLDL Projects", R.mipmap.ourprojects);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(3, "Sustainable Urbanisation", R.mipmap.sustainablurabanization);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(4, "Help", R.mipmap.helpfeedback);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(5, "Contact Us", R.mipmap.contact);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(6, "Terms & Conditions", R.mipmap.termscondition);
        navigationList.add(navigationGetterSetter);

        navigationGetterSetter = new NavigationGetterSetter(7, "Privacy Policy", R.mipmap.privacypolicy);
        navigationList.add(navigationGetterSetter);


        if (userType.equals(Constants.postSales)) {

//            AnalyticsApplication application = (AnalyticsApplication) getApplication();
//            Tracker mTracker = application.getDefaultTracker();
//            mTracker.setScreenName("Android-PostSaleSelected");
//            mTracker.send(new HitBuilders.ScreenViewBuilder().build());

            navigationGetterSetter = new NavigationGetterSetter(1, "MY PROPERTY", R.mipmap.myproperty);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(2, "SITE VISIT", R.mipmap.mysitevisit);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(3, "MY PAYMENTS", R.mipmap.mypayments);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(4, "MY REQUESTS", R.mipmap.myrequest);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(5, "LOYALIE\nLOYALTY PROGRAM", R.mipmap.myreferral);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(8, "VALUE ADDED SERVICES", R.mipmap.valueaddedservices);
            dashboardList.add(navigationGetterSetter);

        } else {

//            AnalyticsApplication application = (AnalyticsApplication) getApplication();
//            Tracker mTracker = application.getDefaultTracker();
//            mTracker.setScreenName("Android-PostHandOverSelected");
//            mTracker.send(new HitBuilders.ScreenViewBuilder().build());

            navigationGetterSetter = new NavigationGetterSetter(1, "MY PROPERTY", R.mipmap.myproperty);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(7, "MY COMMUNITY", R.mipmap.mycommunity);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(3, "MY PAYMENTS", R.mipmap.maintenancebills);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(4, "MY REQUESTS", R.mipmap.mycomplaints);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(6, "LOYALIE\nLOYALTY PROGRAM", R.mipmap.myreferral);
            dashboardList.add(navigationGetterSetter);

            navigationGetterSetter = new NavigationGetterSetter(8, "VALUE ADDED SERVICES", R.mipmap.valueaddedservices);
            dashboardList.add(navigationGetterSetter);

        }
    }

    private void initilizeDashboard() {
        dashboard_adapter = new Dashboard_Adapter(dashboardList, ActivityHome.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        rv_Dashboard.setLayoutManager(mLayoutManager);
        rv_Dashboard.setItemAnimator(new DefaultItemAnimator());
        rv_Dashboard.setAdapter(dashboard_adapter);
    }

    private void initilizeNavigationDrawer() {

        navigation_adapter = new Navigation_Adapter(navigationList, ActivityHome.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_NavigationDrawer.setLayoutManager(mLayoutManager);
        rv_NavigationDrawer.setItemAnimator(new DefaultItemAnimator());
        rv_NavigationDrawer.setAdapter(navigation_adapter);
        navigationItems();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ActivityHome.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ActivityHome.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}