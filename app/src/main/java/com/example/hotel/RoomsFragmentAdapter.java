
package com.example.hotel;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class RoomsFragmentAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public JuniorSuitesFragment juniorFragment;


    public RoomsFragmentAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new JuniorSuitesFragment();
            case 1:
                return new MasterSuitesFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }


}


