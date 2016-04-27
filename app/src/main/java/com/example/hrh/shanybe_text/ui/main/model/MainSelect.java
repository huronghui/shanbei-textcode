package com.example.hrh.shanybe_text.ui.main.model;

import com.example.hrh.shanybe_text.domain.main.MainMenuModel;
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
public class MainSelect {

    List<MainMenuModel> mainMenuModels = new ArrayList<MainMenuModel>();
    private int wordLevel;

    private void fetchDataCache(Observer<MainMenuModel> observable) {
//        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.from(getLessonInfo()).distinct()
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
                        return !mainMenuModel.getText().equals("1 单元");
                    }
                })
                .flatMap(new Func1<MainMenuModel, Observable<MainMenuModel>>() {
                    @Override
                    public Observable<MainMenuModel> call(MainMenuModel mainMenuModel) {
                        return Observable.just(mainMenuModel).distinct();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    public void fetchData(final MainSelectCallBack callBack) {
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

        fetchDataCache(observer);
    }

    private List<String> getLessonInfo() {
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        ScallopLessonInfoDao lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
        List<ScallopLessonInfo> infos = lessonInfoDao.loadAll();
        List<String> result = new ArrayList<>();

        for(ScallopLessonInfo scallopLessonInfo : infos) {
            result.add(scallopLessonInfo.getUnitBelong());
        }
//        Set<String> infoString = new HashSet<>();
        return result;
    }


}
