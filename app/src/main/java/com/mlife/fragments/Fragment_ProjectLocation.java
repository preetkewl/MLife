package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectProjectLocation;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_ProjectLocation extends Fragment implements OnMapReadyCallback, DataHolder, Observer {

    View view;
    ViewGroup parent;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.tv_ProjectLocation)
    TextView tv_ProjectLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }

        try {
            view = inflater.inflate(R.layout.fragment_fragment__project_location, container, false);
            ButterKnife.bind(this, view);
            mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
            objectProjectLocation.addObserver(this);
            progressBar.showProgressBar(getActivity());
            new Service().projectLocation(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ActivityMahindraLifespacesProjects.projectId);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectProjectLocation) {
            progressBar.hideProgressBar();
            if (objectProjectLocation.getProjectLocationResponse().getSuccess()) {
                tv_ProjectLocation.setText(objectProjectLocation.getProjectLocationResponse().getData().get(0).getMldlProjectLocation());
                LatLng sydney = new LatLng(Double.valueOf(objectProjectLocation.getProjectLocationResponse().getData().get(0).getMldlProjectLocationLatitude()), Double.valueOf(objectProjectLocation.getProjectLocationResponse().getData().get(0).getMldlProjectLocationLongitude()));
                mMap.addMarker(new MarkerOptions().position(sydney).title(""));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
        }
    }
}
