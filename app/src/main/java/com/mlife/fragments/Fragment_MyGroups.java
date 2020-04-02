package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.My_Community.Groups.Activity_CreateGroup;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.adapter.ExploreGroupGetterSetter;
import com.mlife.adapter.MyGroupsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectMyGroups;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_MyGroups extends Fragment implements Observer, DataHolder {

    View view;
    MyGroupsAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    ExploreGroupGetterSetter exploreGroupGetterSetter;
    List<ExploreGroupGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_MyGroups)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoGroups)
    TextView tvNoGroups;

    @BindView(R.id.srl_MyGroups)
    SwipeRefreshLayout srl_MyGroups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__my_groups, container, false);
        ButterKnife.bind(this, view);
        tvNoGroups.setVisibility(View.GONE);
        objectMyGroups.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        new Service().myGroups(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                ExploreGroupGetterSetter exploreGroupGetterSetter = list.get(position);
//                startActivity(new Intent(getActivity(),Activity_GroupDetails.class).putExtra("GroupId",exploreGroupGetterSetter.getId()));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        srl_MyGroups.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_MyGroups.setRefreshing(true);
                new Service().myGroups(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        srl_MyGroups.setRefreshing(false);
        new Service().myGroups(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectMyGroups) {

            if (objectMyGroups.getMyGroupsResponse().getSuccess()) {
                srl_MyGroups.setRefreshing(false);

                if (!list.isEmpty()) {
                    list.clear();
                }

                if (objectMyGroups.getMyGroupsResponse().getData().size() == 0) {
                    tvNoGroups.setVisibility(View.VISIBLE);
                }

                adapter = new MyGroupsAdapter(objectMyGroups.getMyGroupsResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Something went wrong, Please Login again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.btn_CreateGroup)
    public void createNewGroup() {
        startActivity(new Intent(getActivity(), Activity_CreateGroup.class));
    }
}