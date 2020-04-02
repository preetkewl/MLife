package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.adapter.OurProjects_Adapter;
import com.mlife.web.api.Service;
import com.mlife.adapter.OurProjectsGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectProjectList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_OurProjects extends AppCompatActivity implements Observer, DataHolder {

    OurProjects_Adapter ourProjects_adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<OurProjectsGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_OurProjects)
    RecyclerView rv_OurProjects;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__our_projects);

        ButterKnife.bind(this);
        progressBar.showProgressBar(this);
        objectProjectList.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        new Service().projectList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectProjectList) {
            progressBar.hideProgressBar();
            if (objectProjectList.getProjectListResponse().getSuccess()) {
                ourProjects_adapter = new OurProjects_Adapter(objectProjectList.getProjectListResponse().getData(), Activity_OurProjects.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rv_OurProjects.setLayoutManager(mLayoutManager);
                rv_OurProjects.setItemAnimator(new DefaultItemAnimator());
                rv_OurProjects.setAdapter(ourProjects_adapter);
            } else {
                Toast.makeText(this, objectProjectList.getProjectListResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}