package com.example.hrh.shanybe_text.ui.main.model;

import com.example.hrh.shanybe_text.domain.main.MainMenuModel;

import java.util.List;

/**
 * Created by hrh on 2016/4/23.
 */
public interface MainSelectCallBack {
    public void success(List<MainMenuModel> list);
    public void failed();
    public void update();
}
