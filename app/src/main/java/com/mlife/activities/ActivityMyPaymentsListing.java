package com.mlife.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.fragments.Fragment_OutstangBalance;
import com.mlife.fragments.Fragment_PaymentHistory;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMyPaymentsListing extends AppCompatActivity {

    PageAdapter pagerAdapter;
    public Spinner sp_Year;
    MahindraClappPreference mahindraClappPreference;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;

    @BindView(R.id.sp_MyProperty)
    TextView sp_Property;

    Fragment_OutstangBalance fragment_outstangBalance;
    Fragment_PaymentHistory fragment_paymentHistory;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "My Payments Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "My Payments Screen Android", null);
        mFirebaseAnalytics.logEvent("My_Payments_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__payments);
        ButterKnife.bind(this);

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        sp_Year = (Spinner) findViewById(R.id.sp_Year);
        sp_Property.setText(mahindraClappPreference.getData("name"));

        fragment_outstangBalance = new Fragment_OutstangBalance();
        fragment_paymentHistory = new Fragment_PaymentHistory();

        fragments.add(fragment_outstangBalance);
        fragments.add(fragment_paymentHistory);
        pagerAdapter = new ActivityMyPaymentsListing.PageAdapter(getSupportFragmentManager(), fragments);
        vp_Pager.setAdapter(pagerAdapter);
        vp_Pager.setCurrentItem(0);
        tb_Layout.setupWithViewPager(vp_Pager);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            vp_Pager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (vp_Pager.getCurrentItem() == 0) {
                        ArrayAdapter<String> categoriesAdapter;
                        categoriesAdapter = new ArrayAdapter<String>(ActivityMyPaymentsListing.this, R.layout.spinner_item, Fragment_OutstangBalance.dateFilter);
                        sp_Year.setAdapter(categoriesAdapter);
                    } else {
                        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(ActivityMyPaymentsListing.this, R.layout.spinner_item, Fragment_PaymentHistory.dateFilter);
                        sp_Year.setAdapter(categoriesAdapter);
                    }
                }
            });    // Do something for lollipop and above versions
        } else { vp_Pager.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {

                    if (vp_Pager.getCurrentItem() == 0) {
                        ArrayAdapter<String> categoriesAdapter;
                        categoriesAdapter = new ArrayAdapter<String>(ActivityMyPaymentsListing.this, R.layout.spinner_item, Fragment_OutstangBalance.dateFilter);
                        sp_Year.setAdapter(categoriesAdapter);
                    } else {
                        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(ActivityMyPaymentsListing.this, R.layout.spinner_item, Fragment_PaymentHistory.dateFilter);
                        sp_Year.setAdapter(categoriesAdapter);
                    }

                }
            });     // do something for phones running an SDK before lollipop
        }

        sp_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fragment_outstangBalance.filter(sp_Year.getSelectedItem().toString());
                fragment_paymentHistory.filter(sp_Year.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public class PageAdapter extends FragmentPagerAdapter {

        String[] Tabtile = new String[]{"OUTSTANDING\nBALANCE", "PAYMENT\nHISTORY"};

        List<Fragment> mFragment = new ArrayList<>();
        public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragment = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Tabtile[position];
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