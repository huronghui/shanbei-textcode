package com.example.hrh.shanybe_text.ui.lesson.view;

import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public interface LessonView {
    void showLoading();
    void hideLoading(List<MainMenuModel> list);
    void tounitActivity();
    void showFabDialog();
    void failedLoading();
}
