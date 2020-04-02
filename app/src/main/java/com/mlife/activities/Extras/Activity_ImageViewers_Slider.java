package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.ProjectImageGetterSetter;
import com.mlife.adapter.ViewPagerAdapter_ImageViewer;
import com.mlife.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ImageViewers_Slider extends AppCompatActivity {

    @BindView(R.id.vp_ImageView)
    ViewPager vp_ImageView;

    ArrayList<ProjectImageGetterSetter> myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__image_viewers__slider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        if (getIntent().getStringExtra("check").equals("MLDL Construction")) {

            myData = (ArrayList<ProjectImageGetterSetter>) getIntent().getSerializableExtra("mylist");
            vp_ImageView.setAdapter(new ViewPagerAdapter_ImageViewer(this, myData,0));
            vp_ImageView.setCurrentItem(getIntent().getIntExtra("position", 0));

        } else if (getIntent().getStringExtra("check").equals("Gallery")) {

            myData = (ArrayList<ProjectImageGetterSetter>) getIntent().getSerializableExtra("mylist");
            vp_ImageView.setAdapter(new ViewPagerAdapter_ImageViewer(this, myData,1));
            vp_ImageView.setCurrentItem(getIntent().getIntExtra("position", 0));

        }else if (getIntent().getStringExtra("check").equals("Construction")) {
            myData = (ArrayList<ProjectImageGetterSetter>) getIntent().getSerializableExtra("mylist");
//            myList = new ArrayList<>();
//            for (ProjectImageGetterSetter getterSetter : myData) {
//                myList.add( getterSetter.getImage());
//            }
            vp_ImageView.setAdapter(new ViewPagerAdapter_ImageViewer(this, myData,2));
            vp_ImageView.setCurrentItem(getIntent().getIntExtra("position", 0));

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