package com.owm.lottery.model.db.dao;

import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.apiplus.Point;
import com.owm.lottery.model.db.DbManagerHelper;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 划图 dao
 * Created by ouweiming on 2017/3/31.
 */

public class GraphDao {

    public static void save(List<Graph> data){
        if (data == null || data.isEmpty()) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            save(data.get(i));
        }
    }

    public static void save(Graph data){
        if (data == null) {
            return;
        }
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            db.saveOrUpdate(data);
            List<Point> points = data.getPoints();
            for (int i = 0; i < points.size(); i++) {
                points.get(i).setGraphId(data.getId());
            }
            PointDao.save(points);
        } catch (DbException e) {
            e.printStackTrace();
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

    /**
     * 获取总条数
     * @return 总条数
     */
    public static long selectCount() {
        long result = 0;
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            result = db.selector(Graph.class).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询条数 在begin~end之前
     * @param begin 前面
     * @param end 后面
     * @return 条数
     */
    public static long selectCountByPercentage(float begin, float end) {
        long result = 0;
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            result = db.selector(Graph.class).where("percentage", "BETWEEN", new Float[]{begin, end}).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询 Graph.percentage = percentage 的条数
     * @param percentage percentage
     * @return 条数
     */
    public static long selectCountByPercentage(float percentage) {
        long result = 0;
        DbManager db = x.getDb(DbManagerHelper.getDaoConfig());
        try {
            result = db.selector(Graph.class).where("percentage", "=", percentage).count();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return result;
    }

}
