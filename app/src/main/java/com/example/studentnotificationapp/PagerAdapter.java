package com.example.studentnotificationapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabcount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ClassesFragment();

            case 1:
                return new ForYouFragment();

            case 2:
                return new LikedFragment();

            case 3:
                return new AllFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
