package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.mlife.R;
import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.ArrayList;
import java.util.List;

public class RewardWalletActivity extends BaseActivity implements RewardWalletView {


    TabLayout rewardTabs;
    ViewPager rewardWalletPager;
    private List<Fragment> fragList = new ArrayList<>();
    private RewardWalletTabAdapter mRewardWalletTabAdapter;
    private ActiveRewardWalletFrag activeRewardWalletFrag;
    private OneVisitRewardWalletFrag oneVisitRewardWalletFrag;
    private ExpiredRewardWalletFrag expiredRewardWalletFrag;
    private RewardWalletPresenter rewardWalletPresenter;
    private boolean hasNetwork = true;


    ArrayList<RewardDetailsItem> activeRewards = new ArrayList<>();
    ArrayList<RewardDetailsItem> expiredRewards = new ArrayList<>();
    ArrayList<VisitLeftItem> onVisitLeftRewards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_wallet);
        rewardTabs = findViewById(R.id.rewardTabs);
        rewardWalletPager = findViewById(R.id.rewardWalletPager);
        initPager();
        rewardWalletPresenter = new ReawardWalletPresenterImple(this);

        if (isNetworkAvailable()) {
            rewardWalletPresenter.getRewardWallet(this, String.valueOf(MlsUtils.getUserId(this)));
            hasNetwork = true;
        } else {
            MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
            hasNetwork = false;
        }
        rewardWalletPresenter.getRewardDataFromLocal();
    }

    private void initPager() {
        fragList.clear();
        activeRewardWalletFrag = ActiveRewardWalletFrag.newInstance();
        oneVisitRewardWalletFrag = OneVisitRewardWalletFrag.newInstance();
        expiredRewardWalletFrag = ExpiredRewardWalletFrag.newInstance();
        fragList.add(activeRewardWalletFrag);
        fragList.add(oneVisitRewardWalletFrag);
        fragList.add(expiredRewardWalletFrag);
        mRewardWalletTabAdapter = new RewardWalletTabAdapter(getSupportFragmentManager(), fragList);
        rewardWalletPager.setAdapter(mRewardWalletTabAdapter);
        setSlider(rewardWalletPager);
    }

    private void setSlider(ViewPager pager) {
        pager.setOffscreenPageLimit(3);
        rewardTabs.setupWithViewPager(pager);
    }

    @Override
    public void onRewardDetailsFetched(final List<RewardDetailsItem> rewardDetailsList,
                                       final List<VisitLeftItem> visitLeftList, boolean isFromLocal) {
        if (isFromLocal && activeRewards != null && onVisitLeftRewards != null && expiredRewards != null
                && (!activeRewards.isEmpty() || !onVisitLeftRewards.isEmpty() || !expiredRewards.isEmpty()))
            return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (visitLeftList != null && !visitLeftList.isEmpty()) {
                    onVisitLeftRewards.clear();
                    onVisitLeftRewards.addAll(visitLeftList);
                }
                if (rewardDetailsList != null && !rewardDetailsList.isEmpty()) {
                    activeRewards.clear();
                    expiredRewards.clear();
                    for (int i = 0; i < rewardDetailsList.size(); i++) {
                        if (rewardDetailsList.get(i).getType() == 1 && rewardDetailsList.get(i).getClaim_status() == 1 &&
                                rewardDetailsList.get(i).getProgram_status() == 1)
                            activeRewards.add(rewardDetailsList.get(i));
                        else if (rewardDetailsList.get(i).getType() == 2 && rewardDetailsList.get(i).getClaim_status() == 0 &&
                                rewardDetailsList.get(i).getProgram_status() == 1)
                            activeRewards.add(rewardDetailsList.get(i));
                        else
                            expiredRewards.add(rewardDetailsList.get(i));
                    }
                }

                if (activeRewardWalletFrag != null)
                    activeRewardWalletFrag.setData(activeRewards);
                if (oneVisitRewardWalletFrag != null)
                    oneVisitRewardWalletFrag.setData(onVisitLeftRewards);
                if (expiredRewardWalletFrag != null)
                    expiredRewardWalletFrag.setData(expiredRewards);

            }
        });


    }

    @Override
    public void onError(String message) {
        MlsUtils.showToast(this, message, false);
    }

    @Override
    public void onNoDataInLocal() {
        if (hasNetwork) return;
        hideLoading();

    }
}
