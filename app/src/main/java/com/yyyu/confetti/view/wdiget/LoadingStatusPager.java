package com.yyyu.confetti.view.wdiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yyyu.baselibrary.utils.MyLog;

/**
 * 功能：加载状态封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

public abstract class LoadingStatusPager extends FrameLayout{

    // 加载的状态
    private static final int STATE_LOADING = 2;
    // 加载失败的状态
    private static final int STATE_ERROR = 3;
    // 加载空的状态
    private static final int STATE_EMPTY = 4;
    // 加载成功的状态
    private static final int STATE_SUCCEED = 5;

    private int mState;// 默认的状态

    private int loadpage_empty;
    private int loadpage_error;
    private int loadpage_loading;
    private View loadingView;
    private View loadingEmpty;
    private View loadingError;
    private View successView;
    private int status;

    public LoadingStatusPager(Context context, int loading, int error, int empty) {
        super(context);
        loadpage_empty = empty;
        loadpage_error = error;
        loadpage_loading = loading;
        init(context);
    }

    private void init(Context context) {
        loadingView = LayoutInflater.from(context).inflate(loadpage_loading , null);
        loadingEmpty = LayoutInflater.from(context).inflate(loadpage_empty , null);
        loadingError = LayoutInflater.from(context).inflate(loadpage_error , null);
        successView = getContentView();
        addView(successView , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status) {
                    case STATE_ERROR:
                        removeView(loadingError);
                        reload();
                        break;
                    case STATE_EMPTY:
                        removeView(loadingEmpty);
                        reload();
                        break;
                }
            }
        });
    }

    protected abstract void reload();

    public  void setViewByStatus(LoadResult result){
        status = result.getValue();
        //removeAllViews();
        MyLog.e("==============="+result);

        switch (status){
            case STATE_LOADING:
                addView(loadingView , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case STATE_ERROR:
                removeView(loadingView);
                addView(loadingError , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case STATE_EMPTY:
                removeView(loadingView);
                addView(loadingEmpty , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                break;
            case STATE_SUCCEED:
                removeView(loadingView);
                break;
        }
    }

    /**
     * 得到真正的内容界面
     * @return
     */
    public abstract  View getContentView();

    public enum LoadResult {
        LOADING(2) ,ERROR(3), EMPTY(4), SUCCESS(5);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
