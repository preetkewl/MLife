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
public class ActiveRewardWalletFrag extends Fragment {

    RelativeLayout headerView;
    RecyclerView activeRewardRV;
    TextView active_reward_status_emptyTV;
    private LinearLayoutManager manager;
    private ActiveRewardFragAdapter mActiveRewardFragAdapter;
    List<RewardDetailsItem> rewardDetailsList = new ArrayList<>();

    public static ActiveRewardWalletFrag newInstance() {
        return new ActiveRewardWalletFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.active_reward_frag, container, false);
        activeRewardRV = rootView.findViewById(R.id.activeRewardRV);
        active_reward_status_emptyTV = rootView.findViewById(R.id.active_reward_status_emptyTV);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRV();
    }

    private void initRV() {
        mActiveRewardFragAdapter = new ActiveRewardFragAdapter(rewardDetailsList);
        manager = new LinearLayoutManager(getActivity());
        activeRewardRV.setLayoutManager(manager);
        activeRewardRV.setAdapter(mActiveRewardFragAdapter);
    }

    public void setData(List<RewardDetailsItem> rewards) {
        if (rewardDetailsList != null && mActiveRewardFragAdapter != null) {
            rewardDetailsList.clear();
            rewardDetailsList.addAll(rewards);
            mActiveRewardFragAdapter.notifyDataSetChanged();
        }
        if (active_reward_status_emptyTV != null) {
            if (rewards.size() > 0) active_reward_status_emptyTV.setVisibility(View.GONE);
            else active_reward_status_emptyTV.setVisibility(View.VISIBLE);
        }


    }


}
