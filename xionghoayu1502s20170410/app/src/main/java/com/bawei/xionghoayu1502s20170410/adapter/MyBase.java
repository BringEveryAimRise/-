package com.bawei.xionghoayu1502s20170410.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xionghoayu1502s20170410.R;
import com.bawei.xionghoayu1502s20170410.bean.Beans;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Effect :ListView 适配器类
 * <p>
 * Created by Administrator
 * Time by 2017/4/10 0010
 */

public class MyBase extends BaseAdapter {

    private Context context;
    private List<Beans.ResultBean> beans;

    public MyBase(Context context, List<Beans.ResultBean> beans){
        this.context=context;
        this.beans=beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder vh;

        if(view==null){
            vh=new ViewHolder();
            view=View.inflate(context, R.layout.item,null);
            vh.iv= (ImageView) view.findViewById(R.id.item_iv);
            vh.tv1= (TextView) view.findViewById(R.id.item_tv1);
            vh.tv2= (TextView) view.findViewById(R.id.item_tv2);
            vh.bit=new BitmapUtils(context);
            vh.bit=vh.bit.configDefaultLoadingImage(R.mipmap.ic_launcher);

            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }

        vh.tv1.setText(beans.get(i).getTitle());
        vh.tv2.setText(beans.get(i).getSrc());
        //通过 xutils加载网络图片
        vh.bit.display(vh.iv,beans.get(i).getImg());

        return view;
    }

    class ViewHolder{
        BitmapUtils bit;
        TextView tv1,tv2;
        ImageView iv;
    }

}
