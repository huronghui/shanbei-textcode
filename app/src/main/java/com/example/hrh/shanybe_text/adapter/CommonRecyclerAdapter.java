package com.example.hrh.shanybe_text.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hrh.shanybe_text.viewholder.ViewHolder;
import com.example.hrh.shanybe_text.listener.OnRecyclerClickListerner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrh on 2016/4/22.
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mDatas;
    private LayoutInflater mInflater;
    protected Context context;
    protected int mItemLayoutId;
    protected ViewHolder temp;
    private OnRecyclerClickListerner onRecyclerClickListerner;

    public CommonRecyclerAdapter(Context context, int LayoutId) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = LayoutId;
        mDatas = new ArrayList<>();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public void addDatas(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
    }

    public List<T> getDatas() {
        return mDatas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).
                inflate(mItemLayoutId, parent, false);

        final ViewHolder viewHolder = ViewHolder.get(context, parent, view, onRecyclerClickListerner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        temp = (ViewHolder)holder;
        conver(temp, getItem(position), position);
        temp.GetConverView().setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public abstract void conver(ViewHolder viewHolder, T item, int position);

    public void setOnItemClickListener(OnRecyclerClickListerner listener) {
        this.onRecyclerClickListerner = listener;
    }
}
