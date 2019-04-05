package com.taskcoordinator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> FragmentList = new ArrayList<>();
    private final List<String> FragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    /**
     * returns the position where the fragments are to be inserted
     */
    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    /**
     *get the count of the total number of fragments present
     */
    @Override
    public int getCount() {
        return FragmentList.size();
    }

    /**
     *Adding the fragments and titles in the list
     */

    public void addFragment(Fragment fragment, String title) {
        FragmentList.add(fragment);
        FragmentTitleList.add(title);
    }

    /**
     *returning the title at the position
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitleList.get(position);
    }

}



