package com.bawei.on_demo.adaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.bawei.on_demo.bean.Beans;
import com.bawei.on_demo.fragment.FragmentUtils;
import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/4/15 0015
 */

public class MyViewPager extends FragmentPagerAdapter {

    private List<Beans.DataBeanX.DataBean> datas;

    public MyViewPager(FragmentManager fm,List<Beans.DataBeanX.DataBean> datas) {
        super(fm);
        this.datas=datas;
    }

    @Override
    public Fragment getItem(int position) {

        return FragmentUtils.getFragmentUtils(datas.get(position).getCategory());
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position).getCategory();
    }
}
