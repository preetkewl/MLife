package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.adapter.LocalOffersGetterSetter;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectOfferList;
import com.mlife.adapter.LocalOffersAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_LocalOffers extends Fragment implements Observer, DataHolder {

    View view;
    LocalOffersAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<LocalOffersGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_localOffers)
    RecyclerView recyclerView;

    @BindView(R.id.tv_No_Data)
    TextView tv_No_Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__local_offers, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        progressBar.showProgressBar(getActivity());
        new Service().offerList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "0");
        objectOfferListAdmin.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectOfferList) {
            progressBar.hideProgressBar();
            list.clear();
            if (objectOfferListAdmin.getOfferListResponse().getSuccess()) {

                if (objectOfferListAdmin.getOfferListResponse().getData().size()==0){
                    tv_No_Data.setVisibility(View.VISIBLE);
                }

                adapter = new LocalOffersAdapter(objectOfferListAdmin.getOfferListResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }
    }
}
