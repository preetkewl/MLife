package com.mlife.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.ActivityLogin;
import com.mlife.activities.ActivityLoginWithOTP;
import com.mlife.adapter.ProjectImageAdapter;
import com.mlife.adapter.ProjectImageGetterSetter;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;

import java.util.HashSet;
import java.util.Observable;
import java.util.ArrayList;
import java.util.Observer;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class Fragment_ProjectUpdates extends Fragment implements Observer, DataHolder {

    @BindView(R.id.rv_Gallary)
    RecyclerView recyclerView;
    @BindView(R.id.sp_Year)
    Spinner sp_Year;
    @BindView(R.id.tv_NoData)
    TextView tv_NoData;
    @BindView(R.id.tv_disclamer)
    TextView tv_disclamer;

    ProjectImageAdapter adapter;
    ProjectImageGetterSetter getterSetter;
    List<String> dates = new ArrayList<>();
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<ProjectImageGetterSetter> list = new ArrayList<>();
    HashSet<String> constructionUpdatesYears = new HashSet<String>();
    List<ProjectImageGetterSetter> filterList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__project_updates, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        progressBar.showProgressBar(getActivity());
        new Service().getConstructionUpdates(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Property"), "2");
        objectConstructionUpdatesResponse.addObserver(this);
        sp_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filter(sp_Year.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }

    public void filter(String charText) {
        filterList.clear();
        if (charText.length() == 0) {
            filterList.addAll(list);
        } else {
            for (ProjectImageGetterSetter wp : list) {
                if (wp.getDate().equals(charText)) {
                    filterList.add(wp);
                }
            }
            adapter = new ProjectImageAdapter(filterList, getActivity());
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @OnClick(R.id.tv_disclamer)
    public void onClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Disclaimer")
                .setMessage(objectConstructionUpdatesResponse.getConstructionResponse().getData().getMldl_project_constructionupdate_desclaimer());
        alertDialogBuilder.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        progressBar.hideProgressBar();
        if (objectConstructionUpdatesResponse.getConstructionResponse().getSuccess()) {
            if (objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().size() == 0) {
                sp_Year.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                tv_disclamer.setVisibility(View.GONE);
                tv_NoData.setVisibility(View.VISIBLE);
            } else {
                for (int i = 0; i < objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().size(); i++) {
                    getterSetter = new ProjectImageGetterSetter(objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getPath(), objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getDate(), objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getData(), objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getName(), objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getDescription());
                    list.add(getterSetter);
                    constructionUpdatesYears.add(objectConstructionUpdatesResponse.getConstructionResponse().getData().getDetails().get(i).getDate());
                }
                dates.addAll(constructionUpdatesYears);
                if (getContext() != null) {
                    ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, dates);
                    sp_Year.setAdapter(categoriesAdapter);
                }
                try {
                    filter(sp_Year.getSelectedItem().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (!objectConstructionUpdatesResponse.getConstructionResponse().getSuccess()) {
            try {
                Toast.makeText(getContext(), "Something Went Wrong, Try again later.", Toast.LENGTH_SHORT).show();
            }catch (Exception e){}
            if (objectConstructionUpdatesResponse.getConstructionResponse().getAction().toLowerCase().equals("showlogin")) {
                if (getContext() != null) {
                    startActivity(new Intent(getContext(), ActivityLoginWithOTP.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    mahindraClappPreference.clearData();
                    getActivity().finish();
                }
            }
        }o.deleteObservers();
    }
}