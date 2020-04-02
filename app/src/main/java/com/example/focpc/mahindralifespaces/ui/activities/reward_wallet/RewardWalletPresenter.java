package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;

/**
 * Created by foc pc on 14-12-2017.
 */

public interface RewardWalletPresenter {

    void getRewardWallet(Context context,String userId);
    void getRewardDataFromLocal();
}
