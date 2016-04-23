package com.example.hrh.shanybe_text.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hrh.shanybe_text.listener.OnRecyclerClickListerner;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private SparseArray<View> mView = null;
	private int mPosition;
	private View mCurrentView;
	private OnRecyclerClickListerner onRecyclerClickListerner;


	public ViewHolder(View view, OnRecyclerClickListerner listerner) {
		super(view);
		this.mView = new SparseArray<View>();
		mCurrentView = view;
		view.setOnClickListener(this);
		this.onRecyclerClickListerner = listerner;
	}
	/**
	 * 拿到一个ViewHolder的实例对象
	 * @param context
	 * @param parent
	 * @param layoutid
	 * @return
	 */
	public static ViewHolder get(Context context, ViewGroup parent,View layoutid, OnRecyclerClickListerner listerner) {
		return new ViewHolder(layoutid, listerner);

	}
	
	/**
	 * 通过控件的Id获取到控件的对象，如果没有的话，就加入到views
	 * @return
	 */
	public View GetConverView() {
		return mCurrentView;
	}
	
	public <T extends View> T getView(int LayoutId) {
		View view = mView.get(LayoutId);
		if (view == null) {
			view = mCurrentView.findViewById(LayoutId);
			mView.put(LayoutId, view);
		}
		return (T)view;
	}
	
	/**
	 * 为TextView 设置字符串
	 * @param LayoutId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int LayoutId, String text, int mPosition) {
		TextView view = getView(LayoutId);
		if (text != null) {
			view.setText(text);
			view.setTag(mPosition);
		}
		return this;
	}

	@Override
	public void onClick(View v) {
		if(onRecyclerClickListerner != null){
			onRecyclerClickListerner.onItemClick(v, getPosition());
		}
	}

	/**
	 * 设置Item点击监听
	 * @param listener
	 */
	public void setOnItemClickListener(OnRecyclerClickListerner listener){
		this.onRecyclerClickListerner = listener;
	}
}
