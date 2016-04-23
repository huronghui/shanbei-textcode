package com.example.hrh.shanybe_text.adapter;

import android.content.Context;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.viewholder.ViewHolder;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

/**
 * Created by hrh on 2016/4/23.
 */
public class LessionAdapter extends CommonRecyclerAdapter<MainMenuModel>{


    public LessionAdapter(Context context, int LayoutId) {
        super(context, LayoutId);
    }

    @Override
    public void conver(ViewHolder viewHolder, MainMenuModel item, int position) {
//       TextView textView = (TextView) viewHolder.getView(android.R.id.text1);
        viewHolder.setText(R.id.item_lession, item.getText(), position);
    }
}
