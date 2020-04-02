package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mlife.R;

import butterknife.ButterKnife;

public class Activity_Maps extends FragmentActivity implements OnMapReadyCallback {

    double latitued, longitued;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        latitued = intent.getDoubleExtra("Lat",30.727630);
        longitued = intent.getDoubleExtra("Long",17.727630);


    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(latitued, longitued)));
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitued, longitued)));
        map.setMaxZoomPreference(18);
        map.setMinZoomPreference(6);
        map.setTrafficEnabled(true);
        map.setBuildingsEnabled(false);
    }
}