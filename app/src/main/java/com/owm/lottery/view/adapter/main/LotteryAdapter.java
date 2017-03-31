package com.owm.lottery.view.adapter.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.owm.lottery.R;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.utils.O;

import java.util.List;

/**
 * lottery 适配器
 * Created by ouweiming on 2017/3/30.
 */

public class LotteryAdapter extends BaseQuickAdapter<Lottery, BaseViewHolder>{

    private int[] numberIds = new int[]{R.id.tv_number1, R.id.tv_number2, R.id.tv_number3,
            R.id.tv_number4, R.id.tv_number5, R.id.tv_number6, R.id.tv_number7};

    public LotteryAdapter(List<Lottery> data) {
        super(R.layout.item_lottery, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Lottery item) {
        helper.setText(R.id.tv_date, item.getDate());
        helper.setText(R.id.tv_expect, item.getExpectNumber());
        helper.setText(R.id.tv_week, item.getWeek());
        helper.setText(R.id.tv_sum, item.getSum());
        String[] numbers = O.getNumber(item.getOpencode());
        for (int i = 0, size = numberIds.length/*Math.min(numbers.length, numberIds.length)*/; i < size; i++) {
            helper.setText(numberIds[i], i < numbers.length ? numbers[i] : "");
        }

        helper.setVisible(R.id.view_line_top, O.isFour(item.getExpect()));
        helper.setVisible(R.id.view_line_gap_top, !O.isFour(item.getExpect()));

        helper.setVisible(R.id.view_line_bottom, helper.getAdapterPosition() == getData().size() - 1 && O.isFour(item.getExpect()));
        helper.setVisible(R.id.view_line_gap_bottom, helper.getAdapterPosition() == getData().size() - 1 && !O.isFour(item.getExpect()));
    }
}
