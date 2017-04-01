package com.owm.lottery.model.utils;

import com.owm.lottery.model.db.DbManagerHelper;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Db 工具类
 * Created by ouweiming on 2017/4/1.
 */

public class DbUtils {

    public static DbManager getDbManager(){
        return x.getDb(DbManagerHelper.getDaoConfig());
    }

}
