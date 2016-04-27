package com.example.hrh.shanybe_text.ui.letter.presenter;

import android.text.SpannableString;
import android.widget.TextView;

import com.example.hrh.shanybe_text.domain.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.letter.model.LetterModel;
import com.example.hrh.shanybe_text.ui.letter.model.LetterSelectCallBack;
import com.example.hrh.shanybe_text.ui.letter.view.LetterView;

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

            @Override
            public void showText(SpannableString spannableString) {

            }
        });
    }

    public void searchData(String text) {
        letterModel.search(text);
    }
    public void getWordInfo(String letter) {
        letterModel.fetchWordData(letter);
    }

    public void highCode(CharSequence text) {
        letterModel.highData(text, new LetterSelectCallBack() {

            @Override
            public void success() {

            }

            @Override
            public void failed() {

            }

            @Override
            public void update(LetterBean list) {
//                letterView.showLoading(list);
            }

            @Override
            public void showText(SpannableString spannableString) {
                letterView.showTextSpan(spannableString);
            }
        });
    }

    public void setWordSelect(int selectedIndex) {
        letterModel.setWordLevel(selectedIndex);
    }
}
