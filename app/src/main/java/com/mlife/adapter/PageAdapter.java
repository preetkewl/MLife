package com.mlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milagro on 8/22/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

      String[] Tabtile = new String[]{"ABOUT PROJECT", "AMENITIES", "GALLERY", "CONSTRUCTION UPDATES", "LOCATION", "OFFERS"};
    List<Fragment> mFragment = new ArrayList<>();

    public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Tabtile[position];
    }

}
