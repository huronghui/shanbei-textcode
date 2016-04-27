package com.example.hrh.shanybe_text.ui.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.example.hrh.shanybe_text.R;
import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.ui.main.MainSelectActivity;
import com.example.hrh.shanybe_text.util.CreateLessonInfos;
import com.example.hrh.shanybe_text.util.CreateWordInfos;
import com.example.hrh.shanybe_text.view.BoilingPanView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hrh on 2016/4/26.
 */
public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.loading_view)
    BoilingPanView loadingView;

    @Override
    protected int getLayoutId() {
        return R.layout.loading_layout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        loadingView.beginFirstInAnim();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(WelcomeActivity.this);
        if (sharedPreferences.getBoolean("firstTime",true)){
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    CreateLessonInfos.createLessonInfos();
                    CreateWordInfos.createScallopWords();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
//                    loadingView.resetAnim();
                    mHandler.sendEmptyMessage(0);
                }
            }).start();
        }else {
            new Handler().postDelayed(r, 3000);
        }
    }

    @Override
    public void initData() {

    }

    public Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {

            startActivity(new Intent(WelcomeActivity.this,MainSelectActivity.class));
            finish();
            super.handleMessage(msg);
        }
    };

    Runnable r = new Runnable() {
        @Override
        public void run() {
//            loadingView.resetAnim();
            mHandler.sendEmptyMessage(0);
        }
    };
}
