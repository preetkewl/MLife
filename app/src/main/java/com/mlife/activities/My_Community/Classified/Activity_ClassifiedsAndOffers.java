package com.mlife.activities.My_Community.Classified;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.fragments.Fragment_LocalOffers;
import com.mlife.fragments.Fragment_Rent;
import com.mlife.fragments.Fragment_Sell;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ClassifiedsAndOffers extends AppCompatActivity {

    PageAdapter pagerAdapter;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__classifieds_and_offers);
        ButterKnife.bind(this);

        vp_Pager.setOffscreenPageLimit(3);
        fragments.add(new Fragment_LocalOffers());
        fragments.add(new Fragment_Sell());
        fragments.add(new Fragment_Rent());
        pagerAdapter = new Activity_ClassifiedsAndOffers.PageAdapter(getSupportFragmentManager(), fragments);
        vp_Pager.setAdapter(pagerAdapter);
        vp_Pager.setCurrentItem(0);
        tb_Layout.setupWithViewPager(vp_Pager);
    }

    public class PageAdapter extends FragmentPagerAdapter {

        String[] Tabtile = new String[]{"Local Offers", "Sell", "Rent"};
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
        finish();
    }

}