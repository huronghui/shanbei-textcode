package com.example.hrh.shanybe_text.ui.letter.model;

import android.util.Log;
import android.widget.TextView;

import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.lesson.model.LessonSelectCallBack;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.util.TextJustification;

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

    private void fetchDataCache(Observer<LetterBean> observable, final TextView textView) {
//        String[] name = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        Observable.just(getinfo())
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }

    public void fetchData(TextView textView, final LetterSelectCallBack callBack) {
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

        fetchDataCache(observer, textView);
    }

    private String getinfo() {
        return "Several cases have been reported in Russia recently of people who can detect colours with their fingers, and even see through solid and walls. One case concerns and eleven-year-old schoolgirl, Vera Petrova, who has normal vision but who can also perceive things with different parts of her skin, and through solid walls. This ability was first noticed by her father. One day she came into his office and happened to put her hands on the door of a locked safe. Suddenly she asked her father why he kept so many old newspapers locked away there, and even described the way they were done up in bundles.\n" +
                "    Vera's curious talent was brought to the notice of a scientific research institute in the town of Ulyanovsk, near where she lives, and in April she was given a series of tests by a special commission of the Ministry of Health of the Russian Federal Republic. During these tests she was able to read a newspaper through an opaque screen and, stranger still, by moving her elbow over a child's game of Lotto she was able to describe the figures and colours printed on it; and, in another instance, wearing stockings and slippers, to make out with her foot the outlines and colours of a picture hidden under a carpet. Other experiments showed that her knees and shoulders had a similar sensitivity. During all these tests Vera was blindfold; and, indeed, except when blindfold she lacked the ability to perceive things with her skin. It was also found that although she could perceive things with her fingers this ability ceased the moment her hands were wet.\n" + "Seeing hands\n" +
                "俄罗斯最近报导了几个事例，有人能用手指看书识字和辨认颜色，甚至能透过厚实的门和墙看到东西。 其中有一例谈到有一个名叫维拉.彼托洛娃的11岁学生。她的视力与常人一样，但她还能用皮肤的不同部位辨认东西，甚至看穿坚实的墙壁。是她父亲首先发现她这一功能的。一天，维拉走进父亲的办公室，偶然把手放在一个锁着的保险柜的门上，她突然问父亲为什么把这么多的旧报纸锁在柜子里，还说了报纸捆扎的情况。\n" +
                "维拉的特异功能引起了她家附近乌里扬诺夫斯克城一个科研单位的注意。4月里，俄罗斯卫生部一个特别委员会对她进行了一系列的测试。在这些测试中，她能隔着不透明的屏幕读报纸。更为奇怪的是，她把肘部在儿童玩的“罗托”纸牌上移动一下，便能说出印在纸牌上的数字和颜色。还有一次，她穿着长筒袜子和拖鞋，能用脚步识别出藏在地毯下面的一幅画的轮廓和颜色。其他实验表明，她的膝盖和双肩有类似的感觉能力，在所有这些实验中，维拉的双眼都是蒙着的。如果不蒙上双眼她的皮肤就不再具有识别物体的能力。这是千真万确的。同时还发现，尽管她能用手指识别东西，但她的手一旦弄湿，这种功能便会立即消失。\n";
    }
}
