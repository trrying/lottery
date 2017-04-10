package com.owm.lottery.view.adapter.commit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 通用viewpager adapter
 * Created by ouweiming on 2017/4/7.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mDate;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        mDate = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mDate.get(position);
    }

    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}
