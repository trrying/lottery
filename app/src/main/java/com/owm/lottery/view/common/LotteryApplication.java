package com.owm.lottery.view.common;

import android.app.Application;

import com.owm.lottery.BuildConfig;

import org.xutils.x;


/**
 * Application
 * Created by ouweiming on 2017/3/30.
 */

public class LotteryApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
