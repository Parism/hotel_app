package com.example.hotel;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class FragmentAdapter extends FragmentPagerAdapter {

    public HomeFragment homeFragment;
    public ChatFragment chatFragment;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else {
            return new ChatFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

//    @Override @NonNull  //https://stackoverflow.com/questions/14035090/how-to-get-existing-fragments-when-using-fragmentpageradapter
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);   // save the appropriate reference depending on position
//        switch (position) {
//            case 0:
//                homeFragment = (HomeFragment) createdFragment;
//                break;
//            case 1:
//                chatFragment = (ChatFragment) createdFragment;
//                break;
//        }
//        return createdFragment;
//    }
}

