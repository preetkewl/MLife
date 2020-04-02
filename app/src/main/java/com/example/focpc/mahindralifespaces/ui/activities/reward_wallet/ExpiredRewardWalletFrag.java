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
import java.util.Collections;
import java.util.List;



public class ExpiredRewardWalletFrag extends Fragment {
    RelativeLayout headerView;
    RecyclerView expiredRewardRV;
    TextView expired_reward_status_emptyTV;
    private ExpiredRewardFragAdapter expiredRewardFragAdapter;
    private LinearLayoutManager manager;
    List<RewardDetailsItem> expiredRewarsList = new ArrayList<>();

    public static ExpiredRewardWalletFrag newInstance() {
        return new ExpiredRewardWalletFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.expired_reward_frag, container, false);

        expiredRewardRV = rootView.findViewById(R.id.expiredRewardRV);
        expired_reward_status_emptyTV = rootView.findViewById(R.id.expired_reward_status_emptyTV);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Collections.sort(expiredRewarsList);
        initRV();
    }

    private void initRV() {
        expiredRewardFragAdapter = new ExpiredRewardFragAdapter(expiredRewarsList);
        manager = new LinearLayoutManager(getActivity());
        expiredRewardRV.setLayoutManager(manager);
        expiredRewardRV.setAdapter(expiredRewardFragAdapter);
    }

    public void setData(List<RewardDetailsItem> rewards){
        if (expiredRewarsList != null  && expiredRewardFragAdapter != null) {
            expiredRewarsList.clear();
            expiredRewarsList.addAll(rewards);
            expiredRewardFragAdapter.notifyDataSetChanged();
        }
        if (expired_reward_status_emptyTV != null) {
            if (rewards.size() > 0) expired_reward_status_emptyTV.setVisibility(View.GONE);
            else expired_reward_status_emptyTV.setVisibility(View.VISIBLE);
        }
    }


}
