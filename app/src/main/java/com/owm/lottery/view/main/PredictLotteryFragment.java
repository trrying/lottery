package com.owm.lottery.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.owm.lottery.R;
import com.owm.lottery.view.common.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 预测Lottery页面
 * Created by ouweiming on 2017/4/10.
 */

@ContentView(R.layout.fragment_predict)
public class PredictLotteryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.sw_refresh)
    private SwipeRefreshLayout sw_refresh;
    @ViewInject(R.id.rv_content)
    private RecyclerView rv_content;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();
        initDate();
    }

    private void initView() {
        sw_refresh.setEnabled(true);
    }

    private void initEvent() {
        sw_refresh.setOnRefreshListener(this);
    }

    private void initDate() {

    }


    @Override
    public void onRefresh() {

    }
}
