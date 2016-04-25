package com.example.hrh.shanybe_text.ui.letter.presenter;

import android.widget.TextView;

import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.lesson.model.LessonSelectCallBack;
import com.example.hrh.shanybe_text.ui.letter.model.LetterModel;
import com.example.hrh.shanybe_text.ui.letter.model.LetterSelectCallBack;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public class LetterSelectPresenter {

    private LetterModel letterModel;
    private LetterView letterView;

    public LetterSelectPresenter(LetterView letterView) {
        this.letterView = letterView;
        letterModel = new LetterModel();
    }

    public void getInfo(TextView textView, String letter) {
        letterModel.fetchData(letter, textView, new LetterSelectCallBack() {
            @Override
            public void success() {
                letterView.hideLoading();
            }

            @Override
            public void failed() {
                letterView.failedLoading();
            }

            @Override
            public void update(LetterBean bean) {
               letterView.showLoading(bean);
            }
        });
    }
}
