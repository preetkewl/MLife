package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityLogin;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_ProjectDetails extends Fragment implements Observer, DataHolder {

    View view;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.tv_ProjectName)
    TextView tvpropertyName;
    @BindView(R.id.tv_OwnerName)
    TextView tvowner;
    @BindView(R.id.tv_Typology)
    TextView tvtypology;
    @BindView(R.id.tv_FlatNumber)
    TextView tvflatNumber;
    @BindView(R.id.tv_Area)
    TextView tvarea;
    @BindView(R.id.tv_Tower)
    TextView tv_Tower;
    @BindView(R.id.tv_Possession)
    TextView tvpossession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_details, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        progressBar.showProgressBar(getActivity());
        new Service().getPropertyDetail(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"));
        objectPropertyDetailsResponse.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            progressBar.hideProgressBar();
            if (objectPropertyDetailsResponse.getPropertyDetailResponse().getSuccess()) {
                tvpropertyName.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getName());
                tvowner.setText(mahindraClappPreference.getData("Name"));
                tvtypology.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getUnitType());
                tvflatNumber.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getHouseNo());
                tvarea.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getArea());
                tv_Tower.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getTower());
                if (objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getPDate() == null) {
                    tvpossession.setText("NA");
                } else {
                    tvpossession.setText(objectPropertyDetailsResponse.getPropertyDetailResponse().getData().getDetails().get(0).getPDate().toString());
                }
            } else if (!objectPropertyDetailsResponse.getPropertyDetailResponse().getSuccess()) {
                Toast.makeText(getContext(), "Something Went Wrong, Try again later.", Toast.LENGTH_SHORT).show();
                if (objectPropertyDetailsResponse.getPropertyDetailResponse().getAction().toLowerCase().equals("showlogin")) {
                    mahindraClappPreference.clearData();
                    startActivity(new Intent(getContext(), ActivityLoginWithOTP.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            } o.deleteObservers();
        } catch (Exception ex) {
        }
    }

}