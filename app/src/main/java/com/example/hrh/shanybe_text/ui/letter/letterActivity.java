package com.example.hrh.shanybe_text.ui.letter;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.letter.presenter.LetterSelectPresenter;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/23.
 */
public class letterActivity extends BaseActivity implements LetterView{

    @Bind(R.id.tvHead)
    TextView tvHead;
    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.action_a)
    FloatingActionButton actionA;
    @Bind(R.id.action_b)
    FloatingActionButton actionB;
    @Bind(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;

    LetterSelectPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_letter;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        tvHead.setText(" unit one");
//        tvContent.setText(getinfo());
    }

    @Override
    public void initData() {
        presenter = new LetterSelectPresenter(this);
        presenter.getInfo();
    }

    @Override
    public void showLoading(LetterBean list) {
        tvContent.setText(list.getEnglishContent());
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void failedLoading() {

    }
}
