package com.owm.lottery.model.db.dao;

import com.owm.lottery.model.apiplus.Point;
import com.owm.lottery.model.db.DbManagerHelper;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 划图--点 dao
 * Created by ouweiming on 2017/3/31.
 */

public class PointDao {

    public static void save(List<Point> data){
        if (data == null || data.isEmpty()) {
            return;
        }
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        for (int i = 0; i < data.size(); i++) {
            try {
                db.saveOrUpdate(data.get(i));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Point> selectByGraph(int graphId) {
        List<Point> result = null;
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            result = db.selector(Point.class).where("graph_id", "=", graphId).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result != null ? result : new ArrayList<Point>();
    }

}
