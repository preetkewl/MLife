package com.mlife.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlife.adapter.DocsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddedValueList;
import com.mlife.web.model.AddedValueListData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_InteriorDesign extends Fragment implements Observer, DataHolder {

    @BindView(R.id.rv_interior)
    RecyclerView recyclerView;

    View view;
    DocsAdapter adapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<AddedValueListData> list = new ArrayList<>();


    // TODO: Rename and change types and number of parameters
    public static Fragment_InteriorDesign newInstance(String param1, String param2) {
        Fragment_InteriorDesign fragment = new Fragment_InteriorDesign();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__interior_design, container, false);

        ButterKnife.bind(this, view);
        progressBar.showProgressBar(getActivity());
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
//        new Service().addedValueList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "0");
        objectAddedValueListInterior.addObserver(this);
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
            if (objectAddedValueListInterior.getAddedValueListData().getSuccess()) {
//                adapter = new DocsAdapter(objectAddedValueListInterior.getAddedValueListData().getData(), getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }
            progressBar.hideProgressBar();
        }
    }
}