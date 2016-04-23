package com.example.hrh.shanybe_text.ui.lesson.model;

import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.ui.main.model.MainSelectCallBack;

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


    private void fetchDataCache(Observer<MainMenuModel> observable) {
        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.from(name)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " 课程";
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

    public void fetchData(final LessonSelectCallBack callBack) {
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

}
