package com.yyyu.baselibrary.anim;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yyyu.baselibrary.R;

/**
 * 功能：从底部渐渐进入动画
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/30
 */

public class AlphaInAnim implements  IAnim{

    @Override
    public Animation getAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.item_alpha_in);
    }
}
