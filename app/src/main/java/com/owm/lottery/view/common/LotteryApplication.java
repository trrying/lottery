package com.owm.lottery.view.common;

import android.app.Application;

/**
 * Created by ouweiming on 2017/3/30.
 */

public class LotteryApplication extends Application{

    private static LotteryApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static LotteryApplication getApplication() {
        return application;
    }
}
