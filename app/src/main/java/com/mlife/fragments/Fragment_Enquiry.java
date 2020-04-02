package com.mlife.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mlife.activities.ActivityMyRequestNewTicket;
import com.mlife.web.holder.DataHolder;
import com.mlife.R;
import com.mlife.utils.DialogRequestCallback;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_Enquiry extends Fragment implements Observer, DataHolder {

    DialogRequestCallback requestCallback = new DialogRequestCallback();
    View view;

    @OnClick(R.id.fab)
    public void onClickDrop() {
        startActivity(new Intent(getActivity(), ActivityMyRequestNewTicket.class).putExtra("type","0"));
    }

    @OnClick(R.id.fabcallback)
    public void onClickCallBack() {
        requestCallback.Dialogbox(getActivity());
    }

    @BindView(R.id.tv_OpenTicket)
    TextView tvopenTickek;

    @BindView(R.id.tv_CloseTicket)
    TextView tvcloseTickek;

    @OnClick(R.id.tv_OpenTicket)
    public void changeColorOpen() {
        tvopenTickek.setTextColor(Color.parseColor("#ffffff"));
        tvopenTickek.setBackgroundColor(Color.parseColor("#cc0033"));
        tvcloseTickek.setTextColor(Color.parseColor("#cc0033"));
        tvcloseTickek.setBackgroundColor(Color.parseColor("#ffffff"));

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameEnq, new Fragment_Enquiry_Open());
        transaction.commit();
    }

    @OnClick(R.id.tv_CloseTicket)
    public void changeColorClose() {
        tvcloseTickek.setTextColor(Color.parseColor("#ffffff"));
        tvcloseTickek.setBackgroundColor(Color.parseColor("#cc0033"));
        tvopenTickek.setTextColor(Color.parseColor("#cc0033"));
        tvopenTickek.setBackgroundColor(Color.parseColor("#ffffff"));

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameEnq, new Fragment_Enquiry_Close());
        transaction.commit();   }

    @BindView(R.id.frameEnq)
    FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__enquiry, container, false);
        ButterKnife.bind(this, view);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameEnq, new Fragment_Enquiry_Open());
        transaction.commit();
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}