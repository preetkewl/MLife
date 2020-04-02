package com.mlife.activities.My_Community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectAcceptEvent;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_EventsDetails extends AppCompatActivity implements DataHolder, Observer {

    @BindView(R.id.tv_EventHeading)
    TextView heading;

    @BindView(R.id.tv_EventDate)
    TextView date;

    @BindView(R.id.tv_EventLocation)
    TextView location;

    @BindView(R.id.tv_EventDetails)
    TextView details;

    @BindView(R.id.btn_EventYes)
    Button btn_Yes;

    @BindView(R.id.btn_EventNo)
    Button btn_No;


    Intent intent;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Events Details Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "Events Details Screen Android", null);
        mFirebaseAnalytics.logEvent("Events_Details_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__events_details);
        ButterKnife.bind(this);

        intent = getIntent();
        heading.setText(intent.getStringExtra("Heading"));
        date.setText(intent.getStringExtra("Date"));
        location.setText(intent.getStringExtra("Location"));
        details.setText(intent.getStringExtra("Detail"));

        if (intent.getStringExtra("Check").equals("0")){
            btn_Yes.setVisibility(View.VISIBLE);
            btn_No.setVisibility(View.VISIBLE);
        }

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        objectAcceptEvent.addObserver(this);
    }

    @OnClick(R.id.btn_EventYes)
    public void accept() {
        progressBar.showProgressBar(Activity_EventsDetails.this);
        new Service().acceptEvent(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("Id"), "1");
    }

    @OnClick(R.id.btn_EventNo)
    public void reject() {
        progressBar.showProgressBar(Activity_EventsDetails.this);
        new Service().acceptEvent(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", intent.getStringExtra("Id"), "2");
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

    @Override
    public void update(Observable o, Object arg) {

        progressBar.hideProgressBar();
        if (o instanceof ObjectAcceptEvent) {
            if (objectAcceptEvent.getAcceptEventResponse().getSuccess()){
                startActivity(new Intent(Activity_EventsDetails.this,Activity_Events.class));
                finish();
            }
        }
    }

}