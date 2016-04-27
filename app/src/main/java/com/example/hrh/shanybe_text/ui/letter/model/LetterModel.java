package com.example.hrh.shanybe_text.ui.letter.model;

import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import com.example.hrh.shanybe_text.domain.letter.LetterBean;
import com.example.hrh.shanybe_text.domain.letter.PositionBean;
import com.example.hrh.shanybe_text.util.DataBaseManager;
import com.example.hrh.shanybe_text.view.TagImageSpan;
import com.shanbei.greendao.DaoSession;
import com.shanbei.greendao.ScallopLessonInfo;
import com.shanbei.greendao.ScallopLessonInfoDao;
import com.shanbei.greendao.ScallopWord;
import com.shanbei.greendao.ScallopWordDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observer;

/**
 * Created by hrh on 2016/4/23.
 */
public class LetterModel {

//    List<LetterBean> mainMenuModels = new ArrayList<LetterBean>();
    private Map<String, List<PositionBean>> map;
    private ScallopLessonInfoDao lessonInfoDao;
    private ScallopWordDao wordInfoDao;
    private ScallopLessonInfo currentLessonInfo;
    private int wordLevel;

    public LetterModel() {
        map = new HashMap<String, List<PositionBean>>();
        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
        lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
        wordInfoDao = mDaoSession.getScallopWordDao();
        wordLevel = 6;
    }

    private void fetchDataCache(Observer<LetterBean> observable, final TextView textView, String letter) {
//        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
//        Observable.just(getLessonInfo(letter))
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
////                        return TextJustification.justifyword(s, textView, textView.getMeasuredWidth());
//                        return s;
//                    }
//                })
//                .map(new Func1<String, LetterBean>() {
//                    @Override
//                    public LetterBean call(String s) {
//                        LetterBean mainMenuModel = new LetterBean(s);
//                        return mainMenuModel;
//                    }
//                })
//                .filter(new Func1<LetterBean, Boolean>() {
//                    @Override
//                    public Boolean call(LetterBean mainMenuModel) {
//                        return !mainMenuModel.getText().equals("1 课程");
//                    }
//                })
//                .flatMap(new Func1<LetterBean, Observable<LetterBean>>() {
//                    @Override
//                    public Observable<LetterBean> call(LetterBean mainMenuModel) {
//                        return Observable.just(mainMenuModel);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observable);
    }

    public void fetchData(String letter, TextView textView, final LetterSelectCallBack callBack) {
//        mainMenuModels.clear();
//        Observer<LetterBean> observer = new Observer<LetterBean>() {
//            @Override public void onCompleted() {
//               callBack.success();
//            }
//
//            @Override public void onError(Throwable e) {
//                callBack.failed();
//            }
//
//            @Override public void onNext(LetterBean bean) {
////                mainMenuModels.add(weather);
//                callBack.update(bean);
//            }
//        };
//
//        fetchDataCache(observer, textView, letter);

        callBack.update(getLessonInfo(letter));
    }

    private LetterBean getLessonInfo(String unit) {
//        DaoSession mDaoSession = DataBaseManager.getInstance().getDaoSession();
//        ScallopLessonInfoDao lessonInfoDao = mDaoSession.getScallopLessonInfoDao();
         currentLessonInfo = lessonInfoDao.queryBuilder().where(ScallopLessonInfoDao.Properties.LessonList.eq(unit)).list().get(0);
//        List<String> result = new ArrayList<>();

//        for(ScallopLessonInfo scallopLessonInfo : currentLessonInfo) {
//            result.add(scallopLessonInfo.getLessonList());
//        }
//        Set<String> infoString = new HashSet<>();
//        search(currentLessonInfo.getContent());

        LetterBean bean = new LetterBean(currentLessonInfo.getTitle(),
                currentLessonInfo.getContent(), currentLessonInfo.getInfo());

        return bean;
    }

    public void search(String string) {
        for (String key:map.keySet()){
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(string);
            while (m.find()) {
                PositionBean pb = new PositionBean(m.start(), m.end());
                map.get(key).add(pb);
            }
        }
    }

    public void highData(CharSequence charSequence, final LetterSelectCallBack callBack) {
        SpannableString s = new SpannableString(charSequence);
        for (String key : map.keySet()) {
            ScallopWord wordInfo = wordInfoDao.queryBuilder().where(ScallopWordDao.Properties.Content.eq(key)).list().get(0);
            if (Integer.parseInt(wordInfo.getLevel()) <= wordLevel) {
                ArrayList<PositionBean> array = (ArrayList<PositionBean>) map
                        .get(key);
                for (int i = 0; i < array.size(); i++) {
                    s.setSpan(new TagImageSpan(0, 0), array.get(i)
                                    .getStart(), array.get(i).getEnd(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        callBack.showText(s);
//        tvContent.setText(s);
    }

    public void fetchWordData(String letter) {
        List<ScallopWord> wordsList = wordInfoDao.queryBuilder().where(ScallopWordDao.Properties.LessonBelong.eq(letter)).list();
//        map = new HashMap<String, List<PositionBean>>();
        for (ScallopWord wordInfo:wordsList){
            Log.i("Letter", wordInfo.getContent());
            map.put(wordInfo.getContent(), new ArrayList<PositionBean>());
        }
    }

    public void setWordLevel(int selectedIndex) {
        wordLevel = selectedIndex;
    }
}
