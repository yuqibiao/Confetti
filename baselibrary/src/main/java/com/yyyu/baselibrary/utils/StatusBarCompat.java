package com.yyyu.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 *
 * 功能：status bar 适配
 *
 * Created by yyyu on 2016/9/14.
 */
public class StatusBarCompat {

    private static final int INVALID_VAL = -1;
    private static final int COLOR_DEFAULT = Color.parseColor("#ffffff");


    public static void compat(Activity activity, int statusColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//---6.0及以上
            Window window = activity.getWindow();
            /*消除5.0以上状态栏上的阴影 FLAG_TRANSLUCENT_NAVIGATION 是取消NavigationBar上的阴影*/
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            /*改变状态栏上icon的颜色（6.0上才生效）*/
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//---[5.0 , 6.0)
            Window window = activity.getWindow();
            /*消除5.0以上状态栏上的阴影 FLAG_TRANSLUCENT_NAVIGATION 是取消NavigationBar上的阴影*/
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {//---[4.4 , 5.0)
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }

    }

    public static void compat(Activity activity) {
        compat(activity, INVALID_VAL);
    }

    /**
     * 得到 status bar的高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}