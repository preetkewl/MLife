package com.example.focpc.mahindralifespaces.ui.activities.refer_now;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.mlife.R;
import com.example.focpc.mahindralifespaces.utils.BaseActivity;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.ArrayList;
import java.util.List;

public class ReferNowActivity extends BaseActivity implements ReferNowView {
    private TabLayout referTabs;
    private ViewPager referNowPager;
    private List<Fragment> fragList = new ArrayList<>();
    private ReferalPagerAdapter referalPagerAdapter;
    private ReferNowPresenter referNowPresenter;
    private ProgramListFragment programListFragment;
    private LeadStatusFragment leadStatusFragment;
    private boolean hasNetwork = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_now);
        referTabs = findViewById(R.id.referTabs);
        referNowPager = findViewById(R.id.referNowPager);
        initPager();
        referNowPresenter = new ReferNowPresenterImple(this);
        if (isNetworkAvailable()) {
            referNowPresenter.getReferalData(this, String.valueOf(MlsUtils.getUserId(this)));
            hasNetwork = true;
        } else {
            MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
            hasNetwork = false;
        }
        referNowPresenter.getReferaDataFromLocal();

    }


    private void initPager() {
        programListFragment = ProgramListFragment.newInstance();
        leadStatusFragment = LeadStatusFragment.newInstance();
        fragList.clear();
        fragList.add(programListFragment);
        fragList.add(leadStatusFragment);
        referalPagerAdapter = new ReferalPagerAdapter(getSupportFragmentManager(), fragList);
        referNowPager.setOffscreenPageLimit(2);
        referNowPager.setAdapter(referalPagerAdapter);
        setSlider(referNowPager);
    }

    private void setSlider(ViewPager pager) {
        pager.setOffscreenPageLimit(2);
        referTabs.setupWithViewPager(pager);
    }

    @Override
    public void onReferalDataFetched(final List<ReferalItem> referalItems, final List<LeadItem> leadItems, final boolean isFromLocal) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (programListFragment != null && referalItems != null)
                    programListFragment.setData(referalItems,isFromLocal);
                if (leadStatusFragment != null && leadItems != null)
                    leadStatusFragment.setData(leadItems,isFromLocal);
            }
        });


    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MlsConstants.REFER_NOW_RESULT){
            if (isNetworkAvailable()) {
                referNowPresenter.getReferalData(this, String.valueOf(MlsUtils.getUserId(this)));
            } else {
                MlsUtils.showToast(this, getString(R.string.network_unavailable), false);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNoDataInLocal() {
        if (hasNetwork) return;
        hideLoading();

    }
}
