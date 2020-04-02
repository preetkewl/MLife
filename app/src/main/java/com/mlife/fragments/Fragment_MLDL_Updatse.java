package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.adapter.ProjectImageAdapter;
import com.mlife.utils.Constants;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.adapter.ProjectImageGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_MLDL_Updatse extends Fragment implements Observer, DataHolder {

    @BindView(R.id.tv_disclamer)
    TextView tv_disclamer;

    @BindView(R.id.rv_Gallary)
    RecyclerView recyclerView;

    @BindView(R.id.sp_Year)
    Spinner sp_Year;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    View view;
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
        View view = inflater.inflate(R.layout.fragment_fragment__mldl__updatse, container, false);
        ButterKnife.bind(this, view);
        sp_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filter(sp_Year.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        progressBar.showProgressBar(getActivity());
        new Service().projectConstructionUpdate(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ActivityMahindraLifespacesProjects.projectId);
        objectProjectConstructionUpdate.addObserver(this);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getSuccess()) {
            try {
                progressBar.hideProgressBar();
                for (int i = 0; i < objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().size(); i++) {
                    getterSetter = new ProjectImageGetterSetter(Constants.consUpdates + objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getImage(), objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getDate(),Constants.consUpdates + objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getImage(), objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getTitle(), objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getDescription());
                    constructionUpdatesYears.add(objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().get(i).getDate());
                    list.add(getterSetter);
                }
                if (objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getConstruction().size() == 0){
                    tv_NoData.setVisibility(View.VISIBLE);
                }
                dates.addAll(constructionUpdatesYears);
                if (getActivity() != null) {
                    ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, dates);
                    sp_Year.setAdapter(categoriesAdapter);
                }
//                tv_disclamer.setText("Disclaimer: " + objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getMldlProjectConstructionupdateDesclaimer());
                filter(sp_Year.getSelectedItem().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    @OnClick(R.id.tv_disclamer)
    public void onClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Disclaimer").setMessage(objectProjectConstructionUpdate.getProjectConstructionUpdateResponse().getData().get(0).getMldlProjectConstructionupdateDesclaimer());
        alertDialogBuilder.show();
    }
}