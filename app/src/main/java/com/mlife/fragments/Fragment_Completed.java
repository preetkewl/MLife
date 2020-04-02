package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.adapter.SiteVisitGetterSetter;
import com.mlife.adapter.SiteVisit_Adapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Completed extends Fragment  implements Observer, DataHolder {

    @BindView(R.id.rv_SiteCompleted)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataViteVisit)
    TextView tv_NoDataViteVisit;

    View view;
    SiteVisit_Adapter adapter;
    SiteVisitGetterSetter siteVisitGetterSetter;
    MahindraClappPreference mahindraClappPreference;
    public List<SiteVisitGetterSetter> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__completed, container, false);
        ButterKnife.bind(this,view);

        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
//        new Service().loadScheduleVisit(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
        objectLoadSiteVisits.addObserver(this);
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!list.isEmpty()) {
            list.clear();
        }

        if (objectLoadSiteVisits.getLoadSiteVisitsResponse().getSuccess()) {
            for (int i = 0; i < objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().size(); i++) {
                if (objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getStatus().equals("2")) {
                    siteVisitGetterSetter = new SiteVisitGetterSetter(objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getId(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getProperty(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getDate(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getTimeSlot(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getLat(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getLong(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getStatus());
                    list.add(siteVisitGetterSetter);
                }
            }
                if (list.size() == 0){
                    tv_NoDataViteVisit.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                adapter = new SiteVisit_Adapter(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

        }
        o.deleteObservers();
    }
}