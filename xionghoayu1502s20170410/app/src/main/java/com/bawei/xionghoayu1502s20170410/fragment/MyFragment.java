package com.bawei.xionghoayu1502s20170410.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bawei.xionghoayu1502s20170410.R;
import com.bawei.xionghoayu1502s20170410.adapter.MyBase;
import com.bawei.xionghoayu1502s20170410.bean.Beans;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Effect :Fragment 复用类
 * <p>
 * Created by Administrator
 * Time by 2017/4/10 0010
 */

public class MyFragment extends Fragment {

    private String path="http://result.eolinker.com/SCNPrIW543a7a64f42043f3fe3a8df2b59f7b130608b922?uri=http://op.juhe.cn/onebox/news/query";

//    @ViewInject(R.id.fragment_listv)
    private ListView listv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化 控件
        initView();

        //初始化 数据
        initData();
    }

    private void initData() {
        //xutils 请求网络数据
        HttpUtils httpUtils = new HttpUtils();

        //发送请求
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {

            //请求成功
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                //解析数据
                Gson gson = new Gson();
                Beans beans = gson.fromJson(result, Beans.class);
                List<Beans.ResultBean> be = beans.getResult();
                MyBase base = new MyBase(getActivity(), be);
                listv.setAdapter(base);
            }

            //请求失败
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });


    }

    private void initView() {
        //xutils 初始化
        ViewUtils.inject(getActivity());

        listv = (ListView) getView().findViewById(R.id.fragment_listv);
    }
}
