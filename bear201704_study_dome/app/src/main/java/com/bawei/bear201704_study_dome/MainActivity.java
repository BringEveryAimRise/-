package com.bawei.bear201704_study_dome;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.bear201704_study_dome.adapter.MyBaseAdapter;
import com.bawei.bear201704_study_dome.bean.Beans;
import com.bawei.bear201704_study_dome.https.MyAsyncTask;
import com.bawei.bear201704_study_dome.urls.Urls;
import com.google.gson.Gson;

import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private ListView listv;
    private List<Beans.DataBean> data;
    private MyBaseAdapter adapter;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==0){
                String str= (String) msg.obj;
                Gson gson = new Gson();
                Beans beans = gson.fromJson(str, Beans.class);
                data = beans.getData();

                adapter = new MyBaseAdapter(MainActivity.this, data);
                listv.setAdapter(adapter);
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {
        new MyAsyncTask(handler).execute(Urls.PATH);
    }

    private void initView() {
        listv = (ListView) findViewById(R.id.main_listv);

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(data!=null){

                    Toast.makeText(MainActivity.this,"数据ID："+data.get(i).getNews_id(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        listv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                data.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
