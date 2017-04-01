package com.owm.lottery.model.db.dao;

import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.db.DbManagerHelper;
import com.owm.lottery.model.utils.DbUtils;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Lottery db Dao
 * Created by ouweiming on 2017/3/31.
 */

public class LotteryDao {

    public static void save(List<Lottery> data){
        if (data == null || data.isEmpty()) {
            return;
        }
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        for (int i = 0; i < data.size(); i++) {
            try {
                Lottery expect = db.selector(Lottery.class).where("expect", "=", data.get(i).getExpect()).findFirst();
                if (expect != null) {
                    data.get(i).setId(expect.getId());
                }
                db.saveOrUpdate(data.get(i));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Lottery> selectOrderByExpect() {
        List<Lottery> result = null;
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            result = db.selector(Lottery.class).orderBy("expect").findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result != null ? result : new ArrayList<Lottery>();
    }

    public static Lottery selectById(int lotteryId){
        Lottery lottery = null;
        try {
            lottery = DbUtils.getDbManager().findById(Lottery.class, lotteryId);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return lottery;
    }
}
