package com.owm.lottery.model.apiplus;

import com.google.gson.annotations.Expose;
import com.owm.lottery.model.common.AppHolder;
import com.owm.lottery.model.db.dao.LotteryDao;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 画图中的点
 * Created by ouweiming on 2017/3/31.
 */

@Table(name = "point")
public class Point {
    @Expose
    /**  标识测试数据x  */
    private int x;

    @Expose
    /**  标识测试数据y */
    private int y;

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "graph_id")
    /**  所属划图id  */
    private int graphId;

    @Column(name = "lottery_id")
    /**  所属期数的Lottery.id  */
    private int lotteryId;

    @Column(name = "differ_first")
    /**  距离第一个点的期数差  */
    private int differFirst;

    @Column(name = "differ_last")
    /**  距离上一个点的期数差  */
    private int differLast;

    @Column(name = "differ_Next")
    /**  距离上一个点的期数差  */
    private int differNext;

    @Column(name = "value")
    /**  数值  */
    private String value;

    @Column(name = "location")
    /**  位置 合数位  */
    private String location;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Lottery getLottery(){
        return LotteryDao.selectById(lotteryId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGraphId() {
        return graphId;
    }

    public void setGraphId(int graphId) {
        this.graphId = graphId;
    }

    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) {
        this.lotteryId = lotteryId;
    }

    public int getDifferFirst() {
        return differFirst;
    }

    public void setDifferFirst(int differFirst) {
        this.differFirst = differFirst;
    }

    public int getDifferLast() {
        return differLast;
    }

    public void setDifferLast(int differLast) {
        this.differLast = differLast;
    }

    public int getDifferNext() {
        return differNext;
    }

    public void setDifferNext(int differNext) {
        this.differNext = differNext;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return AppHolder.getGsonExpose().toJson(this);
        /*"{\"x\":" + x +
                " \"y\":" + y
                + "}";*/
    }
}
