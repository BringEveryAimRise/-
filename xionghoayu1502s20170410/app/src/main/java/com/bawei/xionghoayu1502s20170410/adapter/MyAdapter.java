package com.bawei.xionghoayu1502s20170410.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Effect : Fragment PagerAdapter 适配器类
 * <p>
 * Created by Administrator
 * Time by 2017/4/10 0010
 */

public class MyAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> strings;

    public MyAdapter(FragmentManager fm, List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragments=fragments;
        this.strings=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
