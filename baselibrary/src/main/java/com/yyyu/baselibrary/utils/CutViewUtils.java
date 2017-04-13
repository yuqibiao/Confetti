package com.yyyu.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.ViewGroup;

/**
 * 功能：截图工具类
 *
 * @author yyyu
 * @date 2016/6/4
 */
public class CutViewUtils {

    /**
     * 截取控件内容为图片
     * @return
     */
    public static Bitmap getBitmapByView(ViewGroup parentView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < parentView.getChildCount(); i++) {
            h += parentView.getChildAt(i).getHeight();
            parentView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(parentView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        parentView.draw(canvas);
        return bitmap;
    }

}
