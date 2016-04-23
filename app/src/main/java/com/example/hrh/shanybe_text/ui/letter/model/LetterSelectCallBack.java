package com.example.hrh.shanybe_text.ui.letter.model;

import com.example.hrh.shanybe_text.bean.letter.LetterBean;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LetterSelectCallBack {

    public void success();
    public void failed();
    public void update(LetterBean list);
}
