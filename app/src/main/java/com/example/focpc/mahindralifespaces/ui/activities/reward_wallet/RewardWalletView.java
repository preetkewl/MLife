package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import java.util.List;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface RewardWalletView {

    void onRewardDetailsFetched(List<RewardDetailsItem> rewards, List<VisitLeftItem> visitLeftItems, boolean isFromLocal);

    void onError(String message);

    void onSessionExpired();

    void showLoading();

    void hideLoading();

    void onNoDataInLocal();


}
