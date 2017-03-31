package com.owm.lottery.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具
 * Created by ouweiming on 2017/3/31.
 */

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long ONE_DAY_MILLIS = 1000 * 60 * 60 * 24;

    /**
     * 根据日期，获取星期
     * @param date 数据源
     * @return 处理数据
     */
    public static int getWeekValue(String date) {
        int result = -1;
        if (O.isEmpty(date)) {
            return result;
        }
        try {
            Calendar calendar = getCalendar(date);
            result = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据 时间字符串 获取Calendar, 默认返回默认的Calendar
     * @param date 数据源
     * @return 处理数据
     */
    public static Calendar getCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = getSimpleDateFormat();
            calendar.setTime(sdf.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long getTime(String date) {
        long result =  -1;
        if (O.isEmpty(date)) {
            return result;
        }
        try {
            result = getSimpleDateFormat().parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
    }

    public static String getDateFormatString (long timeMillis) {
        return getSimpleDateFormat().format(new Date(timeMillis));
    }

    /**
     * 获取当前年份
     * @return 年份：2017
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

}
