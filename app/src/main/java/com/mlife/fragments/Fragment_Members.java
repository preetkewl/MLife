package com.mlife.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mlife.activities.Edit_Profile.ActivityViewProfile;
import com.mlife.web.api.Service;
import com.mlife.activities.My_Community.Groups.Activity_GroupDetails;
import com.mlife.adapter.MembersAdapter;
import com.mlife.adapter.MembersGetterSetter;
import com.mlife.R;
import com.mlife.utils.MahindraClappPreference;
import com.mlife.web.holder.DataHolder;
import com.mlife.web.model.MembersbyGroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Members extends Fragment implements Observer, DataHolder {

    View view;
    MembersAdapter apapter;
    MahindraClappPreference mahindraClappPreference;
    List<MembersGetterSetter> list = new ArrayList<>();
    private java.util.List<MembersbyGroupData> dataList = new ArrayList<>();

    @BindView(R.id.rv_members)
    RecyclerView recyclerView;

    @BindView(R.id.et_SearchMembers)
    EditText et_SearchMembers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__members, container, false);
        ButterKnife.bind(this, view);
        mahindraClappPreference = MahindraClappPreference.getInstance(getActivity());
        new Service().membersbyGroup(mahindraClappPreference.getData("UserId"), mahindraClappPreference.getData("Token"), mahindraClappPreference.getData("DeviceToken"), "Android", Activity_GroupDetails.groupId);
        objectMembersbyGroup.addObserver(this);
        et_SearchMembers.setSingleLine(true);
        et_SearchMembers.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = et_SearchMembers.getText().toString().toLowerCase(Locale.getDefault());
                apapter.filter(text);
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
        recyclerView.addOnItemTouchListener(new Fragment_Rent.RecyclerTouchListener(getActivity(), recyclerView, new Fragment_Rent.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                MembersbyGroupData membersbyGroupData = dataList.get(position);

                if (membersbyGroupData.getCanRemoveUsers().equals("1")) {

                    if (membersbyGroupData.getIsOwner().equals("1")) {
                        String[] abc = {"View Profile"};
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        alertDialogBuilder.setTitle("Select a option").setItems(abc, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getContext(), "Hiii", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), ActivityViewProfile.class).putExtra("profileId", objectMembersbyGroup.getMembersbyGroupResponse().getData().get(position).getProfileId()));
                            }
                        });
                        alertDialogBuilder.show();
                    } else {
                        String[] abc = {"View Profile", "Remove"};
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                        alertDialogBuilder.setTitle("Select a option").setItems(abc, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        startActivity(new Intent(getActivity(), ActivityViewProfile.class).putExtra("profileId", objectMembersbyGroup.getMembersbyGroupResponse().getData().get(position).getProfileId()));
                                        break;
                                    case 1:
                                        break;
                                    default:
                                        break;

                                }
                            }
                        });
                        alertDialogBuilder.show();

                    }


                } else {

                    String[] abc = {"View Profile"};
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Select a option").setItems(abc, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getActivity(), ActivityViewProfile.class).putExtra("profileId", objectMembersbyGroup.getMembersbyGroupResponse().getData().get(position).getProfileId()));
                        }
                    });

                    alertDialogBuilder.show();
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void update(Observable o, Object arg) {
        dataList.clear();
        try {
            if (!objectMembersbyGroup.getMembersbyGroupResponse().getData().isEmpty()) {
                for (MembersbyGroupData membersbyGroupData : objectMembersbyGroup.getMembersbyGroupResponse().getData()) {
                    if (membersbyGroupData.getStatus().equals("1")) {
                        dataList.add(membersbyGroupData);
                    }
                }
                apapter = new MembersAdapter(dataList, getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(apapter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}