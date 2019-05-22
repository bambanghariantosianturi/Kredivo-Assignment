package com.androidexercise.admin.kredivoassigment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.androidexercise.admin.kredivoassigment.view.DataPackageFragment;
import com.androidexercise.admin.kredivoassigment.view.PulsaFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new PulsaFragment();
        } else if (i == 1) {
            return new DataPackageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
