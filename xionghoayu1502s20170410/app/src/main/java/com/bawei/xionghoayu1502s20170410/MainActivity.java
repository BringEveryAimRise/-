package com.bawei.xionghoayu1502s20170410;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.xionghoayu1502s20170410.adapter.MyAdapter;
import com.bawei.xionghoayu1502s20170410.fragment.MyFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    @ViewInject(R.id.main_vp)
    private ViewPager vp;

    @ViewInject(R.id.main_title)
    private TabPageIndicator title;

    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化view控件
        initView();
        //初始化Fragment数据
        initData();

    }

    private void initData() {

        //初始化 ViewUtils
        ViewUtils.inject(this);

        //初始化 标题
        strings = new ArrayList<>();
        strings.add("国际新闻");
        strings.add("国内新闻");
        strings.add("北美新闻");
        strings.add("娱乐新闻");
        strings.add("八卦新闻");
        strings.add("游戏新闻");
        strings.add("爱情新闻");
        strings.add("至善新闻");
        strings.add("至善新闻");
        strings.add("至善新闻");


        //fragment集合
        fragments = new ArrayList<>();
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());

        //创建 适配器类
        MyAdapter adapter = new MyAdapter(manager,fragments,strings);
        vp.setAdapter(adapter);

        //将title进行绑定
        title.setViewPager(vp);
    }

    private void initView() {
        manager = getSupportFragmentManager();

//        title = (TabPageIndicator) findViewById(R.id.main_title);
//        vp = (ViewPager) findViewById(R.id.main_vp);

    }
}
