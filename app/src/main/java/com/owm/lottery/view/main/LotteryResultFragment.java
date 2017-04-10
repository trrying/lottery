package com.owm.lottery.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.owm.lottery.R;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.db.dao.LotteryDao;
import com.owm.lottery.model.utils.O;
import com.owm.lottery.presenter.main.ILotteryResult;
import com.owm.lottery.presenter.main.impl.LotteryResultPresenter;
import com.owm.lottery.view.adapter.main.LotteryAdapter;
import com.owm.lottery.view.common.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 首页第一个fragment
 * Created by ouweiming on 2017/4/7.
 */
@ContentView(R.layout.fragment_lottery_result)
public class LotteryResultFragment extends BaseFragment implements ILotteryResult.LotteryResultView, SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.sw_refresh)
    private SwipeRefreshLayout sw_refresh;
    @ViewInject(R.id.rv_content)
    private RecyclerView rv_content;

    private LotteryAdapter mAdapter;

    private ILotteryResult.LotteryResultPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();
        initDate();
    }

    private void initView() {
        sw_refresh.setEnabled(true);

        mPresenter = new LotteryResultPresenter(this);
    }

    private void initEvent() {
        sw_refresh.setOnRefreshListener(this);
    }

    private void initDate() {
        refreshUI();
//        mPresenter.getData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void refreshUI() {
        setAdapter(O.appendLottery(LotteryDao.selectOrderByExpect()));
        sw_refresh.setRefreshing(false);
    }

    private void setAdapter(List<Lottery> data) {
        if (getActivity() == null) {
            return;
        }
        if (mAdapter == null || data != mAdapter.getData()) {
            mAdapter = new LotteryAdapter(data);
            rv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_content.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }
}
