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

import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.activities.ActivityMyPaymentsListing;
import com.mlife.activities.ActivityLogin;
import com.mlife.activities.ActivityHome;
import com.mlife.adapter.Payment_Adapter;
import com.mlife.utils.Constants;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectGetPostPaymentDetails;
import com.mlife.web.model.GetPaymentDetailsDetails;
import com.mlife.web.model.GetPostPaymentDetailsDetails;
import com.mlife.adapter.PostPaymentAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.Response.ObjectGetPaymentDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_PaymentHistory extends Fragment implements DataHolder, Observer {

    @BindView(R.id.rv_PaymentHistory)
    RecyclerView recyclerView;

    @BindView(R.id.tv_noData)
    TextView tv_noData;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipe_container;

    View view;
    boolean temp = false;
    Payment_Adapter adapter;
    PostPaymentAdapter adapters;
    List<String> date = new ArrayList<>();
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<GetPaymentDetailsDetails> list = new ArrayList<>();
    public static List<String> dateFilter = new ArrayList<>();
    List<GetPostPaymentDetailsDetails> lists = new ArrayList<>();
    List<GetPaymentDetailsDetails> filterList = new ArrayList<>();
    ActivityMyPaymentsListing activity_payments = new ActivityMyPaymentsListing();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__payment_history, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        objectGetPaymentDetails.addObserver(this);
        objectGetPostPaymentDetails.addObserver(this);
        progressBar.showProgressBar(getActivity());

        if (ActivityHome.userType.equals(Constants.postSales)) {
            temp = true;
            new Service().getPaymentDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"));
        } else {
            new Service().getPostPaymentDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"));
        }

        swipe_container.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (ActivityHome.userType.equals(Constants.postSales)) {
                            temp = true;
                            new Service().getPaymentDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("bID"));
                        } else {
                            new Service().getPostPaymentDetails(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"));
                        }
                    }
                }
        );
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        swipe_container.setRefreshing(false);

        if (o instanceof ObjectGetPaymentDetails) {

//          "Post-Sales"

            if (objectGetPaymentDetails.getGetPaymentDetailsResponse().getSuccess()) {
                list.clear();
                date.clear();
                date.add(0, "Select");
                progressBar.hideProgressBar();

                for (int i = 0; i < objectGetPaymentDetails.getGetPaymentDetailsResponse().getData().getDetails().size(); i++) {
                    if (objectGetPaymentDetails.getGetPaymentDetailsResponse().getData().getDetails().get(i).getPaid() == 1) {
                        list.add(objectGetPaymentDetails.getGetPaymentDetailsResponse().getData().getDetails().get(i));
                        if (!date.contains(objectGetPaymentDetails.getGetPaymentDetailsResponse().getData().getDetails().get(i).getsDate())) {
                            date.add(objectGetPaymentDetails.getGetPaymentDetailsResponse().getData().getDetails().get(i).getsDate());
                        }
                    }
                }

                if (list.size() == 0) {
                    tv_noData.setVisibility(View.VISIBLE);
                }

                if (temp) {
                    dateFilter.clear();
                    dateFilter.addAll(date);
                    temp = false;
                }

                try {
                    filter("Select");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!objectGetPaymentDetails.getGetPaymentDetailsResponse().getSuccess()) {
                progressBar.hideProgressBar();
                if (objectGetPaymentDetails.getGetPaymentDetailsResponse().getAction().equals("showlogin")){
                    try {
                        if (!getContext().equals(null)) {
                            Toast.makeText(getContext(), "Something went wrong, Please login again", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), ActivityLoginWithOTP.class));
                            mahindraClappPreference.clearData();
                            getActivity().finish();
                        }
                    }catch (Exception e){e.printStackTrace();}
                }else {
//                    Toast.makeText(getActivity(), "Something went wrong, Try again later" , Toast.LENGTH_SHORT).show();
                }
            }

        } else if (o instanceof ObjectGetPostPaymentDetails) {

            if (objectGetPostPaymentDetails.getGetPostPaymentDetailsResponse().getSuccess()) {
                lists.clear();
                progressBar.hideProgressBar();
                for (int i = 0; i < objectGetPostPaymentDetails.getGetPostPaymentDetailsResponse().getData().getDetails().size(); i++) {
                    if (objectGetPostPaymentDetails.getGetPostPaymentDetailsResponse().getData().getDetails().get(i).getPaid().equals("1")) {
                        lists.add(objectGetPostPaymentDetails.getGetPostPaymentDetailsResponse().getData().getDetails().get(i));
                    }
                }
                if (list.size() == 0) {
                    tv_noData.setVisibility(View.VISIBLE);
                }
                adapters = new PostPaymentAdapter(lists, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapters);
            } else if (!objectGetPostPaymentDetails.getGetPostPaymentDetailsResponse().getSuccess()) {
//                Toast.makeText(getActivity(), "Something went wrong, Please login again", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), Activity_Login.class));
            }
        }
    }

    public void filter(String charText) {
        filterList.clear();

        if (charText.equals("Select")) {
            filterList.addAll(list);
        } else {
            for (GetPaymentDetailsDetails wp : list) {
                if (wp.getsDate().equals(charText)) {
                    filterList.add(wp);
                }
            }
        }

        adapter = new Payment_Adapter(filterList, getActivity(), new Payment_Adapter.OnclickListener() {
            @Override
            public void btnOnclick(GetPaymentDetailsDetails detail) {
                Toast.makeText(activity_payments, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

}