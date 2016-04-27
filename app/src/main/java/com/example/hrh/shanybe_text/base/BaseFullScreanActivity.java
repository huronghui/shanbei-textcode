package com.example.hrh.shanybe_text.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.hrh.shanybe_text.listener.BaseViewInterface;

/**
 * Created by hrh on 2016/4/26.
 */
public abstract class BaseFullScreanActivity extends Activity implements BaseViewInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        initView();

        initData();
    }

    protected void init(Bundle savedInstanceState) {}

    abstract protected int getLayoutId();
}
