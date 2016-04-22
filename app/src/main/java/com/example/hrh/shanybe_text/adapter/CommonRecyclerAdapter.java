package com.example.hrh.shanybe_text.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hrh.shanybe_text.ViewHolder.ViewHolder;

import java.util.List;

/**
 * Created by hrh on 2016/4/22.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected List<T> mDatas;
    private LayoutInflater mInflater;
    protected Context context;
    protected int mItemLayoutId;
    protected ViewHolder temp;
    public CommonRecyclerAdapter(Context context, List<T> datas, int LayoutId) {
        this.context = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = LayoutId;
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final ViewHolder viewHolder = ViewHolder.get(context, parent, mItemLayoutId );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        temp = (ViewHolder)holder;
        conver(temp, getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void conver(ViewHolder viewHolder, T item, int position);
}
