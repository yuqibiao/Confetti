package com.yyyu.baselibrary.anim.behavior;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import static android.content.ContentValues.TAG;
import static android.support.v4.view.ViewCompat.animate;

/**
 * 功能：自定义Behavior 实现FAB的动态隐藏、显示
 * <p>
 * Created by yyyu on 2017/2/22.
 */

public class MyFABBehavior extends FloatingActionButton.Behavior {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    public MyFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return ViewCompat.SCROLL_AXIS_VERTICAL == nestedScrollAxes;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 &&child.getVisibility() == View.VISIBLE) {
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() == View.INVISIBLE) {
           animateIn(child);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButton button) {
        if (Build.VERSION.SDK_INT >= 14) {
            animate(button).translationY(button.getHeight() + getMarginBottom(button)).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(new ViewPropertyAnimatorListener() {
                        public void onAnimationStart(View view) {
                        }
                        public void onAnimationCancel(View view) {
                        }
                        public void onAnimationEnd(View view) {
                            view.setVisibility(View.INVISIBLE);
                        }
                    }).start();
        } else {

        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    private void animateIn(final FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 14) {
            animate(button).translationY(0)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                        }
                        @Override
                        public void onAnimationEnd(View view) {
                        }
                        @Override
                        public void onAnimationCancel(View view) {
                            view.setVisibility(View.VISIBLE);
                        }
                    })
                    .start();
        } else {

        }
    }

    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }

}
