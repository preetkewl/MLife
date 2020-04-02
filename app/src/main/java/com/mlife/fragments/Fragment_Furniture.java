package com.mlife.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.utils.Constants;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddedValueList;
import com.mlife.adapter.DocsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.model.AddedValueListData;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Furniture extends Fragment implements Observer, DataHolder {

    View view;
    DocsAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_furniture)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoDataFurniture)
    TextView tv_NoDataFurniture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__furniture, container, false);
        ButterKnife.bind(this, view);
        progressBar.showProgressBar(getActivity());
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
//        new Service().addedValueList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "1",this);
        objectAddedValueListFurniture.addObserver(this);

        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AddedValueListData addedValueListData = objectAddedValueListFurniture.getAddedValueListData().getData().get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.pdf + addedValueListData.getValueAddedserviceAttachment()));
                startActivity(browserIntent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectAddedValueList) {
            if (objectAddedValueListFurniture.getAddedValueListData().getSuccess()) {
                progressBar.hideProgressBar();
                if (objectAddedValueListFurniture.getAddedValueListData().getData().size() == 0) {
                    tv_NoDataFurniture.setVisibility(View.VISIBLE);
                } else {
                    tv_NoDataFurniture.setVisibility(View.GONE);
                }
//                adapter = new DocsAdapter(objectAddedValueListFurniture.getAddedValueListData().getData(), fi);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
        }
    }
}