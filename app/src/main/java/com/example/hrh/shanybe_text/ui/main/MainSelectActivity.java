package com.example.hrh.shanybe_text.ui.main;

import android.content.DialogInterface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.adapter.UnitAdater;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.listener.HidingScrollListener;
import com.example.hrh.shanybe_text.listener.OnRecyclerClickListerner;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.ui.main.presenter.MainSelectPresenter;
import com.example.hrh.shanybe_text.ui.main.view.MainView;
import com.example.hrh.shanybe_text.util.UIHelper;
import com.example.hrh.shanybe_text.weight.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by hrh on 2016/4/23.
 */
public class MainSelectActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        SwipeRefreshLayout.OnRefreshListener, MainView{

//    List<MainMenuModel> mainMenuModels = new ArrayList<MainMenuModel>();
    private final String TAG = MainSelectActivity.class.getSimpleName();
    protected UnitAdater mAdapter;
    private MainSelectPresenter presenter;
    @Bind(R.id.bannner)
    ImageView bannner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.coord)
    CoordinatorLayout coord;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        swiprefresh.setOnRefreshListener(this);
        //标题
        toolbarLayout.setTitle(" ");

        //fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.showFab();
            }
        });
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        final int fabBottomMargin = lp.bottomMargin;

        //RecyclerView 控件属性的初始化
        recyclerview.setLayoutManager(new LinearLayoutManager(MainSelectActivity.this));
        recyclerview.setHasFixedSize(true);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        recyclerview.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                fab.animate()
                        .translationY(fab.getHeight() + fabBottomMargin)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();
            }


            @Override
            public void onShow() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
        });

        //设置适配器
        if (mAdapter != null) {
            recyclerview.setAdapter(mAdapter);
        } else {
            mAdapter = new UnitAdater(this, R.layout.item_unit );
            recyclerview.setAdapter(mAdapter);
        }
        mAdapter.setOnItemClickListener(onRecyclerViewItemClickListerner);
        //初始化抽屉
        initDrawer();
    }

    @Override
    public void initData() {
        presenter = new MainSelectPresenter(this);
        onRefresh();

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        swiprefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading(List<MainMenuModel> list) {

        swiprefresh.setRefreshing(false);
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void tounitActivity() {

    }

    @Override
    public void showFabDialog() {
        new AlertDialog.Builder(MainSelectActivity.this).setTitle("点赞")
                .setMessage("去项目地址给作者个Star，鼓励下作者୧(๑•̀⌄•́๑)૭✧")
                .setPositiveButton("好叻", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void failedLoading() {
        Snackbar.make(fab, "网络出了些问题？( ´△｀)", Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 初始化抽屉
     */
    private void initDrawer() {
        navView.setNavigationItemSelectedListener(this);

        View headerLayout = navView.inflateHeaderView(R.layout.nav_header_main);
        RelativeLayout headerBackground = (RelativeLayout) headerLayout.findViewById(R.id.header_background);

        headerBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.header_back_day));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_set:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_city:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onRefresh() {
       presenter.showInfo();
    }

    private OnRecyclerClickListerner onRecyclerViewItemClickListerner =
            new OnRecyclerClickListerner() {
                @Override
                public void onItemClick(View view, int data) {
                    Toast.makeText(MainSelectActivity.this, mAdapter.getDatas().get(data).getText(), Toast.LENGTH_LONG).show();
                    UIHelper.showLessonActivity(MainSelectActivity.this);
                }
            };
}
