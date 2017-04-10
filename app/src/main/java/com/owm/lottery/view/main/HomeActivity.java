package com.owm.lottery.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.owm.lottery.R;
import com.owm.lottery.view.adapter.commit.ViewPagerAdapter;
import com.owm.lottery.view.common.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;


/**
 * 主界面
 * Created by ouweiming on 2017/4/7.
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @ViewInject(R.id.bottom_navigation)
    private BottomNavigationView bottom_navigation;

    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;

    private ViewPagerAdapter mPagerAdapter;
    private Fragment[] mFragments = new Fragment[]{new LotteryResultFragment(),
            new LotteryResultFragment(), new LotteryResultFragment()};
    private int[] itemId = new int[]{R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewpager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        viewpager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        viewpager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Arrays.asList(mFragments));
        viewpager.setAdapter(mPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottom_navigation.setSelectedItemId(itemId[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setCurrentItem(0);
    }
}