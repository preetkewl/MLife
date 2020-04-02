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
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.adapter.Request_Adapter;
import com.mlife.utils.Constants;
import com.mlife.web.api.Service;
import com.mlife.web.model.GetServiceRequestsDetails;
import com.mlife.web.model.TicketListData;
import com.mlife.activities.ActivityLogin;
import com.mlife.adapter.GetServiceRequestsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetServiceRequests;
import com.mlife.web.holder.Response.ObjectTicketList;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mlife.activities.ActivityHome.userType;

public class Fragment_Enquiry_Open extends Fragment implements Observer, DataHolder {

    View view;
    Request_Adapter adapter;
    GetServiceRequestsAdapter adapters;
    DialogProgressBar progressBar = new DialogProgressBar();
    List<TicketListData> list = new ArrayList<>();
    MahindraClappPreference mahindraClappPreference;
    List<GetServiceRequestsDetails> dataList = new ArrayList<>();

    @BindView(R.id.rv_Myenq)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment__enquiry__open, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectTicketListEnquiries.addObserver(this);
        objectGetServiceRequests.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectTicketList) {
            if (objectTicketListEnquiries.getTicketListResponse().getSuccess()) {
                list.clear();
                progressBar.hideProgressBar();
                for (int i = 0; i < objectTicketListEnquiries.getTicketListResponse().getData().size(); i++) {
                    if (objectTicketListEnquiries.getTicketListResponse().getData().get(i).getTicketStatus().equals("0")) {
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
                if (objectTicketListEnquiries.getTicketListResponse().getAction().toString().toLowerCase().trim().equals("showlogin")) {
                    startActivity(new Intent(getActivity(), ActivityLoginWithOTP.class));
                    getActivity().finish();
                    mahindraClappPreference.clearData();
                }
                Toast.makeText(getActivity(), "Some thing went wrong, Try again later.", Toast.LENGTH_SHORT).show();
            }

        } else if (o instanceof ObjectGetServiceRequests) {

            if (objectGetServiceRequests.getGetServiceRequestsResponse().getSuccess()) {

                if (!dataList.isEmpty()) {
                    dataList.clear();
                }

                progressBar.hideProgressBar();

                for (int i = 0; i < objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().size(); i++) {
                    try {
                        if (objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().get(i).getStatus().equals("Open")) {
                            dataList.add(objectGetServiceRequests.getGetServiceRequestsResponse().getData().getDetails().get(i));
                        }
                    } catch (Exception ec) {
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

                if (objectGetServiceRequests.getGetServiceRequestsResponse().getAction().toString().toLowerCase().trim().equals("showlogin")) {
                    mahindraClappPreference.clearData();
                    startActivity(new Intent(getActivity(), ActivityLoginWithOTP.class));
                    getActivity().finish();
                }
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Some thing went wrong, Try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.showProgressBar(getActivity());
        if (userType.equals(Constants.postSales)) {
//            Post-Sales.
            new Service().getServiceRequests(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), "1");
        } else {
//            Post-Handover.
            new Service().getServiceRequests(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"), "2");
        }
    }

}