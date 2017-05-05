package com.bawei.clock_custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bawei.clock_custom.view.ClockBtn_View;

/**
 * 项目需求：
 * 实现一个自定义表盘
 * 1. 画一个简单的圆
 * 2. 绘制刻度
 * 3. 绘制表针
 * 4. 绘制当前时间
 * 5. 实现时间动态显示
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClockBtn_View clockbtn= (ClockBtn_View) findViewById(R.id.main_clockbtn);
        clockbtn.setButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "按钮被点击了！", Toast.LENGTH_SHORT).show();
            }
        });

//        clockbtn.setClockListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "点我表盘干什么？", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
