package com.mlife.activities.My_Community.Classified;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.ViewPagerAdapter_Classified;
import com.mlife.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_ClassifiedView extends AppCompatActivity {

    private ArrayList<Integer> Array = new ArrayList<Integer>();
    private ArrayList<String> images = new ArrayList<>();
    private int currentPage = 0;
    Intent intent;

    @BindView(R.id.vp_ClassifiedView)
    ViewPager viewPager;

    @BindView(R.id.tv_Price)
    TextView tv_Price;

    @BindView(R.id.tv_PostedBy)
    TextView tv_PostedBy;

    @BindView(R.id.tv_PostedDate)
    TextView tv_PostedDate;

    @BindView(R.id.tv_Heading)
    TextView tv_Heading;

    @BindView(R.id.tv_Detail)
    TextView tv_Detail;

    @BindView(R.id.btn_Contact)
    Button btn_Contact;

    @OnClick(R.id.btn_Contact)
    public void onButtonClick() {

        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + intent.getStringExtra("Phone")));
        startActivity(callIntent);

    }

    @OnClick(R.id.iv_PreviousImage)
    public void iv_PreviousImage() {
        if (currentPage == 0) {
        } else {
            viewPager.setCurrentItem(--currentPage, true);
        }
    }

    @OnClick(R.id.iv_NextImage)
    public void iv_NextImage() {
        if (currentPage == images.size()) {
        } else {
            viewPager.setCurrentItem(++currentPage, true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__classified_view);
        ButterKnife.bind(this);
        intent = getIntent();

        images = intent.getStringArrayListExtra("imageList");
        tv_PostedBy.setText("Posted by: " + intent.getStringExtra("PostedBy"));
        tv_PostedDate.setText(intent.getStringExtra("PostedDate"));
        tv_Detail.setText(intent.getStringExtra("Dec"));
        tv_Price.setText("₹" + intent.getStringExtra("Price"));
        tv_Heading.setText(intent.getStringExtra("Heading"));
        btn_Contact.setText(intent.getStringExtra("Phone"));

        if(!(images == null)){
            viewPager.setAdapter(new ViewPagerAdapter_Classified(Activity_ClassifiedView.this, images));
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
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

