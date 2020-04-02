package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlife.adapter.AmenitiesListingAdapter;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.adapter.ViewPagerAdapter_ProjectAmenities;
import com.mlife.R;
import com.mlife.utils.DialogDropEnquiry;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectProjectAmenities;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mlife.activities.ActivityMahindraLifespacesProjects.projectId;

public class Fragment_ProjectAmenities extends Fragment implements DataHolder, Observer {

    private int currentPage = 0;
    DialogProgressBar progressBar = new DialogProgressBar();
    private ArrayList<Integer> Array = new ArrayList<Integer>();

    @BindView(R.id.rv_Amenities)
    RecyclerView recyclerView;

    @BindView(R.id.vp_aboutProjects)
    ViewPager viewPager;

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

    @BindView(R.id.tv_Location)
    TextView tv_Location;

    @OnClick(R.id.btn_DropEnquery)
    public void btn_DropEnquery() {
        DialogDropEnquiry dropEnquiry = new DialogDropEnquiry();
        dropEnquiry.Dialogbox(getActivity(), projectId);
    }

    AmenitiesListingAdapter adapter;
    MahindraClappPreference mahindraClappPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__project_amenities, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectProjectAmenities.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().projectAmenities(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", projectId);
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

        if (o instanceof ObjectProjectAmenities) {
            try {
                progressBar.hideProgressBar();
                if (objectProjectAmenities.getProjectAmenitiesResponse().getSuccess()) {

                    tv_Location.setText(objectProjectAmenities.getProjectAmenitiesResponse().getData().get(0).getMldlProjectCity());
                    if (getActivity() != null) {
                        viewPager.setAdapter(new ViewPagerAdapter_ProjectAmenities(getActivity(), objectProjectAmenities.getProjectAmenitiesResponse().getData().get(0).getBanners()));
                        if (objectGetProjectDetail.getGetProjectDetailResponse().getData().get(0).getBanners().size() == 1) {
                            iv_Pre.setVisibility(View.GONE);
                            iv_Nxt.setVisibility(View.GONE);
                        }
                    }
                    adapter = new AmenitiesListingAdapter(objectProjectAmenities.getProjectAmenitiesResponse().getData().get(0).getAmentiesname(), getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}