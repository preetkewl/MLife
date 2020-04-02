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

import com.mlife.activities.ActivityHome;
import com.mlife.activities.My_Community.Classified.Activity_ClassifiedView;
import com.mlife.activities.My_Community.Classified.Activity_ManageClassified;
import com.mlife.activities.My_Community.Classified.Activity_PostNewClassified;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectOfferList;
import com.mlife.adapter.RentSellAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_Sell extends Fragment implements Observer, DataHolder {

    View view;
    RentSellAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_Sell)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataSell)
    TextView tv_NoDataSell;

    @OnClick(R.id.fab_Sell)
    public void onFabClick() {
        startActivity(new Intent(getActivity(), Activity_PostNewClassified.class).putExtra("type", "1"));
    }

    @OnClick(R.id.fabmyclassifiedandroid)
    public void onClick() {

        if (objectOfferListSell.getOfferListResponse().getData().size() > 0){
            startActivity(new Intent(getActivity(), Activity_ManageClassified.class).putExtra("check","1"));
        }else {
            Toast.makeText(getContext(), "No classified to manage", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onStart() {
        super.onStart();
        objectOfferListSell.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().offerList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "1");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__sell, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
       recyclerView.addOnItemTouchListener(new ActivityHome.RecyclerTouchListener(getActivity(), recyclerView, new ActivityHome.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {
                    startActivity(new Intent(getActivity(), Activity_ClassifiedView.class).putExtra("Price", objectOfferListSell.getOfferListResponse().getData().get(position).getOfferPrice()).putExtra("PostedBy", objectOfferListSell.getOfferListResponse().getData().get(position).getOfferPostedBy()).putExtra("PostedDate", objectOfferListSell.getOfferListResponse().getData().get(position).getDate()).putExtra("Heading", objectOfferListSell.getOfferListResponse().getData().get(position).getOfferTitle()).putExtra("Phone", objectOfferListSell.getOfferListResponse().getData().get(position).getOfferContact()).putExtra("Dec", objectOfferListSell.getOfferListResponse().getData().get(position).getOfferDec()).putExtra("imageList", (Serializable) objectOfferListSell.getOfferListResponse().getData().get(position).getOfferAttachmentName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectOfferList) {
            progressBar.hideProgressBar();
            tv_NoDataSell.setVisibility(View.GONE);
            if (objectOfferListSell.getOfferListResponse().getSuccess()) {
                if (objectOfferListSell.getOfferListResponse().getData().size() == 0) {
                    tv_NoDataSell.setVisibility(View.VISIBLE);
                }
                adapter = new RentSellAdapter(objectOfferListSell.getOfferListResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }
    }
}