package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.adapter.GroupJoinedAdapter;
import com.mlife.web.api.Service;
import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.adapter.ExploreGroupGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectJoinedGrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_GroupsJoined extends Fragment implements Observer, DataHolder {

    View view;
    GroupJoinedAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    ExploreGroupGetterSetter exploreGroupGetterSetter;
    List<ExploreGroupGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_groupsJoined)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoGroups)
    TextView tvNoGroups;

    @BindView(R.id.et_SearchMyGroups)
    EditText et_SearchMyGroups;

    @BindView(R.id.srl_GroupJoined)
    SwipeRefreshLayout srl_GroupJoined;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__groups_joined, container, false);
        ButterKnife.bind(this, view);
        tvNoGroups.setVisibility(View.GONE);
        objectJoinedGrops.addObserver(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        et_SearchMyGroups.setSingleLine(true);
        et_SearchMyGroups.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_SearchMyGroups.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  TODO Auto-generated method stub
            }
        });
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override public void onClick(View view, int position) {
                ExploreGroupGetterSetter exploreGroupGetterSetter = list.get(position);
                startActivity(new Intent(getActivity(), Activity_GroupDetails.class).putExtra("GroupId", exploreGroupGetterSetter.getId()));
            }
            @Override public void onLongClick(View view, int position) {}
        }));

        srl_GroupJoined.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_GroupJoined.setRefreshing(true);
                new Service().joinedGroups(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new Service().joinedGroups(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectJoinedGrops) {

            srl_GroupJoined.setRefreshing(false);

            if (objectJoinedGrops.getJoinedGroupsResponse().getSuccess()) {
                if (!list.isEmpty()) {
                    list.clear();
                }
                if (objectJoinedGrops.getJoinedGroupsResponse().getData().size() == 0) {
                    tvNoGroups.setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < objectJoinedGrops.getJoinedGroupsResponse().getData().size(); i++) {
                    exploreGroupGetterSetter = new ExploreGroupGetterSetter(objectJoinedGrops.getJoinedGroupsResponse().getData().get(i).getId(), objectJoinedGrops.getJoinedGroupsResponse().getData().get(i).getName(), objectJoinedGrops.getJoinedGroupsResponse().getData().get(i).getMembers());
                    list.add(exploreGroupGetterSetter);
                }
                adapter = new GroupJoinedAdapter(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Something went wrong, Please Login again", Toast.LENGTH_SHORT).show();
            }
        }
    }

}