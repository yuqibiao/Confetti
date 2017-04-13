package com.yyyu.confetti.view.activity;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yyyu.baselibrary.utils.MySPUtils;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.UserInfo;

import butterknife.BindView;

/**
 * 功能：启动页
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/1
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void afterInit() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anime);
        ivSplash.setAnimation(animation);
        animation.start();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSplash.measure(0 , 0 );
                MainActivity.startAction(SplashActivity.this,
                        ivSplash.getX() + ivSplash.getMeasuredWidth() / 2,
                        ivSplash.getY() + ivSplash.getMeasuredHeight() / 2,
                        ivSplash.getMeasuredWidth());
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
