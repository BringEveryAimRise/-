package com.bawei.bear201704_study_dome.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.bear201704_study_dome.R;
import com.bawei.bear201704_study_dome.bean.Beans;

import java.util.List;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/4/7 0007
 */

public class MyBaseAdapter extends BaseAdapter {

    Context context;
    List<Beans.DataBean> data;

    public MyBaseAdapter(Context context,List<Beans.DataBean> data){
        this.context=context;
        this.data=data;
    }



    @Override
    public int getCount() {
        return data !=null ?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler vh;
        if (view==null){
            vh=new ViewHodler();

            view=View.inflate(context,R.layout.item_show,null);

            vh.tv1= (TextView) view.findViewById(R.id.item_tv1);
            vh.tv2= (TextView) view.findViewById(R.id.item_tv2);

            view.setTag(vh);
        }else{
            vh= (ViewHodler) view.getTag();
        }

        vh.tv1.setText(data.get(i).getNews_title());
        vh.tv2.setText(data.get(i).getNews_summary());

        return view;
    }

    class ViewHodler{
        TextView tv1,tv2;
    }


}
