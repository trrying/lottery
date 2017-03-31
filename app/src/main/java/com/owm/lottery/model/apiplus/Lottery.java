package com.owm.lottery.model.apiplus;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * http://f.apiplus.cn/qxc.json api接口基类
 * Created by ouweiming on 2017/3/30.
 */

@Table(name = "lottery")
public class Lottery {
    /**
     * expect : 2017035
     * opencode : 3,5,2,5,7,6,8
     * opentime : 2017-03-28 20:34:00
     * opentimestamp : 1490704440
     */
    @Column(name = "id", isId = true)
    private int id;
//  网络返回字段
    @Column(name = "expect")
    private String expect;

    @Column(name = "open_code")
    private String opencode;

    @Column(name = "open_time")
    private String opentime;

    @Column(name = "open_time_stamp")
    private long opentimestamp;

//  自定义字段，用于界面显示跟记录划图
    @Column(name = "sum")
    private String sum;

    @Column(name = "week")
    private String week;

    @Column(name = "date")
    private String date;

    @Column(name = "expectNumber")
    private String expectNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpencode() {
        return opencode;
    }

    public void setOpencode(String opencode) {
        this.opencode = opencode;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public long getOpentimestamp() {
        return opentimestamp;
    }

    public void setOpentimestamp(long opentimestamp) {
        this.opentimestamp = opentimestamp;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpectNumber() {
        return expectNumber;
    }

    public void setExpectNumber(String expectNumber) {
        this.expectNumber = expectNumber;
    }
}
