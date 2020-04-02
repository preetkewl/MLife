package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mlife.fragments.FirstFragment;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityValueAddedServiceMyInterior extends AppCompatActivity implements DataHolder, Observer {

    Intent intent;
    MyPagerAdapter pagerAdapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.vp_Pager)
    ViewPager vp_Pager;

    @BindView(R.id.tb_Layout)
    TabLayout tb_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_interior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        intent = getIntent();
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        try {
            if (objectGetProduct.getGetProductResponse().getSuccess()) {
                try {
                    pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
                    tb_Layout.setupWithViewPager(vp_Pager);
                    vp_Pager.setAdapter(pagerAdapter);
                    vp_Pager.setCurrentItem(intent.getIntExtra("Position", 0));
                    vp_Pager.setOffscreenPageLimit(intent.getIntExtra("Size", 5));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = objectGetProduct.getGetProductResponse().getData().size();

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return FirstFragment.newInstance(position, objectGetProduct.getGetProductResponse().getData().get(position).getVasProductId());
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return objectGetProduct.getGetProductResponse().getData().get(position).getVasProductName();
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