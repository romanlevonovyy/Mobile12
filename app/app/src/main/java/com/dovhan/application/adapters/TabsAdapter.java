package com.dovhan.application.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dovhan.application.R;
import com.dovhan.application.fragments.BlankFragment;
import com.dovhan.application.fragments.DataListFragment;
import com.dovhan.application.fragments.ProfileFragment;

public class TabsAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public TabsAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case (0):
                return new DataListFragment();
            case (1):
                return new BlankFragment();
            default:
                return new ProfileFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case (0):
                return mContext.getString(R.string.food_machines);
            case (1):
                return mContext.getString(R.string.blank);
            default:
                return mContext.getString(R.string.profile);
        }
    }
}
