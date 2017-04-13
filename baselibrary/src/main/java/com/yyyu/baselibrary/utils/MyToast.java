package com.yyyu.baselibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 功能：Toast工具类
 *
 * @author yyyu
 * @date 2016/5/19
 */
public class MyToast {

    private static boolean isShow = true;

    private MyToast() {
        throw new UnsupportedOperationException("MyToast不能被初始化");
    }

    /**
     * 短时间显示Toast
     *
     * @param ctx
     * @param msg
     */
    public static void showShort(Context ctx, String msg) {
        if (isShow) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短时间显示资源文件里内容的Toats
     * @param ctx
     * @param resId
     */
    public static void showShort(Context ctx, int resId) {
        if (isShow)
            Toast.makeText(ctx, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param ctx
     * @param msg
     */
    public static void showLong(Context ctx, String msg) {
        if (isShow) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 长时间显示资源文件里内容的Toats
     * @param ctx
     * @param resId
     */
    public static void showLong(Context ctx, int resId) {
        if (isShow)
            Toast.makeText(ctx, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示自定义时间的Toast
     * @param ctx
     * @param msg
     * @param duration
     */
    public static void show(Context ctx , String msg , int duration){
        if(isShow){
            Toast.makeText(ctx ,msg , duration);
        }
    }

    /**
     * 显示资源文件中内容的自定义事件Toast
     * @param ctx
     * @param resId
     * @param duration
     */
    public static void show(Context ctx , int resId , int duration){
        if(isShow){
            Toast.makeText(ctx ,resId , duration);
        }
    }


}
