package com.owm.lottery.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.apiplus.Point;
import com.owm.lottery.model.common.AppHolder;
import com.owm.lottery.model.db.dao.LotteryDao;
import com.owm.lottery.model.utils.O;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 后台计算划图 服务
 * Created by ouweiming on 2017/4/1.
 */

public class ComputeService extends Service {

    private List<Lottery> mLotteryList;

    Thread computeThread;

    private boolean isCompute = true;

    @Override
    public void onCreate() {
        super.onCreate();

        mLotteryList = LotteryDao.selectOrderByExpect();
        Collections.reverse(mLotteryList);

        if (computeThread == null) {
            computeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    compute();
                }
            });
            computeThread.start();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void compute() {
        while (isCompute) {
            Graph graph = new Graph();
            List<Point> pointList = O.getComputeData(5, 3);
            graph.setPoints(pointList);
            graph.setStartExpect(O.subExpect(mLotteryList.get(0).getExpect()));
            graph.setPointLocation(AppHolder.getGsonExpose().toJson(pointList));
            int expectRange = O.getExpectRange(graph);
            if (expectRange == 0) {
                break;
            }

            //循环 将各个规律的不同间距 计算出来，
            for (int i = 1, iSize = mLotteryList.size() / expectRange; i < iSize; i++) {
                int[] sums = new int[pointList.size()];
                //循环 计算等间距 几个规律的 周期和的数组
                for (int j = 0, jSize = mLotteryList.size() / (expectRange + i); j < jSize; j++) {
                    //循环 计算一个周期的 和
                    for (int k = 0; k < pointList.size(); k++) {
                        if (j == 0) {
                            break;
                        }
                        sums[j] += (O.getNumberInt(mLotteryList.get(pointList.get(k).getX()).getOpencode()))[pointList.get(k).getY()];
                    }
                }

                //计算等间距的一个规律的 成功率
                HashMap<Integer, Integer> map = new HashMap<>();
                for (int sum : sums) {
                    if (map.containsKey(sum)) {
                        map.put(sum, map.get(sum) + 1);
                    }
                }

            }
        }
    }

}
