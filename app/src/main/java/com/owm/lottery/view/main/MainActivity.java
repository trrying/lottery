package com.owm.lottery.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.owm.lottery.R;
import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.db.dao.LotteryDao;
import com.owm.lottery.model.apiplus.Result;
import com.owm.lottery.model.utils.O;
import com.owm.lottery.view.service.ComputeService;
import com.owm.lottery.presenter.main.IMain;
import com.owm.lottery.view.adapter.main.LotteryAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMain.MainView{

    private IMain.MainPresenter mPresenter;

    private RecyclerView rv_content;
    private LotteryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                getData();
                startService(new Intent(MainActivity.this, ComputeService.class));
            }
        });

        rv_content = (RecyclerView) findViewById(R.id.rv_content);

        initDate();
    }

    private void initDate() {
        setAdapter(LotteryDao.selectOrderByExpect());
    }

    private void getData() {
//        getDateXUtils();
        getDate4HttpURLConnection();
    }

    private void setAdapter(List<Lottery> data) {
        if (mAdapter == null || data != mAdapter.getData()) {
            rv_content.setLayoutManager(new LinearLayoutManager(this));
            rv_content.setAdapter(new LotteryAdapter(data));
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void getDateXUtils() {
        //http://f.apiplus.cn/qxc-50.json
        RequestParams requestParams = new RequestParams("http://f.apiplus.cn/qxc-50.json");
        x.http().get(requestParams, new Callback.CacheCallback<Result>() {
            @Override
            public boolean onCache(Result result) {
                return false;
            }

            @Override
            public void onSuccess(Result result) {
                setAdapter(result.getData());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void getDate4HttpURLConnection() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //http://f.apiplus.cn/qxc-50.json
                HttpURLConnection connection;

                try {
                    URL url = new URL("http://f.apiplus.cn/qxc-50.json");
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
                        final Result result = gson.fromJson(resultStr, Result.class);
                        O.setDateValue(result.getData());
                        LotteryDao.save(result.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Collections.reverse(result.getData());
                                O.appendLottery(result.getData());
                                setAdapter(result.getData());
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //1、随机生成几个点，连线，计算值，往上推>5的周期，准确率大于80的记录
    private void forecast() {
        List<Lottery> lotteries = LotteryDao.selectOrderByExpect();

        for (int i = 0; i < 10; i++) {
            Graph graph = new Graph();

        }
    }

}
