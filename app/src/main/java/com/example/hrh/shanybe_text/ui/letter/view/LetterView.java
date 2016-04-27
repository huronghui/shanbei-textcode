package com.example.hrh.shanybe_text.ui.letter.view;

import android.text.SpannableString;

import com.example.hrh.shanybe_text.domain.letter.LetterBean;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LetterView {
    void showLoading(LetterBean list);
    void hideLoading();
    void failedLoading();
    void showTextSpan(SpannableString spannableString);
}
