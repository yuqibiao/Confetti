package com.yyyu.baselibrary.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

/**
 * <p>更加强壮的 BottomSheetDialog </p>
 *
 * <ul>
 *     <li>增加了设置显示高度跟最大高度的方法</li>
 *     <li>修复了通过手势关闭后无法再显示的问题</li>
 * </ul>
 *
 * @author xiaoGuy
 */

public class BottomSheetDialogWrapper extends BottomSheetDialog {

    private int mPeekHeight;
    private int mMaxHeight;
    private boolean mCreated;
    private Window mWindow;
    private BottomSheetBehavior mBottomSheetBehavior;

    public BottomSheetDialogWrapper(@NonNull Context context) {
        super(context);
        mWindow = getWindow();
    }

    // 便捷的构造器
    public BottomSheetDialogWrapper(@NonNull Context context, int peekHeight, int maxHeight) {
        this(context);

        mPeekHeight = peekHeight;
        mMaxHeight = maxHeight;
    }

    public BottomSheetDialogWrapper(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
        mWindow = getWindow();
    }

    public BottomSheetDialogWrapper(@NonNull Context context, boolean cancelable,
                                    OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCreated = true;

        setPeekHeight();
        setMaxHeight();
        setBottomSheetCallback();
    }

    public void setPeekHeight(int peekHeight) {
        mPeekHeight = peekHeight;

        if (mCreated) {
            setPeekHeight();
        }
    }

    public void setMaxHeight(int height) {
        mMaxHeight = height;

        if (mCreated) {
            setMaxHeight();
        }
    }

    public void setBatterSwipeDismiss(boolean enabled) {
        if (enabled) {

        }
    }

    private void setPeekHeight() {
        if (mPeekHeight <= 0) {
            return;
        }

        if (getBottomSheetBehavior() != null) {
            mBottomSheetBehavior.setPeekHeight(mPeekHeight);
        }
    }

    private void setMaxHeight() {
        if (mMaxHeight <= 0) {
            return;
        }

        mWindow.setLayout(LayoutParams.MATCH_PARENT, mMaxHeight);
        mWindow.setGravity(Gravity.BOTTOM);
    }

    private BottomSheetBehavior getBottomSheetBehavior() {
        if (mBottomSheetBehavior != null) {
            return mBottomSheetBehavior;
        }

        View view = mWindow.findViewById(android.support.design.R.id.design_bottom_sheet);
        // setContentView() 没有调用
        if (view == null) {
            return null;
        }
        mBottomSheetBehavior = BottomSheetBehavior.from(view);
        return mBottomSheetBehavior;
    }

    private void setBottomSheetCallback() {
        if (getBottomSheetBehavior() != null) {
            mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        }
    }

    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback
            = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet,
                                   @BottomSheetBehavior.State int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();      
                BottomSheetBehavior.from(bottomSheet).setState(                                                                                                                                                                                                      
                                    BottomSheetBehavior.STATE_COLLAPSED);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
}