package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.adapter.ProjectImageGetterSetter;
import com.mlife.utils.Constants;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.adapter.GalleryImages;
import com.mlife.R;
import com.mlife.utils.DialogDropEnquiry;
import com.mlife.web.holder.Response.ObjectProjectGallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_ProjectGallery extends Fragment implements DataHolder, Observer {

    @BindView(R.id.rv_Gallary)
    RecyclerView recyclerView;

    GalleryImages adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    ProjectImageGetterSetter getterSetter;
    MahindraClappPreference mahindraClappPreference;
    List<ProjectImageGetterSetter> list = new ArrayList<>();

    @OnClick(R.id.fab_Drop)
    public void onFabClick(View view) {
        DialogDropEnquiry dropEnquiry = new DialogDropEnquiry();
        dropEnquiry.Dialogbox(getActivity(), ActivityMahindraLifespacesProjects.projectId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__project_gallery, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectProjectGallery.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().projectGallery(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ActivityMahindraLifespacesProjects.projectId);
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectProjectGallery) {
            progressBar.hideProgressBar();
            if (objectProjectGallery.getProjectGalleryResponse().getSuccess()) {

                for (int i = 0; i < objectProjectGallery.getProjectGalleryResponse().getData().get(0).getGallery().size(); i++) {
                    getterSetter = new ProjectImageGetterSetter(Constants.gallery + objectProjectGallery.getProjectGalleryResponse().getData().get(0).getGallery().get(i).getImage(), "", Constants.gallery + objectProjectGallery.getProjectGalleryResponse().getData().get(0).getGallery().get(i).getImage(), objectProjectGallery.getProjectGalleryResponse().getData().get(0).getGallery().get(i).getTitle(), objectProjectGallery.getProjectGalleryResponse().getData().get(0).getGallery().get(i).getDescription());
                    list.add(getterSetter);
                }
                adapter = new GalleryImages(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }
    }
}