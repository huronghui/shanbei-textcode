package com.example.hrh.shanybe_text.ui.lesson.model;

import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LessonSelectCallBack {

    public void success(List<MainMenuModel> list);
    public void failed();
    public void update();

}
