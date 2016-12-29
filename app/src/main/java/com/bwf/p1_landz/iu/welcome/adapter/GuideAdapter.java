package com.bwf.p1_landz.iu.welcome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.bwf.p1_landz.iu.welcome.fragment.GuideFrament;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GuideAdapter extends FragmentPagerAdapter {
    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
         GuideFrament guideFrament =GuideFrament.newInstance(position);
        return guideFrament;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
