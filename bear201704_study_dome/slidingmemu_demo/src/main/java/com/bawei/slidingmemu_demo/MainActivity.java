package com.bawei.slidingmemu_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import static android.R.attr.width;


public class MainActivity extends AppCompatActivity {

    private final String TAG="MainActivity";
    private SlidingMenu menu;
    private int width=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWidth();

        initView();

        initMenu();


    }



    private void initView() {

        Button btn= (Button) findViewById(R.id.main_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.showMenu();
            }
        });

    }

    private void initMenu() {
        //实例化 侧滑菜单对象
        menu = new SlidingMenu(this);
        //设置模式 左侧
        menu.setMode(SlidingMenu.LEFT);
        //设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影图片 | 颜色
        menu.setShadowDrawable(R.drawable.shadow);


        if(width!=0){
            //接收 R文件中的资源id
//            menu.setBehindOffsetRes(10);
            //接收 int值
            menu.setBehindOffset(10);
        }else {
            //设置滑动菜单视图后所余宽度
            menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        }

        //设置滑动菜单视图后所余宽度
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        //设置 附加Activity对象
        //参数一：上下文 参数二：menu上下文
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.menus);
    }

    public int getWidth() {
        //获取 资源 获取 展示宽度 获取宽度像素
        width=this.getResources().getDisplayMetrics().widthPixels;

        return width;
    }
}
