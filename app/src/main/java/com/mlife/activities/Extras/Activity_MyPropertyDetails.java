package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.fragments.Fragment_ProjectDetails;
import com.mlife.fragments.Fragment_ProjectDocuments;
import com.mlife.fragments.Fragment_ProjectUpdates;
import com.mlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_MyPropertyDetails extends AppCompatActivity {

    public List<Fragment> fragments = new ArrayList<Fragment>();
    PageAdapter pagerAdapter;

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_property_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        fragments.add(new Fragment_ProjectDetails());
        fragments.add(new Fragment_ProjectUpdates());
        fragments.add(new Fragment_ProjectDocuments());
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        vp_Pager.setAdapter(pagerAdapter);
        vp_Pager.setCurrentItem(0);
        tb_Layout.setupWithViewPager(vp_Pager);
    }


    public class PageAdapter extends FragmentPagerAdapter {

        String[] Tabtile = new String[]{"DETAILS", "CONSTRUCTION UPDATES", "DOCUMENTS"};
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
    public void backClick(){
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick(){
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick(){
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

}
