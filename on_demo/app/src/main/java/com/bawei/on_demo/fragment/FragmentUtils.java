package com.bawei.on_demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.on_demo.MyAsyncTask;
import com.bawei.on_demo.R;
import com.bawei.on_demo.adaper.MyBaseAdapter;
import com.bawei.on_demo.bean.News_Beans;
import com.google.gson.Gson;

import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/4/15 0015
 */

public class FragmentUtils extends Fragment {

    private ListView listv;
    private String context_path="http://ic.snssdk.com/2/article/v25/stream/?count=20&bd_latitude=4.9E-324&bd_longitude=4.9E-324&loc_mode=5&lac=4527&cid=28883&iid=3839760160&device_id=12246291682&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=iToolsAVM&os_api=19&os_version=4.4.4&uuid=352284045861006&openudid=84c1c7b192991cc6&category=";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    String json= (String) msg.obj;

                    News_Beans news_beans = gson.fromJson(json, News_Beans.class);
                    List<News_Beans.DataBean> data = news_beans.getData();

                    MyBaseAdapter adapter = new MyBaseAdapter(getActivity(),data);
                    listv.setAdapter(adapter);
                    break;
            }
        }
    };
    private Gson gson;


    public static FragmentUtils getFragmentUtils(String url){
        FragmentUtils fragmentUtils = new FragmentUtils();
        Bundle bul = new Bundle();
        bul.putString("URL",url);
        fragmentUtils.setArguments(bul);
        return fragmentUtils;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gson = new Gson();

        initView();

        initData();

    }

    private void initData() {
        Bundle arguments = getArguments();
        if(arguments!=null){
            String url = arguments.getString("URL");
            //获取数据
            new MyAsyncTask(1,handler).execute(context_path+url);

        }
    }

    private void initView() {
        listv = (ListView) getView().findViewById(R.id.fragment_listv);
    }
}
