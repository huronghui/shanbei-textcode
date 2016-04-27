package com.example.hrh.shanybe_text.ui.lesson;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.adapter.LessionAdapter;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.listener.OnRecyclerClickListerner;
import com.example.hrh.shanybe_text.ui.lesson.presenter.LessonSelectPresenter;
import com.example.hrh.shanybe_text.ui.lesson.view.LessonView;
import com.example.hrh.shanybe_text.ui.letter.LetterActivity;
import com.example.hrh.shanybe_text.domain.main.MainMenuModel;
import com.example.hrh.shanybe_text.util.UIHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/23.
 */
public class LessonSelectActivity extends BaseActivity implements LessonView, SwipeRefreshLayout.OnRefreshListener{

    public static String LessonBelong = "LessonSelectBelong";
    protected LessionAdapter mAdapter;
    private LessonSelectPresenter presenter;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private String unit;
    @Override
    protected int getLayoutId() {

        return R.layout.activity_lesson;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        swiprefresh.setOnRefreshListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(LessonSelectActivity.this));
        recyclerview.setHasFixedSize(true);
//        recyclerview.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));

        if (mAdapter != null) {
            recyclerview.setAdapter(mAdapter);
        } else {
            mAdapter = new LessionAdapter(this, R.layout.item_lession );
            recyclerview.setAdapter(mAdapter);
        }
        mAdapter.setOnItemClickListener(onRecyclerViewItemClickListerner);
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        unit = bundle.getString(LessonBelong);
        presenter = new LessonSelectPresenter(this);
        onRefresh();

    }

    /**
     * 数据加载中显示的状态
     */
    @Override
    public void showLoading() {
        swiprefresh.setRefreshing(true);
    }

    /**
     * 数据加载完，显示的状态
     * @param list
     */
    @Override
    public void hideLoading(List<MainMenuModel> list) {
        //设置适配器
        swiprefresh.setRefreshing(false);
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void tounitActivity() {

    }

    @Override
    public void showFabDialog() {

    }

    /**
     * 数据加载失败，显示的状态
     */
    @Override
    public void failedLoading() {

    }

    private OnRecyclerClickListerner onRecyclerViewItemClickListerner =
            new OnRecyclerClickListerner() {
                @Override
                public void onItemClick(View view, int data) {
                    Bundle bundle = new Bundle();
                    String[] titles = mAdapter.getDatas().get(data).getText().split("  ");
                    bundle.putString(LetterActivity.LetterStirng, titles[0]);
                    UIHelper.showLetterActivity(LessonSelectActivity.this, bundle);
                }
            };

    @Override
    public void onRefresh() {
        presenter.getInfo(unit);
    }
}
