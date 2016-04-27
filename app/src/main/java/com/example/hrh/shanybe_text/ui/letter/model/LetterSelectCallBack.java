package com.example.hrh.shanybe_text.ui.letter.model;

import android.text.SpannableString;

import com.example.hrh.shanybe_text.domain.letter.LetterBean;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LetterSelectCallBack {

    public void success();
    public void failed();
    public void update(LetterBean list);
    public void showText(SpannableString spannableString);
}
