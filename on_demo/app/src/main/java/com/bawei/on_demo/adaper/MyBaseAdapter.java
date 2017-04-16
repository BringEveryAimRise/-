package com.bawei.on_demo.adaper;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.on_demo.R;
import com.bawei.on_demo.bean.News_Beans;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.jar.Pack200;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/4/15 0015
 */

public class MyBaseAdapter extends BaseAdapter{

    Context context;
    List<News_Beans.DataBean> data;

    public MyBaseAdapter(Context context,List<News_Beans.DataBean> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data!=null ?data.size():0;
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
        ViewHolder vh;
        if(view==null){
            vh=new ViewHolder();
            view=View.inflate(context,R.layout.item,null);
            vh.title= (TextView) view.findViewById(R.id.item_textv);
            vh.pic= (ImageView) view.findViewById(R.id.item_imagev);

            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }

        vh.title.setText(data.get(i).getTitle());

        News_Beans.DataBean.MediaInfoBean media_info = data.get(i).getMedia_info();

        if(media_info!=null){
            vh.pic_path = media_info.getAvatar_url();

            if(!TextUtils.isEmpty(vh.pic_path)){
                ImageLoader.getInstance().displayImage(vh.pic_path,vh.pic);
            }
        }

        return view;
    }

    class ViewHolder{
        TextView title;
        ImageView pic;
        String pic_path;
    }

}
