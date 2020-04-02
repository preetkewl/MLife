package com.mlife.activities.My_Community.Groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Switch;

import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_EditGroupDetails extends AppCompatActivity implements DataHolder,Observer {

    Intent intent;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.et_GroupName)
    EditText et_GroupName;

    @BindView(R.id.et_GroupDescription)
    EditText et_GroupDescription;

    @BindView(R.id.sw_GroupType)
    Switch sw_GroupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__edit_group_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        intent = getIntent();
        et_GroupName.setText(intent.getStringExtra("Name"));
        et_GroupDescription.setText(intent.getStringExtra("Description"));
        et_GroupDescription.setText(intent.getStringExtra("Description"));
        objectChangeGroupDetails.addObserver(this);
    }

    @OnClick(R.id.btn_UpdateGroup)
    public void onButtonClick() {

        et_GroupName.setError(null);
        et_GroupDescription.setError(null);

        if (et_GroupName.getText().toString().trim().isEmpty()) {
            et_GroupName.setError("Not valid input");
            et_GroupName.requestFocus();
        } else if (et_GroupDescription.getText().toString().trim().isEmpty()) {
            et_GroupDescription.setError("Not valid input");
            et_GroupDescription.requestFocus();
        } else {
            progressBar.showProgressBar(Activity_EditGroupDetails.this);
            new Service().changeGroupDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), intent.getStringExtra("GroupId"), et_GroupName.getText().toString().trim(), et_GroupDescription.getText().toString().trim(), "0");
        }
    }

    @Override
    public void update(Observable observable, Object o) {
     progressBar.hideProgressBar();
    }

}