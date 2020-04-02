package com.example.focpc.mahindralifespaces.ui.activities.reward_wallet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Admin on 13-07-2015.
 */
public class RewardWalletTabAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragList;
    private final String[] tabTitles = {"Active", "Pending", "Claimed"};

    public RewardWalletTabAdapter(FragmentManager fm
            , List<Fragment> fragList) {

        super(fm);
        this.fragList = fragList;

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];

    }

}
