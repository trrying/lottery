package com.owm.lottery.model.apiplus;

import org.xutils.db.annotation.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * 划图
 * Created by ouweiming on 2017/3/31.
 */

public class Graph {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "size")
    /**  点的数量  */
    private int pointSize;

    @Column(name = "sum")
    /**  总和  */
    private int sum;

    @Column(name = "gap_expect")
    /**  规律 期数间距  */
    private int gapExpect;

    @Column(name = "round_count")
    /**  循环次数  */
    private int roundCount;

    @Column(name = "percentage")
    /**  准确率  */
    private float percentage;

    @Column(name = "start_expect")
    /**  开始期数  */
    private String startExpect;

    @Column(name = "point_location")
    /**  图中点的gson  */
    private String pointLocation;

    /**  点 集合  */
    private List<Point> points = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getGapExpect() {
        return gapExpect;
    }

    public void setGapExpect(int gapExpect) {
        this.gapExpect = gapExpect;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getStartExpect() {
        return startExpect;
    }

    public void setStartExpect(String startExpect) {
        this.startExpect = startExpect;
    }

    public String getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(String pointLocation) {
        this.pointLocation = pointLocation;
    }

    public List<Point> getPoints() {
//        points.clear();
//        points.addAll(PointDao.selectByGraph(id));
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
