package com.example.hrh.shanybe_text.base;

import android.app.Application;
import android.content.Context;

import com.example.hrh.shanybe_text.exception.CrashHandler;
import com.example.hrh.shanybe_text.util.SdCardUtil;

/**
 * Created by hrh on 2016/4/22.
 */
public class BaseApplication extends Application{

    public static String cacheDir = "";
    private volatile static BaseApplication mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.init(new CrashHandler(getApplicationContext()));

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */

        if (getApplicationContext().getExternalCacheDir() != null && SdCardUtil.ExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();

        }
        else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public BaseApplication() {

    }


    /**
     * Application  在android中默认是单例模式，
     * 但此处经过双重校验
     * 确保 Application 的实例只有一个
     * @return
     */
    public BaseApplication getInstance() {
        BaseApplication temp = mAppContext;
        if(temp == null) {
            synchronized (BaseApplication.class) {
                if(temp == null) {
                    temp = new BaseApplication();
                }
            }
        }
        return temp;
    }
}
