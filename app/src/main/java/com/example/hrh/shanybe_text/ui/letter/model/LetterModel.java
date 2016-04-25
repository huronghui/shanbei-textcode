package com.example.hrh.shanybe_text.ui.letter.model;

import android.util.Log;
import android.widget.TextView;

import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.lesson.model.LessonSelectCallBack;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.util.DataBaseManager;
import com.example.hrh.shanybe_text.util.TextJustification;
import com.shanbei.greendao.DaoSession;
import com.shanbei.greendao.ScallopLessonInfo;
import com.shanbei.greendao.ScallopLessonInfoDao;
import com.shanbei.greendao.ScallopWordDao;

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
public class LetterModel {

//    List<LetterBean> mainMenuModels = new ArrayList<LetterBean>();

    private void fetchDataCache(Observer<LetterBean> observable, final TextView textView, String letter) {
//        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.just(getLessonInfo(letter))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
//                        return TextJustification.justifyword(s, textView, textView.getMeasuredWidth());
                        return s;
                    }
                })
                .map(new Func1<String, LetterBean>() {
                    @Override
                    public LetterBean call(String s) {
                        LetterBean mainMenuModel = new LetterBean(s);
                        return mainMenuModel;
                    }
                })
//                .filter(new Func1<LetterBean, Boolean>() {
//                    @Override
//                    public Boolean call(LetterBean mainMenuModel) {
//                        return !mainMenuModel.getText().equals("1 课程");
//                    }
//                })
                .flatMap(new Func1<LetterBean, Observable<LetterBean>>() {
                    @Override
                    public Observable<LetterBean> call(LetterBean mainMenuModel) {
                        return Observable.just(mainMenuModel);
                    }
                })
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    public void fetchData(String letter, TextView textView, final LetterSelectCallBack callBack) {
//        mainMenuModels.clear();
        Observer<LetterBean> observer = new Observer<LetterBean>() {
            @Override public void onCompleted() {
               callBack.success();
            }

            @Override public void onError(Throwable e) {
                callBack.failed();
            }

            @Override public void onNext(LetterBean bean) {
//                mainMenuModels.add(weather);
                callBack.update(bean);
            }
        };

        fetchDataCache(observer, textView, letter);
    }

    private String getLessonInfo(String unit) {
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        ScallopLessonInfoDao lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
        ScallopLessonInfo currentLessonInfo = lessonInfoDao.queryBuilder().where(ScallopLessonInfoDao.Properties.LessonList.eq(unit)).list().get(0);
//        List<String> result = new ArrayList<>();

//        for(ScallopLessonInfo scallopLessonInfo : currentLessonInfo) {
//            result.add(scallopLessonInfo.getLessonList());
//        }
//        Set<String> infoString = new HashSet<>();
        return currentLessonInfo.getContent();
    }
}
