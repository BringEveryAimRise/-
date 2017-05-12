package com.bawei.recyclerview_demo3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ArrayList<Beans> list;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Beans("数据"+i,false,true));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("周五作业");
        toolbar.setBackgroundColor(Color.BLUE);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.findViewById(R.id.main_toolbar_tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "全选", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setTag(true);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
        toolbar.findViewById(R.id.main_toolbar_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "反选", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setTag(false);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
        toolbar.findViewById(R.id.main_toolbar_tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();

                ArrayList<Beans> beanses = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).isTag()){
                        beanses.add(list.get(i));
                    }
                }

                Intent intent = new Intent(MainActivity.this, XXActivity.class);
                intent.putExtra("tag",beanses);
                startActivity(intent);

            }
        });




    }
}
