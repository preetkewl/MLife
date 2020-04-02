package com.mlife.activities.My_Community.Groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mlife.adapter.AcceptMemberAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.model.MembersbyGroupData;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_AcceptMembers extends AppCompatActivity implements Observer, DataHolder {

    Intent intent;
    String groupId;
    AcceptMemberAdapter adapter;
    MahindraClappPreference mahindraClappPreference;
    DialogProgressBar progressBar = new DialogProgressBar();
    private java.util.List<MembersbyGroupData> dataList = new ArrayList<>();

    @BindView(R.id.rv_AcceptMembers)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoPendingRequest)
    TextView tv_NoPendingRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__accept_members);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(this);
        intent = getIntent();
        groupId = intent.getStringExtra("GroupId");
        progressBar.showProgressBar(Activity_AcceptMembers.this);
        new Service().membersbyGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", groupId);
        objectMembersbyGroup.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        dataList.clear();
        progressBar.hideProgressBar();

        for (MembersbyGroupData membersbyGroupData : objectMembersbyGroup.getMembersbyGroupResponse().getData()) {
            if (membersbyGroupData.getStatus().equals("2")) {
                dataList.add(membersbyGroupData);
            }
        }

        if (dataList.isEmpty()) {
            tv_NoPendingRequest.setVisibility(View.VISIBLE);
        }

        adapter = new AcceptMemberAdapter(dataList, this, groupId);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

}