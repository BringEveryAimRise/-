package com.bawei.clock_custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bawei.clock_custom.R;

/**
 * Effect :组合控件
 * <p>
 * Created by Administrator
 * Time by 2017/5/5 0005
 */

public class ClockBtn_View extends RelativeLayout {


    private final Button btn;
    private final ClockView clock;

    public ClockBtn_View(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载布局
        LayoutInflater.from(context).inflate(R.layout.clock_view,this);
        //获取控件
        btn = (Button)findViewById(R.id.clock_btn);
        clock = (ClockView) findViewById(R.id.clock_clock);
    }


    /**
     * 设置按钮的点击事件
     */
    public void setButtonListener(OnClickListener onClickListener){
        btn.setOnClickListener(onClickListener);
    }
    /**
     * 设置按钮的点击事件
     */
    public void setClockListener(OnClickListener onClickListener){
        clock.setOnClickListener(onClickListener);
    }



}
