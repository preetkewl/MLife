package com.mlife.activities.My_Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_NoticeDetails extends AppCompatActivity {

    Intent intent;
    DialogProgressBar progressBar = new DialogProgressBar();

    @BindView(R.id.tv_Heading)
    TextView heading;

    @BindView(R.id.tv_Date)
    TextView date;

    @BindView(R.id.tv_Details)
    TextView details;

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
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Notice Details Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Notice Details Screen Android", null);
        mFirebaseAnalytics.logEvent("Notice_Details_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__notice_details);
        progressBar.showProgressBar(Activity_NoticeDetails.this);
        ButterKnife.bind(this);

        intent = getIntent();
        heading.setText(intent.getStringExtra("Heading"));
        date.setText("Posted: "+intent.getStringExtra("Date"));
        details.setText(intent.getStringExtra("Detail"));
        progressBar.hideProgressBar();
    }
}