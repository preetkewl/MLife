package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mlife.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ajo philip PC on 27-06-2016.
 */
public class OneVisitRewardWalletFrag extends Fragment {
    RecyclerView oneVisitRewardRV;
    TextView one_visit_left_status_emptyTV;
    private OneVisitRewardFragAdapter mOneVisitRewardFragAdapter;
    private LinearLayoutManager manager;
    List<VisitLeftItem> visitLeftList = new ArrayList<>();

    public static OneVisitRewardWalletFrag newInstance() {
        return new OneVisitRewardWalletFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.one_visit_reward_frag, container, false);
        oneVisitRewardRV = rootView.findViewById(R.id.oneVisitRewardRV);
        one_visit_left_status_emptyTV = rootView.findViewById(R.id.one_visit_left_status_emptyTV);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRV();

    }

    private void initRV() {
        mOneVisitRewardFragAdapter = new OneVisitRewardFragAdapter(visitLeftList);
        manager = new LinearLayoutManager(getActivity());
        oneVisitRewardRV.setLayoutManager(manager);
        oneVisitRewardRV.setAdapter(mOneVisitRewardFragAdapter);
    }

    public void setData(List<VisitLeftItem> rewards) {
        if (visitLeftList != null && mOneVisitRewardFragAdapter != null) {
            visitLeftList.clear();
            visitLeftList.addAll(rewards);
            mOneVisitRewardFragAdapter.notifyDataSetChanged();
        }
        if (one_visit_left_status_emptyTV != null) {
            if (rewards.size() > 0) one_visit_left_status_emptyTV.setVisibility(View.GONE);
            else one_visit_left_status_emptyTV.setVisibility(View.VISIBLE);
        }
    }


}
