package com.owm.lottery.model.apiplus;

import java.util.List;

/**
 * http://f.apiplus.cn/qxc.json api接口基类
 * Created by ouweiming on 2017/3/30.
 */

public class Result {

    /**
     * rows : 5
     * code : qxc
     * info : 免费接口随机延迟3-6分钟，实时接口请访问opencai.net或QQ:23081452(注明彩票或API)
     * data : [{"expect":"2017035","opencode":"3,5,2,5,7,6,8","opentime":"2017-03-28 20:34:00","opentimestamp":1490704440}]
     */

    private int rows;
    private String code;
    private String info;
    private List<Lottery> data;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Lottery> getData() {
        return data;
    }

    public void setData(List<Lottery> data) {
        this.data = data;
    }

}
