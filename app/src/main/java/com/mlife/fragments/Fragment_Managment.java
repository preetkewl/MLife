package com.mlife.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.model.CommitteeManagmentListData;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Managment extends Fragment implements DataHolder, Observer {

    View view;
    ManagmentAdapter apapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.managment)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__managment, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        progressBar.showProgressBar(getActivity());
        new Service().committeeManagmentList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"));
        objectCommitteeManagmentList.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.hideProgressBar();
        try {
            if (objectCommitteeManagmentList.getCommitteeManagmentListResponse().getData().size() == 0) {
                tv_NoData.setVisibility(View.VISIBLE);
            } else {
                apapter = new ManagmentAdapter(objectCommitteeManagmentList.getCommitteeManagmentListResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(apapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class ManagmentAdapter extends RecyclerView.Adapter<ManagmentAdapter.MyViewHolder> {

        Context context;
        private java.util.List<CommitteeManagmentListData> List;

        @Override
        public ManagmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_managmentlist, parent, false);
            return new ManagmentAdapter.MyViewHolder(itemView);
        }

        public ManagmentAdapter(java.util.List<CommitteeManagmentListData> DataList, FragmentActivity activity) {
            this.List = DataList;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView Heading, Position;

            public MyViewHolder(View view) {
                super(view);
                Heading = (TextView) view.findViewById(R.id.tv_Name);
                Position = (TextView) view.findViewById(R.id.tv_Position);

            }
        }

        @Override
        public void onBindViewHolder(final ManagmentAdapter.MyViewHolder holder, final int position) {
            final CommitteeManagmentListData detail = List.get(position);
            holder.Heading.setText(detail.getCommitteeManagmentName());
            holder.Position.setText(detail.getCommitteeManagmentDesignation());
        }

        @Override
        public int getItemCount() {
            return List.size();
        }

    }

}