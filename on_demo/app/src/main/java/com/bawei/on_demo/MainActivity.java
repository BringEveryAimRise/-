package com.bawei.on_demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.on_demo.adaper.MyViewPager;
import com.bawei.on_demo.bean.Beans;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private TabLayout tab;

    private String titile_path="http://ic.snssdk.com/article/category/get/v2/?user_city=%E5%AE%89%E9%98%B3&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1465099837&categories=%5B%22video%22%2C%22news_hot%22%2C%22news_local%22%2C%22news_society%22%2C%22subscription%22%2C%22news_entertainment%22%2C%22news_tech%22%2C%22news_car%22%2C%22news_sports%22%2C%22news_finance%22%2C%22news_military%22%2C%22news_world%22%2C%22essay_joke%22%2C%22image_funny%22%2C%22image_ppmm%22%2C%22news_health%22%2C%22positive%22%2C%22jinritemai%22%2C%22news_house%22%5D&version=17375902057%7C14%7C1465030267&iid=4471477475&device_id=17375902057&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=Samsung+Galaxy+S3+-+4.3+-+API+18+-+720x1280&os_api=18&os_version=4.3&openudid=7036bc89d44f680c";


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                //获取 标题数据
                case 0:
                    String json= (String) msg.obj;
                    Beans beans = gson.fromJson(json, Beans.class);
                    Beans.DataBeanX data = beans.getData();
                    datas = data.getData();
                    MyViewPager adapter = new MyViewPager(getSupportFragmentManager(), datas);
                    vp.setAdapter(adapter);
                    break;
            }


        }
    };
    private Gson gson;
    private List<Beans.DataBeanX.DataBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        gson = new Gson();

        initView();

        initData();
    }

    //获取数据
    private void initData() {
        //获取标题数据
        new MyAsyncTask(0,handler).execute(titile_path);
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.main_tabLayout);
        vp = (ViewPager) findViewById(R.id.main_viewPager);

        //将tab与vp进行绑定
        tab.setupWithViewPager(vp);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabTextColors(Color.BLACK,Color.RED);

    }
}
