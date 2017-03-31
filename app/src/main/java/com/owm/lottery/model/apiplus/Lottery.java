package com.owm.lottery.model.apiplus;

/**
 * http://f.apiplus.cn/qxc.json api接口基类
 * Created by ouweiming on 2017/3/30.
 */

public class Lottery {
    /**
     * expect : 2017035
     * opencode : 3,5,2,5,7,6,8
     * opentime : 2017-03-28 20:34:00
     * opentimestamp : 1490704440
     */

    private String expect;
    private String opencode;
    private String opentime;
    private long opentimestamp;

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
}
