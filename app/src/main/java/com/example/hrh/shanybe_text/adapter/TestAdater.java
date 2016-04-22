package com.example.hrh.shanybe_text.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.hrh.shanybe_text.ViewHolder.ViewHolder;

import java.util.List;

/**
 * Created by hrh on 2016/4/22.
 */
public class TestAdater extends CommonRecyclerAdapter<MainMenuModel>{


    public TestAdater(Context context, List<MainMenuModel> datas, int LayoutId) {
        super(context, datas, LayoutId);
    }

    @Override
    public void conver(ViewHolder viewHolder, MainMenuModel item, int position) {
//       TextView textView = (TextView) viewHolder.getView(android.R.id.text1);
        viewHolder.setText(android.R.id.text1, item.getText());
    }
}
