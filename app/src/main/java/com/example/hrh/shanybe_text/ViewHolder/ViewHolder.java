package com.example.hrh.shanybe_text.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{

	private SparseArray<View> mView = null;
	private int mPosition;
	private View mCurrentView;



//	public ViewHolder(Context context, ViewGroup viewGroup, int LayoutId,
//			int position) {
//		this.mPosition = position;
//		this.mView = new SparseArray<View>();
//		mCurrentView = LayoutInflater.from(context).inflate(LayoutId, viewGroup,false);
//		mCurrentView.setTag(this);
//	}

	public ViewHolder(View view) {
		super(view);
		this.mView = new SparseArray<View>();
		mCurrentView = view;
//		this.mPosition = position;
//		this.mView = new SparseArray<View>();
//		mCurrentView = LayoutInflater.from(context).inflate(LayoutId, viewGroup, false);
//		mCurrentView.setTag(this);

	}
	/**
	 * 拿到一个ViewHolder的实例对象
	 * @param context
	 * @param parent
	 * @param layoutid
	 * @return
	 */
	public static ViewHolder get(Context context, ViewGroup parent,
									int layoutid) {

		View view = LayoutInflater.from(context).
				inflate(layoutid, parent, false);

		return new ViewHolder(view);

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
	public ViewHolder setText(int LayoutId, String text) {
		TextView view = getView(LayoutId);
		if (text != null) {
			view.setText(text);
		}
		return this;
	}

//	public ViewHolder setCricleImage(int LayoutId, String url,Context context) {
//		ImageView view = getView(LayoutId);
//
//		return this;
//	}
//
//	public ViewHolder setImage(int LayoutId, String url) {
//		ImageView view = getView(LayoutId);
////		view.setTag(url);
////		url = Appconfig.BASIC_URL + url + "/cp_file";
//
//		return this;
//	}
//
//	public ViewHolder setNativeImage(int LayoutId, int id) {
//		ImageView view = getView(LayoutId);
//		view.setImageResource(id);
//		return this;
//	}

//	public int getPosition() {
//		return mPosition;
//	}
}
