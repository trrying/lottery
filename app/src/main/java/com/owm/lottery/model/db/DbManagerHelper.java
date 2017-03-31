package com.owm.lottery.model.db;

import org.xutils.DbManager;

/**
 * Db 配置
 * Created by ouweiming on 2017/3/31.
 */

public class DbManagerHelper {

    /**  数据库名称  */
    public static final String DB_NAME = "lottery.db";
    /**  数据库版本  */
    public static final int DB_VERSON = 1;

    public static DbManager.DaoConfig daoConfig;

    public static DbManager.DaoConfig getDaoConfig(){
        if (daoConfig == null) {
            synchronized (DbManagerHelper.class) {
                if (daoConfig == null) {
                    daoConfig = new DbManager.DaoConfig()
                            .setDbName(DB_NAME)
                            .setDbVersion(DB_VERSON)
                            .setDbOpenListener(new DbManager.DbOpenListener() {
                                @Override
                                public void onDbOpened(DbManager db) {
                                    // 开启WAL, 对写入加速提升巨大
                                    db.getDatabase().enableWriteAheadLogging();
                                }
                            })
                            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                                @Override
                                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                                }
                            });
                }
            }
        }
        return daoConfig;
    }

}
