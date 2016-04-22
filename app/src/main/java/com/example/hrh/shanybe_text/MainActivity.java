package com.example.hrh.shanybe_text;

import android.content.DialogInterface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

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

import com.example.hrh.shanybe_text.adapter.MainMenuModel;
import com.example.hrh.shanybe_text.adapter.TestAdater;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.listener.HidingScrollListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        SwipeRefreshLayout.OnRefreshListener{

    List<MainMenuModel> mainMenuModels = new ArrayList<MainMenuModel>();
    private final String TAG = MainActivity.class.getSimpleName();
    protected TestAdater mAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FloatingActionButton fab;
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView bannner;
    private ProgressBar mProgressBar;
    private RelativeLayout headerBackground;

    private RecyclerView mRecyclerView;
    //private Weather mWeatherData = new Weather();
//    private WeatherAdapter mAdapter;
//    private Observer<Weather> observer;

    private long exitTime = 0; ////记录第一次点击的时间

//    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    public AMapLocationClientOption mLocationOption = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bannner = (ImageView) findViewById(R.id.bannner);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiprefresh);
        mRefreshLayout.setOnRefreshListener(this);

        //标题
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");

        //彩蛋-夜间模式
        Calendar calendar = Calendar.getInstance();
//        mSetting.putInt(Setting.HOUR, calendar.get(Calendar.HOUR_OF_DAY));
//        setStatusBarColorForKitkat(R.color.colorSunrise);
//        if (mSetting.getInt(Setting.HOUR, 0) < 6 || mSetting.getInt(Setting.HOUR, 0) > 18) {
//            Glide.with(this).load(R.mipmap.sunset).diskCacheStrategy(DiskCacheStrategy.ALL).into(bannner);
//            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorSunset));
//            setStatusBarColorForKitkat(R.color.colorSunset);
//        }

        //fab
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showFabDialog();
            }
        });
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        final int fabBottomMargin = lp.bottomMargin;
        //recclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override public void onHide() {
                fab.animate()
                        .translationY(fab.getHeight() + fabBottomMargin)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();
            }


            @Override public void onShow() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }
        });

        if (mAdapter != null) {
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new TestAdater(this,mainMenuModels,android.R.layout.simple_list_item_1 );
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    private void showFabDialog() {
        new AlertDialog.Builder(MainActivity.this).setTitle("点赞")
                .setMessage("去项目地址给作者个Star，鼓励下作者୧(๑•̀⌄•́๑)૭✧")
                .setPositiveButton("好叻", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
//                        Uri uri = Uri.parse(getString(R.string.app_html));   //指定网址
//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_VIEW);           //指定Action
//                        intent.setData(uri);                            //设置Uri
//                        MainActivity.this.startActivity(intent);        //启动Activity
                    }
                })
                .show();

        initDrawer();
    }

    /**
     * 初始化抽屉
     */
    private void initDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        headerBackground = (RelativeLayout) headerLayout.findViewById(R.id.header_background);
//        if (mSetting.getInt(Setting.HOUR, 0) < 6 || mSetting.getInt(Setting.HOUR, 0) > 18) {
//            //headerBackground.setBackground(this.getResources().getDrawable(R.mipmap.header_back_night)); 过时
            headerBackground.setBackground(ContextCompat.getDrawable(this, R.drawable.header_back_day));
//        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void initData() {
        onRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
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
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onRefresh() {
        mainMenuModels.clear();
        mProgressBar.setVisibility(View.INVISIBLE);
        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.from(name)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " 视频";
                    }
                })
                .map(new Func1<String, MainMenuModel>() {
                    @Override
                    public MainMenuModel call(String s) {
                        MainMenuModel mainMenuModel = new MainMenuModel(s);
//                        mainMenuModels.add(mainMenuModel);
                        return mainMenuModel;
                    }
                })
                .filter(new Func1<MainMenuModel, Boolean>() {
                    @Override
                    public Boolean call(MainMenuModel mainMenuModel) {
                        return !mainMenuModel.getText().equals("1 视频");
                    }
                })
                .flatMap(new Func1<MainMenuModel, Observable<MainMenuModel>>() {
                    @Override
                    public Observable<MainMenuModel> call(MainMenuModel mainMenuModel) {
                        return Observable.just(mainMenuModel);
                    }
                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MainMenuModel>() {
                    @Override
                    public void call(MainMenuModel s) {
                        mainMenuModels.add(s);
                    }
                });

        mAdapter.setData(mainMenuModels);
        mAdapter.notifyDataSetChanged();
    }
}
