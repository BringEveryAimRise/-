package com.bawei.recyclerview_demo3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.PREPEND;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Effect :
 * <p>
 * Created by Administrator
 * Time by 2017/5/12 0012
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<Beans> list;

    public RecyclerViewAdapter(Context context, List<Beans> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(View.inflate(context, R.layout.list_item, null));
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {


        //判断 如果非true 则不显示
        if (!list.get(position).isV()){
            holder.tag.setVisibility(View.INVISIBLE);
        }else{
            holder.tag.setVisibility(View.VISIBLE);
            holder.tag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    list.get(position).setTag(isChecked);
                }
            });

            holder.tag.setChecked(list.get(position).isTag());
        }

        holder.tv.setText(list.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return list!=null ?list.size():0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final CheckBox tag;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
            tag = (CheckBox) itemView.findViewById(R.id.item_tag);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    switch (tag.getVisibility()){
                        case View.VISIBLE:
                            //如果当前视图可见 则将所有进行隐藏
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setV(false);
                            }
                            break;
                        case View.INVISIBLE:
                            //如果当前视图不可见 则将所有进行显示
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setV(true);
                            }
                            break;
                    }
                    //刷新适配器
                    notifyDataSetChanged();
                    return true;
                }
            });


        }
    }
}
