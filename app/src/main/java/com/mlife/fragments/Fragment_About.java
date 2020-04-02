package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.activities.My_Community.Groups.Activity_EditGroupDetails;
import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGroupAboutbyGroup;
import com.mlife.R;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_About extends Fragment implements Observer, DataHolder {

    View view;
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.tv_GroupOwner)
    TextView tv_GroupOwner;

    @BindView(R.id.tv_GroupCreation)
    TextView tv_GroupCreation;

    @BindView(R.id.tv_GroupLastActivity)
    TextView tv_GroupLastActivity;

    @BindView(R.id.tv_GroupDescription)
    TextView tv_GroupDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__about, container, false);
        ButterKnife.bind(this,view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        new Service().groupAboutbyGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId);
        objectGroupAboutbyGroup.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectGroupAboutbyGroup)
            if (objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getSuccess()){
                tv_GroupOwner.setText("Group Moderater: " + objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getData().get(0).getModerater());
                tv_GroupCreation.setText("Creation Date: " + objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getData().get(0).getCreationdate());
                tv_GroupLastActivity.setText("Last Activity: " + objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getData().get(0).getLastactivity());
                tv_GroupDescription.setText(objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getData().get(0).getAboutus());
            }
    }

    @OnClick(R.id.iv_EditGroupDetails)
    public void editGroup(){
        startActivity(new Intent(getActivity(), Activity_EditGroupDetails.class)
                .putExtra("Name",Activity_GroupDetails.groupName)
                .putExtra("Description",objectGroupAboutbyGroup.getGroupAboutbyGroupResponse().getData().get(0).getAboutus())
                .putExtra("GroupId", Activity_GroupDetails.groupId)
                .putExtra("isPublic",""));
    }

}