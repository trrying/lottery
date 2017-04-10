package com.owm.lottery.view.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.owm.lottery.R;
import com.owm.lottery.model.db.dao.GraphDao;
import com.owm.lottery.view.common.BaseFragment;
import com.owm.lottery.view.service.ComputeService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 设置页面
 * Created by ouweiming on 2017/4/10.
 */

@ContentView(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {

    @ViewInject(R.id.tb_switch)
    private ToggleButton tb_switch;

    @ViewInject(R.id.tv_result)
    private TextView tv_result;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (getUserVisibleHint()) {
                refreshUI();
            }
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessageDelayed(1, 100);
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initEvent();
        initDate();
    }

    private void initView() {

    }

    private void initEvent() {
        tb_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ComputeService.setIsCompute(isChecked);
            }
        });
    }

    private void initDate() {
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }

    private void refreshUI() {
        tb_switch.setChecked(ComputeService.isCompute());

        Observable.just("")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        StringBuilder result = new StringBuilder();
                        result.append("汇总").append("\n")
                                .append("总条数：" + GraphDao.selectCount() + " 条").append("\n")
                                .append("准确率70%~80% : " + GraphDao.selectCountByPercentage(0.7f, 0.8f)).append("\n")
                                .append("准确率80%~90% : " + GraphDao.selectCountByPercentage(0.8f, 0.9f)).append("\n")
                                .append("准确率90%~100% : " + GraphDao.selectCountByPercentage(0.8f, 1.0f)).append("\n")
                                .append("准确率100% : " + GraphDao.selectCountByPercentage(1.0f)).append("\n");
                        return result.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        tv_result.setText(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && tb_switch != null) {
            initDate();
        }
    }
}
