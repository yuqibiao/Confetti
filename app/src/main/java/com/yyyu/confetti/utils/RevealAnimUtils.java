package com.yyyu.confetti.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

/**
 * 功能：Reveal动画封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class RevealAnimUtils {

    public static void animateRevealShow(final View view, final int centerX, final int centerY, final float startRadius, final float endRadius) {

        view.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator mAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
                    mAnimator.setDuration(500);
                    mAnimator.setInterpolator(new AccelerateInterpolator());
                    view.setVisibility(View.VISIBLE);
                    mAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            view.setVisibility(View.VISIBLE);
                            super.onAnimationStart(animation);
                        }
                    });
                    mAnimator.start();
                }
            }
        });
    }

    public static void animateRevealClose(final View view, final int centerX, final int centerY, final float startRadius, final float endRadius, final OnRevealCloseListener closeListener) {

        view.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator mAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
                    mAnimator.setDuration(500);
                    mAnimator.setInterpolator(new AccelerateInterpolator());
                    mAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            view.setVisibility(View.INVISIBLE);
                            if (closeListener != null) {
                                closeListener.close();
                            }
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                        }
                    });
                    mAnimator.start();
                }
            }
        });
    }

    public interface OnRevealCloseListener {
        void close();
    }


}
