package com.bawei.clock_custom.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

import static android.R.attr.data;


/**
 * Effect :绘制 表
 *
 * 1. 绘制圆
 * <p>
 * Created by Administrator
 * Time by 2017/5/5 0005
 */

public class ClockView extends ImageView {

    private Paint paint;
    private int width=300;
    private int heigth=300;
    private int padding=5;
    private int hour;
    private int minute;
    private int second;
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            //字面意思 是如此 使无效
//            //起到刷新的作用
//            //重绘
//            invalidate();
//        }
//    };


    /**
     * 第一步
     * 创建两个参数的构造方法
     * 在 两个参数的构造方法中，初始化画笔
     */
    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化 画笔
        initPaint();
    }


    /**
     * 初始化 Paint 画笔
     */
    public void initPaint(){

        paint = new Paint();
        //设置 画笔宽度
        paint.setStrokeWidth(3);
        //设置 风格
        //Paint.Style.STROK 画笔
        //Paint.Style.FILL  充满
        //Paint.Style.FILL_AND_STROKE  画笔 and 充满
        paint.setStyle(Paint.Style.STROKE);
        //设置颜色
        //或者 Color.parseColor("#666666");
        paint.setColor(Color.RED);
        //设置抗锯齿
        paint.setAntiAlias(true);
    }


    /**
     * 第二步
     * 测量控件宽高
     */
    /**
     * 测量（测量控件宽高）
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽高尺寸
        setMeasuredDimension(width,heigth);
    }

    /**
     * 第三步
     * 绘制 画布
     */
    /**
     * 设置画布
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //绘制圆
        drawCircle(canvas);
        //绘制刻度
        drawScale(canvas);
        //绘制圆心
        drawCenterCircle(canvas);
        //绘制指针
        drawPointer(canvas);
        //绘制文字
        drawStr(canvas);

        //在这个地方进行handler 刷新数据
//        handler.sendEmptyMessage(0);
        invalidate();
    }

    /**
     * 绘制 文字
     */
    private void drawStr(Canvas canvas) {
        //设置 画笔字体大小
        paint.setTextSize(20);
        //格式化当前时间
        StringBuffer sb =  new StringBuffer();
        if(hour<10){
            sb.append("0").append(String.valueOf(hour)).append(":");
        }else{
            sb.append(String.valueOf(hour)).append(":");
        }
        if(minute<10){
            sb.append("0").append(String.valueOf(minute)).append(":");
        }else{
            sb.append(String.valueOf(minute)).append(":");
        }
        if(second<10){
            sb.append("0").append(String.valueOf(second));
        }else{
            sb.append(String.valueOf(second));
        }
        String str = sb.toString();

        //测量字体宽度
        float v = paint.measureText(str);
        //绘制 text
        canvas.drawText(str,width/2-v/2,width/2+30,paint);

    }

    /**
     * 绘制 圆中心点
     * @param canvas
     */
    private void drawCenterCircle(Canvas canvas){
        //设置 画笔风格
        paint.setStyle(Paint.Style.FILL);
        //绘制圆
        canvas.drawCircle(width/2,heigth/2,5,paint);
    }

    /**
     * 绘制 圆
     * @param canvas
     */
    private void drawCircle(Canvas canvas){
        //绘制圆
        //设置 画笔风格
        paint.setStyle(Paint.Style.STROKE);
        //设置 画笔颜色
        paint.setColor(Color.RED);
        //绘制 圆
        //参数：
        //1.cx:控件宽度的一半
        //2.cy:控件高度的一半  二者作用是 限制以控件中心为点进行绘制
        //3.radius:半径 控件一半 减去指定宽度 剩余宽度
        //4.paint:画笔
        canvas.drawCircle(width/2,heigth/2,width/2-padding,paint);
    }

    /**
     * 绘制刻度
     * 多根刻度 是通过对画布进行旋转
     */
    private void drawScale(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);

        //循环绘制
        for (int i = 0; i < 12; i++) {
            //12 3 6 9 四条线条长度变长
            if(i%3==0){
                //绘制 线
                canvas.drawLine(width/2-padding,padding,width/2-padding,padding+4+18,paint);
            }else{
                //绘制 线
                canvas.drawLine(width/2-padding,padding,width/2-padding,padding+4+8,paint);
            }
            //旋转画布
            //以 圆的中心为原点进行旋转重复绘制
            //参数：
            //1.旋转角度
            //2.x轴
            //3.y轴
            canvas.rotate(30,width/2,heigth/2);
        }
    }

    /**
     * 绘制 表针
     * @param canvas
     */

    private void drawPointer(Canvas canvas){
        //获取当前时间
        Date date = new Date();
        //获取时分秒
        //时
        hour = date.getHours();
        //分
        minute = date.getMinutes();
        //秒
        second = date.getSeconds();


        //获取 小时的旋转度数
        //写法：小时数*30度+分钟数/2
        int hour_c = hour * 30 + minute / 2;
        //设置画笔的颜色
        paint.setColor(Color.BLACK);
        //提交 画布
        canvas.save();
        //旋转  以控件中心点 进行 时针度数旋转
        canvas.rotate(hour_c,width/2,heigth/2);
        //绘制  线条
        canvas.drawLine(width/2,heigth/2,width/2, (float) (width/2-(width/2*0.6)),paint);
        //恢复 还原画笔
        canvas.restore();

        //获取 分钟的旋转度数
        int minute_c = minute * 6 + second / 10;
        //设置画笔颜色
        paint.setColor(Color.GREEN);
        //提交 画布
        canvas.save();
        //旋转 画布
        canvas.rotate(minute_c,width/2,heigth/2);
        //旋转 线条
        canvas.drawLine(width/2,heigth/2,width/2,(float) (width/2-(width/2*0.7)),paint);
        //恢复 还原画笔
        canvas.restore();

        //获取 秒针的旋转度数
        int second_c = second * 6;
        //设置画笔颜色
        paint.setColor(Color.BLUE);
        //提交 画布
        canvas.save();
        //旋转 画布
        canvas.rotate(second_c,width/2,heigth/2);
        //旋转 线条
        canvas.drawLine(width/2,heigth/2,width/2,(float) (width/2-(width/2*0.8)),paint);
        //恢复 还原画笔
        canvas.restore();
    }



}
