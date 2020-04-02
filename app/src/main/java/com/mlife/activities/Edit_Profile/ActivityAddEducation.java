package com.mlife.activities.Edit_Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectDeleteProfileEducationDetails;
import com.mlife.web.holder.Response.ObjectUpdateProfileEducation;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityAddEducation extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String Id = "";
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.et_Institute)
    EditText et_Institute;

    @BindView(R.id.et_Degree)
    EditText et_Degree;

    @BindView(R.id.et_YearFrom)
    EditText et_YearFrom;

    @BindView(R.id.et_YearTo)
    EditText et_YearTo;

    @BindView(R.id.btn_SubmitDetails)
    Button btn_SubmitDetails;

    @BindView(R.id.btn_DeleteDetails)
    Button btn_DeleteDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__education);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        objectUpdateProfileEducation.addObserver(this);
        objectDeleteProfileEducationDetails.addObserver(this);
        intent = getIntent();
        if (intent.getStringExtra("Check").equals("0")) {

        } else {
            Id =intent.getStringExtra("Id");
            et_Degree.setText(intent.getStringExtra("Degree"));
            et_Institute.setText(intent.getStringExtra("University"));
            et_YearTo.setText(intent.getStringExtra("YearTo"));
            et_YearFrom.setText(intent.getStringExtra("YearFrom"));
            btn_DeleteDetails.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_SubmitDetails)
    public void submitData() {
        if (et_Institute.getText().toString().trim().isEmpty()) {
            et_Institute.setError("Invalid");
            et_Institute.requestFocus();
        } else if (et_Degree.getText().toString().trim().isEmpty()) {
            et_Degree.setError("Invalid");
            et_Degree.requestFocus();
        } else if (et_YearFrom.getText().toString().trim().isEmpty()) {
            et_YearFrom.setError("Invalid");
            et_YearFrom.requestFocus();
        } else if (et_YearTo.getText().toString().trim().isEmpty()) {
            et_YearTo.setError("Invalid");
            et_YearTo.requestFocus();
        } else {
            btn_SubmitDetails.setClickable(false);
            progressBar.showProgressBar(this);
            new Service().updateProfileEducationDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", et_Institute.getText().toString().trim(), et_Degree.getText().toString().trim(), et_YearFrom.getText().toString().trim(), et_YearTo.getText().toString().trim(), Id);
        }
    }

    @OnClick(R.id.btn_DeleteDetails)
    public void deleteData() {
        progressBar.showProgressBar(this);
        new Service().deleteProfileEducationDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Id);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectUpdateProfileEducation) {
            btn_SubmitDetails.setClickable(true);
            progressBar.hideProgressBar();
            if (objectUpdateProfileEducation.getUpdateProfileEducationResponse().getSuccess()) {
                Toast.makeText(this, objectUpdateProfileEducation.getUpdateProfileEducationResponse().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, objectUpdateProfileEducation.getUpdateProfileEducationResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (o instanceof ObjectDeleteProfileEducationDetails) {
            Toast.makeText(this, objectDeleteProfileEducationDetails.getDeleteProfileEducationDetailsResponse().getMessage(), Toast.LENGTH_SHORT).show();
            progressBar.hideProgressBar();
            finish();
        }
    }

    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
        finish();
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
        finish();
    }

}