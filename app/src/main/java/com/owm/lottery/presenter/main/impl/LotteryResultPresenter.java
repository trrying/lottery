package com.owm.lottery.presenter.main.impl;

import com.google.gson.Gson;
import com.owm.lottery.model.apiplus.Result;
import com.owm.lottery.model.db.dao.LotteryDao;
import com.owm.lottery.model.utils.O;
import com.owm.lottery.presenter.main.ILotteryResult;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * lottery 结果控制器
 * Created by ouweiming on 2017/4/7.
 */

public class LotteryResultPresenter implements ILotteryResult.LotteryResultPresenter{

    private ILotteryResult.LotteryResultView mView;

    public LotteryResultPresenter(ILotteryResult.LotteryResultView view) {
        mView = view;
    }

    @Override
    public void getData() {
        Observable.just("http://f.apiplus.cn/qxc-50.json")
                .map(new Function<String, Result>() {
                    @Override
                    public Result apply(String urlStr) throws Exception {
                        HttpURLConnection connection;
                        Result result = null;
                        try {
                            URL url = new URL(urlStr);
                            connection = (HttpURLConnection) url.openConnection();
                            // 设置请求方式
                            connection.setRequestMethod("POST");
                            // 设置编码格式
                            connection.setRequestProperty("Charset", "UTF-8");
                            // 设置超时
                            connection.setConnectTimeout(10 * 1000);

                            if (connection.getResponseCode() == 200) {
                                InputStream is = connection.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024 * 8];
                                int size;
                                while ((size = is.read(buffer)) >= 1) {
                                    baos.write(buffer, 0 , size);
                                }
                                final String resultStr = new String(baos.toByteArray());
                                Gson gson = new Gson();
                                System.out.println("resultStr-->"+resultStr);
                                result = gson.fromJson(resultStr, Result.class);
                                O.setDateValue(result.getData());
                                LotteryDao.save(result.getData());
                                Collections.reverse(result.getData());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return result != null ? result : new Result();
                    }
                })
                .filter(new Predicate<Result>() {
                    @Override
                    public boolean test(Result result) throws Exception {
                        return result != null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result value) {
                        mView.refreshUI();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
