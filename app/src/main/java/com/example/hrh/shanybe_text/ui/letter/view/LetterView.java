package com.example.hrh.shanybe_text.ui.letter.view;

import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LetterView {
    void showLoading(LetterBean list);
    void hideLoading();
    void failedLoading();
}
