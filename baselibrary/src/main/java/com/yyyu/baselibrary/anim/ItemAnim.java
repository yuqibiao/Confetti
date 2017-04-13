package com.yyyu.baselibrary.anim;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.yyyu.baselibrary.R;


/**
 * 功能：Item的动画
 *
 * @author yyyu
 * @date 2016/5/22
 */
public class ItemAnim {

    private  int lastPos = -1;

    private Context ctx;

    private static ItemAnim instance;
    private  IAnim anim;

    private ItemAnim(Context ctx){
        this.ctx = ctx;
        anim = new BottomInAnim();
    }

    public static synchronized ItemAnim getInstance(Context ctx){
        while (instance ==null){
            instance = new ItemAnim(ctx);
        }
        return instance;
    }

    public void startAnimator(View view, int position) {
        if (position > lastPos) {
            view.startAnimation(anim.getAnimation(ctx));
        }
        lastPos = position;
    }

    public void setAnim(IAnim anim) {
        this.anim = anim;
    }
}
