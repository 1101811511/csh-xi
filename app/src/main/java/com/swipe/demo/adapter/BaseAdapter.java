package com.swipe.demo.adapter;

import android.content.res.ObbInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private List<T> mList;

    public void setList(List<T> aList) {
        this.mList = aList;
        notifyDataSetChanged();
    }

    public void clear() {
        if (null == mList)
            return;
        mList.clear();
    }

    public int getDataCount() {
        if (null == mList)
            return 0;
        return mList.size();
    }

    @Override
    public abstract int getItemViewType(int position);

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public abstract int getItemCount();
}
