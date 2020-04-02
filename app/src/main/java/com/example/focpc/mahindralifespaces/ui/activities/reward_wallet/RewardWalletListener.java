package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import com.example.focpc.mahindralifespaces.utils.BaseListener;

import java.util.List;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface RewardWalletListener extends BaseListener {

    void onRewardWalletFetched(List<RewardDetailsItem> rewards, List<VisitLeftItem> visitLeftItems, boolean isFromLocal);

    void onError(String message);

    void onNoDataInLocal();
}
