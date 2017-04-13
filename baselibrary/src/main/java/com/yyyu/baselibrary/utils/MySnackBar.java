package com.yyyu.baselibrary.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yyyu.baselibrary.R;

/**
 * 功能：对SnackBar的封装
 *
 * @author yyyu
 * @date 2016/5/25
 */
public class MySnackBar {

    private static final int defMsgColor =0xFFFFFFFF;
    private static final int defActionColor =0xFFFFFFFF;
    private static final int defBgColor =0xFFF5A623;

    /**
     * 短消息 传入Activity
     * @param act
     * @param msg
     * @return
     */
    public static Snackbar showShortDef(Activity act , String msg){
        View rootView =((ViewGroup)act.findViewById(android.R.id.content)).getChildAt(0);
        return showShortDef(rootView ,msg);
    }

    /**
     * 显示默认颜色的短时间消息
     * @param target
     * @param msg
     */
    public static Snackbar showShortDef(View target , String msg){
        Snackbar snackbar = Snackbar.make(target , msg , Snackbar.LENGTH_SHORT);
        setSnackbarBg(snackbar,defBgColor);
        setSnackbarMessageTextColor(snackbar,defMsgColor);
        setSnackBarActionBtnColor(snackbar , defActionColor);
        snackbar.show();
        return snackbar;
    }

    /**
     * 显示长消息 传入Activity
     * @param act
     * @param msg
     * @return
     */
    public static Snackbar showLongDef(Activity act , String msg){
        View rootView =((ViewGroup)act.findViewById(android.R.id.content)).getChildAt(0);
        return showLongDef(rootView ,msg);
    }

    /**
     * 显示默认颜色的长时间消息
     * @param target
     * @param msg
     */
    public static Snackbar showLongDef(View target , String msg){
        Snackbar snackbar = Snackbar.make(target , msg , Snackbar.LENGTH_LONG);
        setSnackbarBg(snackbar,defBgColor);
        setSnackBarActionBtnColor(snackbar , defActionColor);
        setSnackbarMessageTextColor(snackbar,defMsgColor);
        snackbar.show();
        return snackbar;
    }



    /**
     * 设置SnackBar的字体颜色
     * @param snackbar
     * @param color
     */
    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(color);
    }

    /**
     * 设置Snackbar的背景
     * @param snackbar
     * @param color
     */
    public static void setSnackbarBg(Snackbar snackbar , int color){
        View view = snackbar.getView();
        view.setBackgroundColor(color);
    }

    public static void setSnackBarActionBtnColor(Snackbar snackbar , int color){
        View view = snackbar.getView();
        ((Button) view.findViewById(R.id.snackbar_action)).setTextColor(color);
    }

}
