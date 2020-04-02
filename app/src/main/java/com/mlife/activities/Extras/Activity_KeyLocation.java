package com.mlife.activities.Extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mlife.activities.ActivityHome;
import com.mlife.activities.ActivityNotification;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;

import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_KeyLocation extends AppCompatActivity implements OnMapReadyCallback,DataHolder, Observer {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;


    protected GeoDataClient mGeoDataClient;
    MahindraClappPreference mahindraClappPreference;


    String[] mPlaceType = null;
    String[] mPlaceTypeName = null;
    private PlaceDetectionClient mPlaceDetectionClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__key_location);
        ButterKnife.bind(this);
        mahindraClappPreference = MahindraClappPreference.getInstance(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        objectKeyLocation.addObserver(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        new Service().getKeyLocationService(-34,151);
    }


    @OnClick(R.id.iv_Back_ManageDitePlan)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.iv_Notification)
    public void notificationClick() {
        startActivity(new Intent(getApplicationContext(), ActivityNotification.class));
    }

    @OnClick(R.id.iv_MahindraLogo)
    public void logoClick() {
        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
    }

    @Override
    public void update(Observable o, Object arg) {
        objectKeyLocation.getKeyLocation();
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    }
}
