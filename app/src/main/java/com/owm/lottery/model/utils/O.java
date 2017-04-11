package com.owm.lottery.model.utils;

import android.support.annotation.Nullable;

import com.owm.lottery.model.apiplus.Graph;
import com.owm.lottery.model.apiplus.Lottery;
import com.owm.lottery.model.apiplus.Point;
import com.owm.lottery.model.common.AppHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Other utils
 * Created by ouweiming on 2017/3/30.
 */

public class O {
    
    public static void main(String[] args) {
        System.out.println(subDate("2017-03-21 20:34:00"));
        System.out.println(getWeek("2017-03-30 20:34:00"));
        System.out.println(subExpect("2017032"));
        System.out.println(getSum("5,1,1,8,3,2,0"));
        for (int i = 0; i < 20; i++) {
            System.out.println(AppHolder.getGsonExpose().toJson(getComputeData(5, 3)));
        }
    }
    
    /**
     * 截取日期  2017-03-21 20:34:00 --> 3月21日
     * @param date 原数据
     * @return 处理数据
     */
    public static String subDate(String date){
        String result = "";
        String[] split;
        if (!isEmpty(date) && (split = date.split(" ")).length == 2 && (split = split[0].split("-")).length == 3) {
            result = Integer.valueOf(split[1]) + "月" + split[2] + "日";
        }
        return result;
    }

    /**
     * 截取期数 2017032 --> 032
     * @param expect 原数据
     * @return 处理数据
     */
    public static String subExpect(String expect) {
        String result = "";
        if (!isEmpty(expect) && expect.length() > 4) {
            result = expect.substring(4, expect.length());
        }
        return result;
    }

    /**
     * 期数是否是4的倍数余1
     * @param data 原数据
     * @return 是:true
     */
    public static boolean isFour(String data) {
        boolean result = false;
        String expect = subExpect(data);
        if (!isEmpty(expect) && isNumeric(expect)) {
            result = Integer.valueOf(expect) % 4 == 1;
        }
        return result;
    }

    /**
     * 根据日期，获取星期
     * @param date 数据源
     * @return 处理数据
     */
    public static String getWeek(String date) {
        String result = "星期";
        int dayOfWeek = DateUtils.getWeekValue(date);
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                result += "日";
                break;
            case Calendar.MONDAY:
                result += "一";
                break;
            case Calendar.TUESDAY:
                result += "二";
                break;
            case Calendar.WEDNESDAY:
                result += "三";
                break;
            case Calendar.THURSDAY:
                result += "四";
                break;
            case Calendar.FRIDAY:
                result += "五";
                break;
            case Calendar.SATURDAY:
                result += "六";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * 获取合数
     * @param data 原数据
     * @return 合数
     */
    public static String getSum(String data) {
        String result = "";
        String[] split;
        if (!isEmpty(data) && (split = data.split(",")).length > 4) {
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                if (isNumeric(split[i])) {
                    sum += Integer.valueOf(split[i]);
                }
            }
            result = String.valueOf(sum);
        }
        return result;
    }

    /**
     * 解析码数
     * @param data 原数据
     * @return 码数
     */
    public static String[] getNumber(String data) {
        if (!isEmpty(data)){
            return data.split(",");
        }
        return new String[]{};
    }

    /**
     * 解析码数
     * @param data 原数据
     * @return 码数
     */
    public static int[] getNumberInt(String data) {
        int[] results = null;
        if (!isEmpty(data)){
            String[] strings = data.split(",");
            results = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                if (isNumeric(strings[i])) {
                    results[i] = Integer.valueOf(strings[i]);
                }
            }
        }
        return results;
    }

    /**
     * 判断是否全部都是纯数字
     * @param str 原数据
     * @return 是:true
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        return isNum.matches();
    }

    /**
     * 判断是否全部都是纯数字
     * @param strs 原数据
     * @return 全部是:true
     */
    private static boolean isNumeric(String[] strs) {
        boolean result = true;
        Pattern pattern = Pattern.compile("[0-9]*");
        for (String str : strs) {
            if (!pattern.matcher(str).matches()) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 追加 Lottery，默认在末尾追加4个
     * @param data 原数据
     */
    public static List<Lottery> appendLottery(List<Lottery> data) {
        return appendLottery(data, data != null ? data.size() - 1 : 0, 4);
    }

    /**
     * 追加 Lottery
     * @param data 原数据
     * @param startPosition 追加开始下标
     * @param count 追加数量
     */
    public static List<Lottery> appendLottery(List<Lottery> data, int startPosition, int count) {
        if (data == null) {
            data = new ArrayList<>();
        }
        if (data.isEmpty()) {
            Lottery lottery = new Lottery();

            lottery.setOpentimestamp(System.currentTimeMillis() + (getNextOpenDay() * DateUtils.ONE_DAY_MILLIS));
            lottery.setOpentime(DateUtils.getDateFormatString(lottery.getOpentimestamp()));
            lottery.setExpect(String.format(Locale.CHINA, DateUtils.getCurrentYear() + "%03d", 1));

            data.add(lottery);

            count--;
        }
        if (startPosition < 0 || startPosition >= data.size()) {
            startPosition = data.size() - 1;
        }
        for (int i = startPosition, size = startPosition + count; i < size; i++) {
            long timeMillis = data.get(i).getOpentimestamp();
            timeMillis += DateUtils.ONE_DAY_MILLIS * (DateUtils.getWeekValue(data.get(i).getOpentime()) == Calendar.TUESDAY ? 3 : 2);

            Lottery lottery = new Lottery();
            lottery.setOpentime(DateUtils.getDateFormatString(timeMillis));
            lottery.setExpect(String.format(Locale.CHINA, DateUtils.getCurrentYear() + "%03d", Integer.valueOf(subExpect(data.get(i).getExpect())) + 1));

            data.add(lottery);
        }
        return data;
    }

    /**
     * 获取距离下次开奖天数
     * @return 间隔天数
     */
    public static int getNextOpenDay() {
        int result = 0;
        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 0;
                break;
            case Calendar.WEDNESDAY:
                result = 2;
                break;
            case Calendar.THURSDAY:
                result = 1;
                break;
            case Calendar.FRIDAY:
                result = 0;
                break;
            case Calendar.SATURDAY:
                result = 1;
                break;
        }
        return result;
    }

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0 || str.equals("");
    }

    /**
     * 设置value值
     * @param data 原数据
     */
    public static void setDateValue(List<Lottery> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        for (Lottery lottery : data) {
            lottery.setDate(O.subDate(lottery.getOpentime()));
            lottery.setExpectNumber(O.subExpect(lottery.getExpect()));
            lottery.setWeek(O.getWeek(lottery.getOpentime()));
            lottery.setSum(O.getSum(lottery.getOpencode()));
        }
    }

    /**
     * 获取随机测试数据
     * @param expectSize 期数范围
     * @param xSize 多少个点
     * @return 测试数据
     */
    public static List<Point> getComputeData(int expectSize, int xSize) {
        return getComputeData(expectSize, xSize, 7);
    }

    /**
     * 获取随机测试数据
     * @param expectSize 期数范围
     * @param xSize 多少个点
     * @param ySize 点的取值范围
     * @return 测试数据
     */
    public static List<Point> getComputeData(int expectSize, int xSize, int ySize) {
        List<Point> result = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < xSize; i++) {
            boolean isExist = false;
            //获取随机坐标
            int x = i == 0 ? 0 : random.nextInt(expectSize);
            int y = random.nextInt(i == 0 ? 4 : ySize);
            //判断坐标是否出现过
            if (!result.isEmpty()) {
                for (int j = result.size() - 1; j < result.size(); j++) {
                    isExist = result.get(j).getX() == x && result.get(j).getY() == y;
                    if (isExist) {
                        break;
                    }
                }
            }
            //保存或者重新获取
            if (!isExist) {
                Point point = new Point();
                point.setX(x);
                point.setY(y);
                result.add(point);
            } else {
                i--;
            }
        }
        return result;
    }

    /**
     * 获取不重复随机数
     * @param total 随机数范围 0~total
     * @param count 随机数个数 count < total
     * @return 随机数
     */
    public static Integer[] GetRandomSequence1(int total, int count){
        List<Integer> input = new ArrayList<>();
        if (count > total) {
            total = count;
        }
        for (int i = 0; i < total; i++){
            input.add(i);
        }

        List<Integer> output = new ArrayList<>();

        Random random = new Random();
        int end = total;
        for (int i = 0; i < count; i++){
            int num = random.nextInt(end);
            output.add(input.get(num));
            input.remove(i);
            end--;
        }
        Integer[] result = new Integer[output.size()];
        output.toArray(result);
        return result;
    }

    /**
     * 获取一个划图的步宽
     * @param graph 划图
     * @return 步宽
     */
    public static int getExpectRange(Graph graph) {
        int result = 0;
        if (graph == null ||graph.getPoints() == null || graph.getPoints().isEmpty()) {
            return result;
        }
        for (int i = 0; i < graph.getPoints().size(); i++) {
            result = Math.max(result, graph.getPoints().get(i).getX());
        }
        return result;
    }

    public static int[] getComputeInfo() {
        String compute = SharedPreferencesUtil.getCompute();
        String[] split;
        if (O.isEmpty(compute) && (split = compute.split(",")).length == 4 && isNumeric(split)) {
            int[] ints = new int[split.length];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = Integer.valueOf(split[i]);
            }
            return ints;
        } else {
            return new int[]{2,2,1,0};
        }
    }

    /**
     * 克隆一个对象
     * @param obj 数据源
     * @param classType 数据类型
     * @param <T> 数据类型
     * @return 新的对象
     */
    public static <T> T cloneObject(Object obj,Class<T> classType) {
        return AppHolder.getGson().fromJson(AppHolder.getGson().toJson(obj), classType);
    }

}
