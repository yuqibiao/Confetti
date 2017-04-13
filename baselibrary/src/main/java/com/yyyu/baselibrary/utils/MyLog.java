package com.yyyu.baselibrary.utils;

import android.util.Log;

/**
 * 功能：打印Log工具类
 *
 * @author yyyu
 * @date 2016/5/19
 */
public class MyLog {

    private static boolean isDebug = true;
    private static final String TAG = "测试Log============";

    private MyLog() {
        throw new UnsupportedOperationException("不能被初始化");
    }

    //------------------下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    //------------ 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

}
