package com.owm.lottery.presenter.main.impl;

import com.owm.lottery.presenter.common.BasePresenter;
import com.owm.lottery.presenter.main.IMain;

/**
 * 主界面控制器
 * Created by ouweiming on 2017/3/30.
 */

public class MainPresenter extends BasePresenter implements IMain.MainPresenter {

    private IMain.MainView mView;

    public MainPresenter(IMain.MainView view) {
        mView = view;
    }


}
