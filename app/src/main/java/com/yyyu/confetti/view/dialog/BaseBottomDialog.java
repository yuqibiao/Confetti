package com.yyyu.confetti.view.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.baselibrary.view.BottomSheetDialogWrapper;
import com.yyyu.confetti.adapter.CommentAdapter;

/**
 * 功能：评论展示Dialog的基类
 * <p>
 * 由于有的需要下拉加载有的不需要所以分开处理了
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public abstract class BaseBottomDialog {

    protected Context mContext;
    protected final BottomSheetDialogWrapper bottomSheetDialog;
    private CommentAdapter adapter;

    protected String comId;

    protected final View bottomView;

    public BaseBottomDialog(Context context, int peekHeight, int maxHeight , String comId) {
        this.mContext = context;
        bottomSheetDialog = new BottomSheetDialogWrapper(mContext, peekHeight, maxHeight);
        bottomView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WindowUtils.getSize(mContext)[0], maxHeight);
        bottomView.setLayoutParams(lp);
        bottomSheetDialog.setContentView(bottomView);
        this.comId = comId;

        initView();
        initListener();
        initData();
    }

    protected abstract void initView();

    protected void initListener() {

    }

    protected void initData() {

    }


    public void show() {
        bottomSheetDialog.show();
    }


    public abstract int getLayoutId();

    public View getBottomView() {
        return bottomView;
    }
}
