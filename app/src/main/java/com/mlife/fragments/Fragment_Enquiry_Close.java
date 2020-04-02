package com.mlife.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityLogin;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.adapter.Request_Adapter;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectTicketList;
import com.mlife.web.model.GetServiceRequestsDetails;
import com.mlife.web.model.TicketListData;
import com.mlife.adapter.GetServiceRequestsAdapter;
import com.mlife.R;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectGetServiceRequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mlife.activities.ActivityHome.userType;

public class Fragment_Enquiry_Close extends Fragment implements Observer, DataHolder {

    View view;

    Request_Adapter adapter;
    MahindraClappPreference mahindraClappPreference;
    List<TicketListData> list = new ArrayList<>();
    DialogProgressBar progressBar = new DialogProgressBar();
    GetServiceRequestsAdapter adapters;
    List<GetServiceRequestsDetails> dataList = new ArrayList<>();

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    @BindView(R.id.rv_Myenq)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment__enquiry__close, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        progressBar.showProgressBar(getActivity());
        objectTicketListEnquiries.addObserver(this);
        objectGetServiceRequests.addObserver(this);
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
            if (objectTicketListEnquiries.getTicketListResponse().getSuccess()) {
                progressBar.hideProgressBar();
                list.clear();

                for (int i = 0; i < objectTicketListEnquiries.getTicketListResponse().getData().size(); i++) {
                    if (objectTicketListEnquiries.getTicketListResponse().getData().get(i).getTicketStatus().equals("1")) {
                        list.add(objectTicketListEnquiries.getTicketListResponse().getData().get(i));
                    }
                }

                if (list.size() == 0) {
                    tv_NoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                adapter = new Request_Adapter(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            } else if (!objectTicketListEnquiries.getTicketListResponse().getSuccess()) {
                progressBar.hideProgressBar();
                if (objectTicketListEnquiries.getTicketListResponse().getMessage().toString().toLowerCase().trim().equals("showlogin")){
                    Toast.makeText(getActivity(), "Some thing went wrong, Please login again", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), ActivityLoginWithOTP.class));
                    mahindraClappPreference.clearData();
                    getActivity().finish();
                }
            }
        } else if (o instanceof ObjectGetServiceRequests) {
            if (objectGetServiceRequests.getGetServiceRequestsResponse().getSuccess()) {

                if (!dataList.isEmpty()) {
                    dataList.clear();
                }
                progressBar.hideProgressBar();

                for (int i = 0; i < objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().size(); i++) {
                    if (!objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().get(i).getStatus().equals("Open")) {
                        dataList.add(objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().get(i));
                    }
                }

                if (dataList.size() == 0) {
                    tv_NoData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                adapters = new GetServiceRequestsAdapter(dataList, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapters);
            } else if (!objectGetServiceRequests.getGetServiceRequestsResponse().getSuccess()) {
                progressBar.hideProgressBar();

                if (objectGetServiceRequests.getGetServiceRequestsResponse().getMessage().toString().toLowerCase().trim().equals("showlogin")){
                    try {
                        Toast.makeText(getContext(), "Some thing went wrong, Please login again", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), ActivityLoginWithOTP.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userType.equals(Constants.postSales)) {
//            Post-Sales.
            new Service().getServiceRequests(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), "1");
        } else {
//            Post-Handover.
            new Service().getServiceRequests(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), "2");
        }

    }

}