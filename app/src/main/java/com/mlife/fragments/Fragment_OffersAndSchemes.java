package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.adapter.Offers_Adapter;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.adapter.OffersGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectProjectOffers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_OffersAndSchemes extends Fragment implements DataHolder, Observer {

    View view;
    Offers_Adapter offers_adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<OffersGetterSetter> list = new ArrayList<>();

    @BindView(R.id.rv_OffersAndNews)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__offers_and_schemes, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectProjectOffers.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().projectOffers(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ActivityMahindraLifespacesProjects.projectId);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectProjectOffers) {
            progressBar.hideProgressBar();
            if (objectProjectOffers.getProjectOffersResponse().getSuccess()) {
                offers_adapter = new Offers_Adapter(objectProjectOffers.getProjectOffersResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(offers_adapter);
            }
        }

    }
}