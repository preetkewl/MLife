package com.mlife.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectAddTicket;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddServiceRequest;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityMyRequestNewTicket extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String type = "";
    DialogProgressBar progressBar;
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.et_Description_NewTicket)
    EditText et_Description_NewTicket;

    @OnClick(R.id.btn_SubmitTicket)
    public void submitTicket() {
        et_Description_NewTicket.setError(null);

        if (type.equals("1")) {
            if (et_Description_NewTicket.getText().toString().trim().length() == 0) {
                et_Description_NewTicket.setError("Not Valid Input");
            } else {
                progressBar.showProgressBar(ActivityMyRequestNewTicket.this);
                new Service().addServiceRequest(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), et_Description_NewTicket.getText().toString(), type, mahindraClappPreference.getData("Project"));
            }
        } else {
            if (et_Description_NewTicket.getText().toString().trim().length() == 0) {
                et_Description_NewTicket.setError("Not Valid Input");
            } else {
                progressBar.showProgressBar(ActivityMyRequestNewTicket.this);
                new Service().addServiceRequest(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), et_Description_NewTicket.getText().toString(), type, mahindraClappPreference.getData("Project"));
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "New Request Screen Android");
        mFirebaseAnalytics.setCurrentScreen(this, "New Request Screen Android", null);
        mFirebaseAnalytics.logEvent("New_Request_Screen_Android", params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__my_request__new_ticket);
        ButterKnife.bind(this);
        progressBar = new DialogProgressBar();

//        AnalyticsApplication application = (AnalyticsApplication) getApplication();
//        Tracker mTracker = application.getDefaultTracker();
//        mTracker.setScreenName("Android-" + "New Request");
//        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        objectAddTicket.addObserver(this);
        objectAddServiceRequest.addObserver(this);
        intent = getIntent();
        type = intent.getStringExtra("type");
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.hideProgressBar();

        if (o instanceof ObjectAddTicket) {
            if (objectAddTicket.getAddTicketResponse().getSuccess()) {
                startActivity(new Intent(ActivityMyRequestNewTicket.this, Activity_ThankYou.class).putExtra("Title", "New Ticket").putExtra("Heading", "THANK YOU").putExtra("Detail", "Your ticket has been submitted \n our representative will respond within 24 hours.").putExtra("Image", R.mipmap.ticketconfirmation).putExtra("callBack", "MyRequest"));
                finish();
            } else {
                Toast.makeText(ActivityMyRequestNewTicket.this, objectAddTicket.getAddTicketResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
            objectAddTicket.deleteObservers();
        } else if (o instanceof ObjectAddServiceRequest) {
            if (objectAddServiceRequest.getAddServiceRequestResponse().getSuccess()) {
                startActivity(new Intent(ActivityMyRequestNewTicket.this, Activity_ThankYou.class).putExtra("Title", "New Ticket").putExtra("Heading", "THANK YOU").putExtra("Detail", "Your ticket has been submitted \n our representative will respond within 24 hours.").putExtra("Image", R.mipmap.ticketconfirmation).putExtra("callBack", "MyRequest"));
                finish();
            }else {
                Toast.makeText(ActivityMyRequestNewTicket.this, objectAddServiceRequest.getAddServiceRequestResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
//            objectAddServiceRequest.deleteObservers();
        }
    }
}