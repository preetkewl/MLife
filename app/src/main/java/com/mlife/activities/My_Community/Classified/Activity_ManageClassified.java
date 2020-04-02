package com.mlife.activities.My_Community.Classified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.Adapter_ManageClassified;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectOfferList;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.focpc.mahindralifespaces.utils.MlsApp.getContext;

public class Activity_ManageClassified extends AppCompatActivity implements Observer, DataHolder {

    Adapter_ManageClassified adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    Intent intent = new Intent();

    @BindView(R.id.rv_ManageClassified_Records)
    RecyclerView recyclerView;

    @BindView(R.id.tv_ManageClassified_NoRecordFound)
    TextView tv_ManageClassified_NoRecordFound;

    @OnClick(R.id.iv_Back_ManageClassified)
    public void backClk(View view) {
        finish();
    }


    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__manage_classified);
        ButterKnife.bind(this);
        progressBar.showProgressBar(Activity_ManageClassified.this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        intent = getIntent();
        if (intent.getStringExtra("check").equals("1")) {
            new Service().myOfferList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "1");
        } else {
            new Service().myOfferList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "2");
        }
        objectMyOfferList.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof ObjectOfferList) {
            if (objectMyOfferList.getOfferListResponse().getSuccess()) {
                tv_ManageClassified_NoRecordFound.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter = new Adapter_ManageClassified(objectMyOfferList.getOfferListResponse().getData(), Activity_ManageClassified.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_ManageClassified.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                tv_ManageClassified_NoRecordFound.setVisibility(View.VISIBLE);
                Toast.makeText(this, objectMyOfferList.getOfferListResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
            progressBar.hideProgressBar();
        }
    }
}