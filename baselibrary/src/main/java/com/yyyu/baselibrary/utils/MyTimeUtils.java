package com.yyyu.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能：时间日期相关的工具类
 *
 * Created by yyyu on 2016/7/29.
 */
public class MyTimeUtils {

    public static String formatDateDT(long date) {
        Date currentdate = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(currentdate);
    }


    /**
     * 补全十位数（针对时、分）
     * @param i
     * @return
     */
    public static String complDigit(int i){
        String reslut = i+"";
        if(i<10){
            reslut = "0"+reslut;
        }
        return reslut;
    }

    /**
     * 根据年月得到当前月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDayNum(int year, int month) {
        int day ;
        boolean isLeap;
        switch (year % 4) {
            case 0:
                isLeap = true;
                break;
            default:
                isLeap = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = isLeap ? 28 : 29;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }


    /**
     * 得到当前日
     * @return
     */
    public static int getCurerntDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到当前月
     * @return
     */
    public static int getCurerntMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 得到当前年
     * @return
     */
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

}
