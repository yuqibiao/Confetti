package com.yyyu.baselibrary.anim;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yyyu.baselibrary.R;

/**
 * 功能：缩放Item进入
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/30
 */

public class ScaleInAnim implements  IAnim{
    @Override
    public Animation getAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.item_scale_in);
    }
}
