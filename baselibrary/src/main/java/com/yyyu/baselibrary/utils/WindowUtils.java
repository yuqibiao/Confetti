package com.yyyu.baselibrary.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * 功能：窗口相关工具类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class WindowUtils {

    public static int[] getSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width , height};
    }

}
