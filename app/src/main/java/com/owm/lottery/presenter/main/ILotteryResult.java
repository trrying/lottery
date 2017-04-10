package com.owm.lottery.presenter.main;

/**
 * Lottery 结果展示
 * Created by ouweiming on 2017/4/7.
 */

public interface ILotteryResult {

    interface LotteryResultPresenter{
        /**  获取数据  */
        void getData();
    }

    interface LotteryResultView{
        /**  刷新界面  */
        void refreshUI();
    }

}
