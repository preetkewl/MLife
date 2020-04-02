package com.mlife.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.adapter.SiteVisit_Adapter;
import com.mlife.web.api.Service;
import com.mlife.adapter.SiteVisitGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectCancelSiteVisit;
import com.mlife.web.holder.Response.ObjectLoadSiteVisits;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Schedule extends Fragment implements Observer, DataHolder {

    View view;
    String id = "";
    SiteVisit_Adapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    SiteVisitGetterSetter siteVisitGetterSetter;
    MahindraClappPreference mahindraClappPreference;
    List<SiteVisitGetterSetter> list = new ArrayList<>();

    @BindView(R.id.tv_NoDataViteVisit)
    TextView tv_NoDataViteVisit;

    @BindView(R.id.rv_Schedule)
    RecyclerView recyclerView;

    @Override
    public void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__schedule, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectCancelSiteVisit.addObserver(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        objectLoadSiteVisits.addObserver(this);
        progressBar.showProgressBar(getActivity());
        new Service().loadScheduleVisit(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android");
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!list.isEmpty()) {
            list.clear();
        }

        if (o instanceof ObjectLoadSiteVisits) {
            progressBar.hideProgressBar();
            if (objectLoadSiteVisits.getLoadSiteVisitsResponse().getSuccess()) {
                for (int i = 0; i < objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().size(); i++) {
                    if (objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getStatus().equals("1") || objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getStatus().equals("0")) {
                        siteVisitGetterSetter = new SiteVisitGetterSetter(objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getId(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getProperty(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getDate(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getTimeSlot(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getLat(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getLong(), objectLoadSiteVisits.getLoadSiteVisitsResponse().getData().get(i).getStatus());
                        list.add(siteVisitGetterSetter);
                    }
                }
                if (list.size() == 0) {
                    tv_NoDataViteVisit.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                adapter = new SiteVisit_Adapter(list, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Something went wrong, try again later", Toast.LENGTH_SHORT).show();
            }
        } else if (o instanceof ObjectCancelSiteVisit) {
            CancelVisit.progressBar.hideProgressBar();
            for (int i = 0; i < objectCancelSiteVisit.getCancelSiteVisitResponse().getData().size(); i++) {
                if (objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getStatus().equals("1") || objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getStatus().equals("0")) {
                    siteVisitGetterSetter = new SiteVisitGetterSetter(objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getId(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getProperty(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getDate(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getTimeSlot(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getLat(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getLong(), objectCancelSiteVisit.getCancelSiteVisitResponse().getData().get(i).getStatus());
                    list.add(siteVisitGetterSetter);
                }
            }
            if (list.size() == 0) {
                tv_NoDataViteVisit.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
            adapter = new SiteVisit_Adapter(list, getActivity());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
//        o.deleteObservers();
    }

    public static class CancelVisit {

        Activity activity;
        Button btn_Yes, btn_No;
        public static DialogProgressBar progressBar = new DialogProgressBar();
        MahindraClappPreference mahindraClappPreference;

        public void Dialogbox(final Activity activity, final String id) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_cancelvisit);
            dialog.setCanceledOnTouchOutside(true);
            this.activity = activity;
            mahindraClappPreference = MahindraClappPreference.getInstance(activity);

            btn_Yes = (Button) dialog.findViewById(R.id.btn_yes);
            btn_No = (Button) dialog.findViewById(R.id.btn_no);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams((int) (activity.getResources().getDisplayMetrics().widthPixels * 0.90), WindowManager.LayoutParams.MATCH_PARENT);
            flp.gravity = Gravity.CENTER;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            lp.gravity = Gravity.CENTER;

            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.WhiteTrans)));
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            lp.gravity = Gravity.CENTER;
            dialog.getWindow().setAttributes(lp);
            dialog.show();

            btn_Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.showProgressBar(activity);
                    new Service().cancleVisit(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", id);
                    dialog.dismiss();
                }
            });

            btn_No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        }

    }

}