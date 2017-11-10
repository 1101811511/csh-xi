package com.swipe.demo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swipe.demo.Bean.LifeResponse;
import com.swipe.demo.R;

import java.util.List;

/**
 * Created by coco on 2017/11/9.
 */

public class LifeAdapter extends BaseAdapter {
    List<LifeResponse.itemInfo> infoList;
    Context context;

    public LifeAdapter(List<LifeResponse.itemInfo> infoList,Context context){
        this.infoList = infoList;
        this.context =context;
    }
    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int i) {
        return infoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.layout_item_life,null);
            holder = new ViewHolder();
            holder.input = (TextView) contentView.findViewById(R.id.input);
            holder.inputImg = (ImageView) contentView.findViewById(R.id.input_img);
            holder.income = (TextView) contentView.findViewById(R.id.income);
            contentView.setTag(holder);
        }else {
          holder = (ViewHolder) contentView.getTag();
        }
        holder.input.setText(infoList.get(position).businessName);
        Picasso.with(context).load(infoList.get(position).imagePath).into(holder.inputImg);
        holder.income.setText(infoList.get(position).businsessDesc);
        return contentView;
    }





    public class ViewHolder{
        TextView input;
        ImageView inputImg;
        TextView income;
    }
}
