package com.owm.lottery.model.utils;

import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
     * 期数是否是4的倍数
     * @param data 原数据
     * @return 是:true
     */
    public static boolean isFour(String data) {
        boolean result = false;
        String expect = subExpect(data);
        if (!isEmpty(expect) && isNumeric(expect)) {
            result = Integer.valueOf(expect) % 4 == 0;
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
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
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
        if (!isEmpty(data) && (split = data.split(",")).length > 0) {
            int sum = 0;
            for (String num : split) {
                if (isNumeric(num)) {
                    sum += Integer.valueOf(num);
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
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0 || str.equals("");
    }

}
