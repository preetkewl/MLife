package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by foc pc on 14-12-2017.
 */

public class ReawardWalletPresenterImple implements RewardWalletPresenter, RewardWalletListener {
    private RewardWalletView rewardWalletView;
    private RewardWalletInteractor rewardWalletInteractor;

    public ReawardWalletPresenterImple(RewardWalletView rewardWalletView) {
        this.rewardWalletView = rewardWalletView;
        this.rewardWalletInteractor = new RewardWalletInteractorImple();
    }

    @Override
    public void getRewardDataFromLocal() {
        rewardWalletView.showLoading();
        new RewardDataReadAsync((RewardWalletActivity) rewardWalletView, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void getRewardWallet(Context context, String userId) {
        rewardWalletInteractor.getRewardWallet(context, userId, this);
    }

    @Override
    public void onSessionExpired() {
        rewardWalletView.hideLoading();
        rewardWalletView.onSessionExpired();

    }

    @Override
    public void onApiError(String message) {
        onError(message);
    }

    @Override
    public void onRewardWalletFetched(List<RewardDetailsItem> rewards, List<VisitLeftItem> visitLeftItems, boolean isFromLocal) {
        rewardWalletView.hideLoading();
        rewardWalletView.onRewardDetailsFetched(rewards, visitLeftItems, isFromLocal);

    }

    @Override
    public void onError(String message) {
        rewardWalletView.hideLoading();
        rewardWalletView.onError(message);
    }

    @Override
    public void onNoDataInLocal() {
        rewardWalletView.onNoDataInLocal();
    }
}
