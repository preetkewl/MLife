package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_FeedbackChatHistory;
import com.mlife.adapter.Request_Adapter;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectTicketList;
import com.mlife.web.model.TicketListData;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Complaints_Open extends Fragment implements Observer, DataHolder {

    View view;

    Request_Adapter adapter;
    MahindraClappPreference mahindraClappPreference;
    List<TicketListData> list = new ArrayList<>();
    DialogProgressBar progressBar = new DialogProgressBar();

    @BindView(R.id.rv_MyRequest)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment__complaints__open, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        progressBar.showProgressBar(getActivity());
        new Service().ticketList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"), "2", mahindraClappPreference.getData("Project"));
        objectTicketListComplaints.addObserver(this);
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), Activity_FeedbackChatHistory.class));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        return view;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectTicketList) {
            if (objectTicketListComplaints.getTicketListResponse().getSuccess()) {
                list.clear();

                progressBar.hideProgressBar();
                for (int i = 0; i < objectTicketListComplaints.getTicketListResponse().getData().size(); i++) {
                    if (objectTicketListComplaints.getTicketListResponse().getData().get(i).getTicketStatus().equals("0")) {
                        list.add(objectTicketListComplaints.getTicketListResponse().getData().get(i));
                    }
                }

                adapter = new Request_Adapter(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                if (!objectTicketListComplaints.getTicketListResponse().getSuccess()) {
                    progressBar.hideProgressBar();
                    Toast.makeText(getContext(), objectTicketListComplaints.getTicketListResponse().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
