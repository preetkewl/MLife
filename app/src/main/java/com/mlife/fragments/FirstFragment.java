package com.mlife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mlife.activities.Extras.Activity_ThankYou;
import com.mlife.activities.ActivityValueAddedServiceRequirements;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectAddUserVas;
import com.mlife.web.holder.Response.ObjectAddedValueList;
import com.mlife.adapter.DocsAdapter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.model.AddedValueListData;
import com.mlife.web.model.AddedValueListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstFragment extends Fragment implements Observer, DataHolder {

    View view;
    private int page;
    private String id;
    DocsAdapter adapter;
    Boolean isVisible = false;
    public List<String> vasId;
    private ObjectAddedValueList AddedValue;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;
    List<AddedValueListData> list = new ArrayList<>();
    AddedValueListResponse addedValueListData;
    FirstFragment firstFragment;

    @BindView(R.id.rv_interior)
    RecyclerView recyclerView;

    @BindView(R.id.ll_NoItemFound)
    LinearLayout ll_NoItemFound;

    @BindView(R.id.ll_Listing)
    LinearLayout ll_Listing;

    @OnClick(R.id.btn_ShareIntrest)
    public void btn_ShareIntrest() {
        startActivity(new Intent(getActivity(), ActivityValueAddedServiceRequirements.class));
    }

    @OnClick(R.id.btn_NewIntrest)
    public void btn_NewIntrest() {
        startActivity(new Intent(getActivity(), ActivityValueAddedServiceRequirements.class));
    }

    @OnClick(R.id.btn_registerIntrest)
    public void registerIntrest() {
        if (!vasId.isEmpty()) {
            String ids = vasId.toString();
            ids = ids.substring(1, ids.length() - 1);
            progressBar.showProgressBar(getActivity());
            new Service().addUserVas(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", ids, this);
        } else {
            Toast.makeText(getActivity(), "Please select atleast one option", Toast.LENGTH_SHORT).show();
        }
    }

    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
        page = getArguments().getInt("someInt", 0);
        id = getArguments().getString("someTitle");
        mahindraClappPreference = MahindraClappPreference.getInstance(getContext());
        objectAddedValueListInterior.addObserver(this);
        objectAddUserVas.addObserver(this);
        progressBar.showProgressBar(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        vasId = new ArrayList<>();
        firstFragment = this;
        new Service().addedValueList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), id, this);
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

            if (arg == this) {
                 addedValueListData = objectAddedValueListInterior.getAddedValueListData();

                if (objectAddedValueListInterior.getAddedValueListData().getSuccess()) {
                    progressBar.hideProgressBar();

                    if (objectAddedValueListInterior.getAddedValueListData().getData().size() == 0) {
                        ll_NoItemFound.setVisibility(View.VISIBLE);
                        ll_Listing.setVisibility(View.GONE);
                    }

                    try {
                        AddedValue = (ObjectAddedValueList) objectAddedValueListInterior.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                    adapter = new DocsAdapter(AddedValue.getAddedValueListData().getData(), firstFragment, AddedValue);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }
        } else if (o instanceof ObjectAddUserVas) {
            progressBar.hideProgressBar();

            if (arg == this) {
                {
                    if (objectAddUserVas.getAddUserVasResponse().getSuccess()) {
                        startActivity(new Intent(getActivity(), Activity_ThankYou.class).putExtra("Title", "Value added services").putExtra("Heading", "Thank You").putExtra("Detail", "Your request has been received \n our representative will contact you soon.").putExtra("Image", R.mipmap.thankyouiconclubhouse).putExtra("callBack", "Vas"));
                    } else {
                        Toast.makeText(getActivity(), objectAddUserVas.getAddUserVasResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}