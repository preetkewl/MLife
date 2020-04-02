package com.mlife.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mlife.activities.Edit_Profile.ActivityViewProfile;
import com.mlife.utils.DialogProgressBar;
import com.mlife.web.api.Service;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.holder.Response.ObjectResidentList;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.model.ResidentListDetails;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Resident extends Fragment implements DataHolder, Observer {

    View view;
    ResidendAdapter apapter;
    DialogProgressBar progressBar = new DialogProgressBar();
    MahindraClappPreference mahindraClappPreference;

    @BindView(R.id.rv_resident)
    RecyclerView recyclerView;

    @BindView(R.id.tv_NoData)
    TextView tv_NoData;

    @BindView(R.id.et_SearchResident)
    EditText et_SearchResident;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__resident, container, false);
        ButterKnife.bind(this, view);

        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        progressBar.showProgressBar(getActivity());
        new Service().residentList(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", mahindraClappPreference.getData("Project"), "", mahindraClappPreference.getData("Name"));
        objectResidentList.addObserver(this);
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getContext(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (objectResidentList.getResidentListResponse().getData().getDetails().get(position).getHasProfile()) {
                    startActivity(new Intent(getActivity(), ActivityViewProfile.class).putExtra("profileId", objectResidentList.getResidentListResponse().getData().getDetails().get(position).getProfileId()));
                } else {
                    Toast.makeText(getActivity(), "User has not login into the application", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        et_SearchResident.setSingleLine(true);
        et_SearchResident.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_SearchResident.getText().toString().toLowerCase(Locale.getDefault());

                try {
                    apapter.filter(text);
                }catch (Exception ex){
                    Toast.makeText(getContext(), "Something went wrong, Try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

        });
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof ObjectResidentList) {
            progressBar.hideProgressBar();
            if (objectResidentList.getResidentListResponse().getSuccess()) {
                if (objectResidentList.getResidentListResponse().getData().getDetails().size() == 0) {
                    tv_NoData.setVisibility(View.VISIBLE);
                } else {
                    apapter = new ResidendAdapter(objectResidentList.getResidentListResponse().getData().getDetails(), getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(apapter);
                }
            }
        }
    }

    public class ResidendAdapter extends RecyclerView.Adapter<ResidendAdapter.MyViewHolder> {
        Context context;

        private java.util.List<ResidentListDetails> List;
        private java.util.List<ResidentListDetails> arraylist;


        @Override
        public ResidendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_managmentlist, parent, false);
            return new ResidendAdapter.MyViewHolder(itemView);
        }

        public ResidendAdapter(java.util.List<ResidentListDetails> DataList, FragmentActivity activity) {
            this.List = DataList;
            arraylist = new ArrayList<>(List);
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView Heading, Position;

            public MyViewHolder(View view) {
                super(view);
                Heading = (TextView) view.findViewById(R.id.tv_Name);
                Position = (TextView) view.findViewById(R.id.tv_Position);
            }

        }

        @Override
        public void onBindViewHolder(final ResidendAdapter.MyViewHolder holder, final int position) {
            final ResidentListDetails detail = List.get(position);
            holder.Heading.setText(detail.getName());
            holder.Position.setVisibility(View.GONE);
        }

        @Override
        public int getItemCount() {
            return List.size();
        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            List.clear();

            if (charText.length() == 0) {
                List.addAll(arraylist);
            } else {
                for (ResidentListDetails wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        List.add(wp);
                    }
                }
            }

            if (List.size() == 0) {
                tv_NoData.setVisibility(View.VISIBLE);
            } else {
                tv_NoData.setVisibility(View.GONE);
            }

            notifyDataSetChanged();
        }


    }

}