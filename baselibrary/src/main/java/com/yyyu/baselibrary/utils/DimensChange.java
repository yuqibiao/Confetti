package com.yyyu.baselibrary.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 功能：尺寸转换工具类
 *
 * @author yyyu
 * @date 2016/5/22
 */
public class DimensChange {

    private  DimensChange(){
        throw new UnsupportedOperationException("该类不能被实例化");
    }

    /**
     * dp转px
     */
    public static int dp2px (Context ctx , float dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dpValue,
                ctx.getResources().getDisplayMetrics());
    }
    /**
     * px转dp
     */
    public static float px2dp(Context ctx , int pxValue){
        float  scale = ctx.getResources().getDisplayMetrics().density;
        return pxValue/scale;
    }

}
