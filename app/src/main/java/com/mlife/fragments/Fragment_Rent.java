package com.mlife.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.My_Community.Classified.Activity_ClassifiedView;
import com.mlife.activities.My_Community.Classified.Activity_ManageClassified;
import com.mlife.activities.My_Community.Classified.Activity_PostNewClassified;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.Response.ObjectOfferList;
import com.mlife.adapter.RentSellAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_Rent extends Fragment implements Observer, DataHolder {

    @BindView(R.id.rv_Rent)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataRent)
    TextView tv_NoDataRent;

    View view;
    RentSellAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @OnClick(R.id.fab_Rent)
    public void onFabClick() {
        startActivity(new Intent(getActivity(), Activity_PostNewClassified.class).putExtra("type", "2"));
    }

    @OnClick(R.id.fabmyclassifiedandroid)
    public void onClick() {

        if (objectOfferListRent.getOfferListResponse().getData().size() > 0) {
            startActivity(new Intent(getActivity(), Activity_ManageClassified.class).putExtra("check", "2"));
        } else {
            Toast.makeText(getContext(), "No classified to manage", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        progressBar.showProgressBar(getActivity());
        objectOfferListRent.addObserver(this);
        new Service().offerList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__rent, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), Activity_ClassifiedView.class).putExtra("Price", objectOfferListRent.getOfferListResponse().getData().get(position).getOfferPrice()).putExtra("PostedBy", objectOfferListRent.getOfferListResponse().getData().get(position).getOfferPostedBy()).putExtra("PostedDate", objectOfferListRent.getOfferListResponse().getData().get(position).getDate()).putExtra("Heading", objectOfferListRent.getOfferListResponse().getData().get(position).getOfferTitle()).putExtra("Phone", objectOfferListRent.getOfferListResponse().getData().get(position).getOfferContact()).putExtra("Dec", objectOfferListRent.getOfferListResponse().getData().get(position).getOfferDec()).putExtra("imageList", (Serializable) objectOfferListRent.getOfferListResponse().getData().get(position).getOfferAttachmentName()));
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ObjectOfferList) {
            progressBar.hideProgressBar();
            tv_NoDataRent.setVisibility(View.GONE);
            if (objectOfferListRent.getOfferListResponse().getSuccess()) {
                if (objectOfferListRent.getOfferListResponse().getData().size() == 0) {
                    tv_NoDataRent.setVisibility(View.VISIBLE);
                }
                adapter = new RentSellAdapter(objectOfferListRent.getOfferListResponse().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Fragment_Rent.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Fragment_Rent.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}