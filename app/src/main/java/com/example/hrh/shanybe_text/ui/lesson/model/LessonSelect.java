package com.example.hrh.shanybe_text.ui.lesson.model;

import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.ui.main.model.MainSelectCallBack;
import com.example.hrh.shanybe_text.util.DataBaseManager;
import com.shanbei.greendao.DaoSession;
import com.shanbei.greendao.ScallopLessonInfo;
import com.shanbei.greendao.ScallopLessonInfoDao;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hrh on 2016/4/23.
 */
public class LessonSelect {

    List<MainMenuModel> mainMenuModels = new ArrayList<MainMenuModel>();


    private void fetchDataCache(Observer<MainMenuModel> observable, String unit) {
        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.from(getLessonInfo(unit)).distinct()
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                })
                .map(new Func1<String, MainMenuModel>() {
                    @Override
                    public MainMenuModel call(String s) {
                        MainMenuModel mainMenuModel = new MainMenuModel(s);
                        return mainMenuModel;
                    }
                })
                .filter(new Func1<MainMenuModel, Boolean>() {
                    @Override
                    public Boolean call(MainMenuModel mainMenuModel) {
                        return !mainMenuModel.getText().equals("1 课程");
                    }
                })
                .flatMap(new Func1<MainMenuModel, Observable<MainMenuModel>>() {
                    @Override
                    public Observable<MainMenuModel> call(MainMenuModel mainMenuModel) {
                        return Observable.just(mainMenuModel);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    public void fetchData(String unit, final LessonSelectCallBack callBack) {
        mainMenuModels.clear();
        Observer<MainMenuModel> observer = new Observer<MainMenuModel>() {
            @Override public void onCompleted() {
                callBack.success(mainMenuModels);
            }

            @Override public void onError(Throwable e) {
                callBack.failed();
            }

            @Override public void onNext(MainMenuModel weather) {
                mainMenuModels.add(weather);
                callBack.update();
            }
        };

        fetchDataCache(observer, unit);
    }

    private List<String> getLessonInfo(String unit) {
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        ScallopLessonInfoDao lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
        List<ScallopLessonInfo> infos = lessonInfoDao.queryBuilder().where(ScallopLessonInfoDao.Properties.UnitBelong.eq(unit)).list();
        List<String> result = new ArrayList<>();

        for(ScallopLessonInfo scallopLessonInfo : infos) {
            result.add(scallopLessonInfo.getLessonList());
        }
//        Set<String> infoString = new HashSet<>();
        return result;
    }

}
