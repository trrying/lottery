package com.owm.lottery.view.common;

import android.app.Application;

import org.xutils.x;


/**
 * Created by ouweiming on 2017/3/30.
 */

public class LotteryApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
    }
}
