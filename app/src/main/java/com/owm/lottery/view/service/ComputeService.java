package com.owm.lottery.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.apiplus.Point;
import com.owm.lottery.model.common.AppHolder;
import com.owm.lottery.model.db.dao.GraphDao;
import com.owm.lottery.model.db.dao.LotteryDao;
import com.owm.lottery.model.utils.O;
import com.owm.lottery.model.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 后台计算划图 服务
 * Created by ouweiming on 2017/4/1.
 */

public class ComputeService extends Service {

    private static List<Lottery> mLotteryList = new ArrayList<>();

    private static Thread computeThread;

    private static boolean isRunning = false;

    private static boolean isCompute = false;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public static boolean isCompute() {
        return isCompute;
    }

    public static void setIsCompute(boolean isCompute) {
        ComputeService.isCompute = isCompute;
        if (isCompute && !isRunning) {
            computeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    compute(O.getComputeInfo());
                }
            });
            computeThread.start();
        }
    }

    public static void initDate() {
        mLotteryList.clear();
        mLotteryList = LotteryDao.selectOrderByExpect();
        Collections.reverse(mLotteryList);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static void compute(int[] ints) {
        isRunning = true;
        if (mLotteryList.isEmpty()) {
            initDate();
            if (mLotteryList.isEmpty()) {
                isCompute = false;
            }
        }
        try {
            while (isCompute) {
                for (int expectSize = ints[0]; expectSize < 10 && isCompute; expectSize++) {
                    for (int xSize = ints[1]; xSize < 6 && isCompute; xSize++) {

                        Graph graph = new Graph();
                        //获取随机测试数据
                        List<Point> pointList = O.getComputeData(expectSize, xSize);
                        graph.setPoints(pointList);
                        //设置开始期数
                        graph.setStartExpect(O.subExpect(mLotteryList.get(0).getExpect()));
                        // 赋值计算点的坐标x,y
                        graph.setPointLocation(AppHolder.getGsonExpose().toJson(pointList));
                        int expectRange = O.getExpectRange(graph);
                        if (expectRange == 0) {
                            break;
                        }

                        //循环 将各个规律的不同间距 计算出来，
                        for (int i = ints[2], iSize = mLotteryList.size() / expectRange; i < iSize && isCompute; i++) {
                            List<Integer> sums = new ArrayList<>();
                            //循环 计算等间距 几个规律的 周期和的数组
                            for (int j = ints[3], jSize = mLotteryList.size() / (expectRange + i); j < jSize; j++) {
                                //循环 计算一个周期的 和
                                int sum = 0;
                                for (int k = 0; k < pointList.size(); k++) {
                                    if (j == 0) {
                                        break;
                                    }
                                    String openCode = mLotteryList.get(pointList.get(k).getX() + j).getOpencode();
                                    int y = pointList.get(k).getY();
                                    sum += (O.getNumberInt(openCode))[y];
                                }
                                sums.add(sum);
                            }

                            //计算等间距的一个规律的 成功率
                            HashMap<Integer, Integer> map = new HashMap<>();
                            //统计离散数据
                            for (int sum : sums) {
                                map.put(sum, map.containsKey(sum) ? map.get(sum) + 1 : 1);
                            }
                            map.remove(0);
                            float keySum = 0;
                            for (int key : map.keySet()) {
                                if (map.get(key) > keySum) {
                                    keySum = map.get(key);
                                }
                            }
                            Graph graphTemp = O.cloneObject(graph, Graph.class);
                            graphTemp.setId(0);
                            graphTemp.setPercentage(keySum / sums.size());
                            graphTemp.setGapExpect(i);
                            GraphDao.save(graphTemp);
                            //保存计算进度
                            SharedPreferencesUtil.putCompute(expectSize + "," + xSize + "," + i + "," + 0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

}
