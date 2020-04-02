package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlife.R;

import java.util.ArrayList;
import java.util.List;


public class LeadStatusFragment extends Fragment {
    private RecyclerView statusRV;
    private List<LeadItem> referedUsers = new ArrayList<>();
    private LeadStatusAdapter leadStatusAdapter;
    private TextView emptyView;


    public LeadStatusFragment() {
        // Required empty public constructor
    }

    public static LeadStatusFragment newInstance() {
        return new LeadStatusFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lead_status, container, false);
        statusRV = v.findViewById(R.id.statusRV);
        emptyView = v.findViewById(R.id.emptyView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iniRV();

    }

    private void iniRV() {
        leadStatusAdapter = new LeadStatusAdapter(getActivity(), referedUsers);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        statusRV.setLayoutManager(manager);
        statusRV.setAdapter(leadStatusAdapter);
    }

    public void setData(List<LeadItem> leadItemList, boolean isFromLocal) {
        if (isFromLocal && referedUsers != null && !referedUsers.isEmpty()) return;
        if (referedUsers != null && leadStatusAdapter != null) {
            referedUsers.clear();
            referedUsers.addAll(leadItemList);
            leadStatusAdapter.notifyDataSetChanged();
        }

        if (emptyView != null) {
            if (leadItemList.size() > 0) emptyView.setVisibility(View.GONE);
            else emptyView.setVisibility(View.VISIBLE);
        }


    }
}
