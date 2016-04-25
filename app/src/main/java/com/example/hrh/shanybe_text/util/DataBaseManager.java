package com.example.hrh.shanybe_text.util;

import android.database.sqlite.SQLiteDatabase;

import com.example.hrh.shanybe_text.base.BaseActivity;
import com.example.hrh.shanybe_text.base.BaseApplication;
import com.shanbei.greendao.DaoMaster;
import com.shanbei.greendao.DaoSession;

/**
 * 数据库管理类
 *
 */
public class DataBaseManager {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "shanbay-db";
    public static DataBaseManager INSTANCE = null;
    private DaoSession daoSession;

    private DataBaseManager() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance().getApplicationContext(), DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public synchronized static DataBaseManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataBaseManager();
        }
        return INSTANCE;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
