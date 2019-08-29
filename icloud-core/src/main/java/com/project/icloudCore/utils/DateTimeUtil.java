package com.project.icloudCore.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化工具类
 */
public class DateTimeUtil {

    /**
    * @description: TODO 格式 yyyy年MM月dd日 HH:mm:ss
    * @param dateTime 时间
    * @param formatter 格式
    * @return java.lang.String
    */
    public static String getDateTimeDisplayString(Date dateTime, String formatter) {
//        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat dtf2 = new SimpleDateFormat(formatter);
        String strDate2 = dtf2.format(dateTime);

        return strDate2;
    }

    /**
    * @description: TODO 字符串转日期
    * @param dateT 时间字符串
    * @param formatter 格式
    * @return java.util.Date
    */
    public static Date getStringToDate(String dateT, String formatter) {
        SimpleDateFormat dtf2 = new SimpleDateFormat(formatter);
        Date strDate2 = null;
        try {
            strDate2 = dtf2.parse(dateT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate2;
    }

    /**
    * @description: TODO Date和Time信息拼装
    * @param date 日期
    * @param time 时间
    * @return java.util.Date
    */
    public static Date getAssemableTime(Date date, Time time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastDate = dateFormat.format(date) + " " + time;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date completeTime = null;
        try {
            completeTime = format.parse(lastDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return completeTime;
    }

    /**
     * @Author wxy
     * @Description //TODO
     * @Date 14:51 2019/2/13日期格式化
     **/
    public static SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
    * @description: TODO 给时间加上几个小时
    * @param day 日期
    * @param hour 小时
    * @return java.lang.String
    */
    public static String addDateHour(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour); // 24小时制
        date = cal.getTime();
        return format.format(date);

    }

    /**
    * @description: TODO 给时间加上几个小时
    * @param day 日期
    * @param hour 小时
    * @return java.util.Date
    */
    public static Date addDateHours(String day, int hour) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour); // 24小时制
        date = cal.getTime();
        return date;

    }

    /**
    * @description: TODO 获取指定时间的后几天
    * @param time 时间
    * @param days 天数
    * @return java.util.Date
    */
    public static Date addDate(Date time, int days) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        ca.add(Calendar.DAY_OF_MONTH, days);

        return ca.getTime();
    }

    /**
    * @description: TODO 给时间加上几个月
    * @param day 时间
    * @param month 月数
    * @return java.util.Date
    */
    public static String addDateMonth(String day, int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month); 
        date = cal.getTime();
        return format.format(date);
    }

}
