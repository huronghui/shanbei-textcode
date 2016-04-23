package com.example.hrh.shanybe_text.ui.main.presenter;

import com.example.hrh.shanybe_text.ui.main.model.MainMenuModel;
import com.example.hrh.shanybe_text.ui.main.model.MainSelect;
import com.example.hrh.shanybe_text.ui.main.model.MainSelectCallBack;
import com.example.hrh.shanybe_text.ui.main.view.MainView;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public class MainSelectPresenter {

    private MainSelect mainSelect;
    private MainView mainView;

    public MainSelectPresenter(MainView mainView) {
        this.mainView = mainView;
        mainSelect = new MainSelect();
    }

    public void showFab() {
        mainView.showFabDialog();
    }

    public void showInfo() {
        mainSelect.fetchData(new MainSelectCallBack() {

            @Override
            public void success(List<MainMenuModel> list) {
                mainView.hideLoading(list);
            }

            @Override
            public void failed() {
                mainView.failedLoading();
            }

            @Override
            public void update() {
                mainView.showLoading();
            }
        });
    }
}
