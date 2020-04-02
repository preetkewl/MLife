package com.mlife.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.web.api.Service;
import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectExploreGroups;
import com.mlife.web.holder.Response.ObjectJoinGroup;
import com.mlife.web.model.ExporeGroupsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_ExploreGroup extends Fragment implements Observer, DataHolder {

    View view;
    ExploreGroupApapter apapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    public List<ExporeGroupsData> dataList = new ArrayList<>();

    @BindView(R.id.et_SearchGroups)
    EditText et_SearchGroups;

    @BindView(R.id.rv_exp)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoGroups)
    TextView tvNoGroups;

    @BindView(R.id.srl_ExploreGroup)
    SwipeRefreshLayout srl_ExploreGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__explore_group, container, false);
        ButterKnife.bind(this, view);
        tvNoGroups.setVisibility(View.GONE);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        objectExploreGroups.addObserver(this);
        objectJoinGroup.addObserver(this);
        et_SearchGroups.setSingleLine(true);
        et_SearchGroups.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                try {
                    String text = et_SearchGroups.getText().toString().toLowerCase(Locale.getDefault());
                    apapter.filter(text);
                }catch (Exception e){

                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

        });

        srl_ExploreGroup.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl_ExploreGroup.setRefreshing(true);
                progressBar.showProgressBar(getActivity());
                new Service().exploreGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.showProgressBar(getActivity());
        new Service().exploreGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectExploreGroups) {

            srl_ExploreGroup.setRefreshing(false);

            if (objectExploreGroups.getExporeGroupsResponse().getSuccess()) {
                if (objectExploreGroups.getExporeGroupsResponse().getData().size() == 0) {
                    tvNoGroups.setVisibility(View.VISIBLE);
                }
                apapter = new ExploreGroupApapter(objectExploreGroups.getExporeGroupsResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(apapter);
                progressBar.hideProgressBar();
            } else {
                progressBar.hideProgressBar();
                Toast.makeText(getActivity(), "Something went wrong, Please Login again", Toast.LENGTH_SHORT).show();
            }

        } else if (o instanceof ObjectJoinGroup) {
            if (objectJoinGroup.getJoinGroupResponse().getSuccess()) {
                Toast.makeText(getContext(), objectJoinGroup.getJoinGroupResponse().getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), Activity_GroupDetails.class).putExtra("GroupId", Activity_GroupDetails.groupId));

//                AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
//                Tracker mTracker = application.getDefaultTracker();
//                mTracker.setScreenName("Android-GroupJoined");
//                mTracker.send(new HitBuilders.ScreenViewBuilder().build());

            } else {
                Toast.makeText(getContext(), objectJoinGroup.getJoinGroupResponse().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ExploreGroupApapter extends RecyclerView.Adapter<ExploreGroupApapter.MyViewHolder> {

        Context context;
        private java.util.List<ExporeGroupsData> List;
        private java.util.List<ExporeGroupsData> arraylist;

        @Override
        public ExploreGroupApapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_explore_group, parent, false);
            return new ExploreGroupApapter.MyViewHolder(itemView);
        }

        public ExploreGroupApapter(java.util.List<ExporeGroupsData> DataList, FragmentActivity activity) {
            this.List = DataList;
            arraylist = new ArrayList<>(List);
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView Name, Members;
            public Button Join, Explore;

            public MyViewHolder(View view) {
                super(view);
                Name = (TextView) view.findViewById(R.id.tv_ExploreGroupHeading);
                Members = (TextView) view.findViewById(R.id.tv_ExploreGroupMembers);
                Explore = (Button) view.findViewById(R.id.btn_ExploreGroupExplore);
                Join = (Button) view.findViewById(R.id.btn_ExploreGroupJoinGroup);
            }
        }

        @Override
        public void onBindViewHolder(final ExploreGroupApapter.MyViewHolder holder, final int position) {
            final ExporeGroupsData detail = List.get(position);
            holder.Name.setText(detail.getName());
            holder.Members.setText(detail.getMembers() + " Members");

            holder.Explore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, Activity_GroupDetails.class).putExtra("GroupId", detail.getId()));
                }
            });

            holder.Join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Service().joinGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", detail.getId());
                    Activity_GroupDetails.groupId = detail.getId();
                }
            });
        }

        @Override
        public int getItemCount() {
            return List.size();
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            List.clear();

            if (charText.length() == 0) {
                List.addAll(arraylist);
            } else {
                for (ExporeGroupsData wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        List.add(wp);
                    }
                }
            }

            if (List.size() == 0) {
                tvNoGroups.setVisibility(View.VISIBLE);
            } else {
                tvNoGroups.setVisibility(View.GONE);
            }
            notifyDataSetChanged();
        }

    }

}