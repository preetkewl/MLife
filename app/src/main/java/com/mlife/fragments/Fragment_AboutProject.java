package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.activities.ActivityMahindraLifespacesProjects;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectGetProjectDetail;
import com.mlife.adapter.ViewPagerAdapter_AboutProject;
import com.mlife.R;
import com.mlife.utils.DialogDropEnquiry;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_AboutProject extends Fragment implements DataHolder, Observer {

    private int currentPage = 0;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    private ArrayList<String> list = new ArrayList<>();

    @BindView(R.id.vp_aboutProjects)
    ViewPager viewPager;

    @BindView(R.id.tv_Location)
    TextView tv_Location;

    @BindView(R.id.tv_ProjectName)
    TextView tv_ProjectName;

    @BindView(R.id.tv_AboutProject)
    TextView tv_AboutProject;

    @OnClick(R.id.iv_PreviousImage)
    public void iv_PreviousImage() {
        if (currentPage == 0) {
        } else {
            viewPager.setCurrentItem(--currentPage, true);
        }
    }

    @OnClick(R.id.iv_NextImage)
    public void iv_NextImage() {
        if (currentPage == 2) {
        } else {
            viewPager.setCurrentItem(++currentPage, true);
        }
    }

    @BindView(R.id.iv_PreviousImage)
    ImageView iv_Pre;

    @BindView(R.id.iv_NextImage)
    ImageView iv_Nxt;

    @OnClick(R.id.btn_DropEnquery)
    public void btn_DropEnquery() {
        DialogDropEnquiry dropEnquiry = new DialogDropEnquiry();
        dropEnquiry.Dialogbox(getActivity(), ActivityMahindraLifespacesProjects.projectId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__about_project, container, false);
        ButterKnife.bind(this, view);

        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectGetProjectDetail.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().getProjectDetail(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ActivityMahindraLifespacesProjects.projectId);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectGetProjectDetail) {
            progressBar.hideProgressBar();
            if (objectGetProjectDetail.getGetProjectDetailResponse().getSuccess()) {

                if (getActivity() != null) {
                    viewPager.setAdapter(new ViewPagerAdapter_AboutProject(getActivity(), objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getBanners()));
                    if (objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getBanners().size() == 1) {
                        iv_Pre.setVisibility(View.GONE);
                        iv_Nxt.setVisibility(View.GONE);
                    }
                }

                tv_Location.setText(objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getMldlProjectCity());
                tv_ProjectName.setText("About " + objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getMldlProjectName());
                tv_AboutProject.setText(objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getMldlProjectDesc());
            }
        }
    }

}