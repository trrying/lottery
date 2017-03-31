package com.owm.lottery.model.apiplus;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 画图中的点
 * Created by ouweiming on 2017/3/31.
 */

@Table(name = "point")
public class Point {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "lottery_id")
    private int lotteryId;




}
