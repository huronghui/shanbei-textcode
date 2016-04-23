package com.example.hrh.shanybe_text.ui.lesson.presenter;

import com.example.hrh.shanybe_text.ui.lesson.model.LessonSelect;
import com.example.hrh.shanybe_text.ui.lesson.model.LessonSelectCallBack;
import com.example.hrh.shanybe_text.ui.lesson.view.LessonView;
import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public class LessonSelectPresenter {

    private LessonSelect lessonSelect;
    private LessonView lessonView;

    public LessonSelectPresenter(LessonView lessonView) {
        this.lessonView = lessonView;
        lessonSelect = new LessonSelect();
    }

    public void getInfo() {
        lessonSelect.fetchData(new LessonSelectCallBack() {
            @Override
            public void success(List<MainMenuModel> list) {
                lessonView.hideLoading(list);
            }

            @Override
            public void failed() {
                lessonView.failedLoading();
            }

            @Override
            public void update() {
                lessonView.showLoading();
            }
        });
    }
}
