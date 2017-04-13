package com.yyyu.baselibrary.view.recyclerview.refresh;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;


/**
 * 功能：具有上拉加载的SwipeRefreshLayout
 */

public class RefreshLayout extends SwipeRefreshLayout{

    private static final String TAG = "RefreshLayout=============";

    /**
     * 滑动到最下面时的上拉操作
     */

    private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;

    /**
     * RecyclerView实例
     */
    private RecyclerView mRecyclerView;

    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnRefreshWrapperListener mOnRefreshWrapperListener;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    /**
     * @param context
     */
    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if (mListView == null || mRecyclerView == null) {
            getChildView();
        }
    }

    /**
     * 获取ListView或者RecyclerView对象
     */
    private void getChildView() {
        int childs = getChildCount();
        for (int i = 0; i < childs; i++) {
            View childView = getChildAt(i);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
            } else if (childView instanceof RecyclerView) {
                mRecyclerView = (RecyclerView) childView;
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起（判断是否加载更多）
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多
     *
     * @return
     */
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        } else if (mRecyclerView != null && mRecyclerView.getAdapter() != null) {
            int lastItemPosition = 0;
            RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                lastItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                int[] lastPos = manager.findLastCompletelyVisibleItemPositions(null);
                for (int i : lastPos) {
                    lastItemPosition = Math.max(lastItemPosition, i);
                }
                // lastItemPosition = manager.findLastCompletelyVisibleItemPositions(null)[manager.getSpanCount()-1];
            } else if (layoutManager instanceof GridLayoutManager) {
                lastItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else {
                throw new UnsupportedOperationException("目前还不支持该类型LayoutManager");
            }
            return lastItemPosition + 1 == mRecyclerView.getAdapter().getItemCount();
        }
        return false;
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData() {
        if (mOnRefreshWrapperListener != null) {
            setLoading(true);
            mOnRefreshWrapperListener.onLoadMore();
        }
    }

    /**
     * 设置加载完成
     */
    private void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
        } else {
            mYDown = 0;
            mLastY = 0;
        }
    }

    public void setLoadFinished() {
        setLoading(false);
    }

    /**
     * 设置加载更多监听
     */
    public void setOnRefreshWrapper(OnRefreshWrapperListener loadListener) {
        mOnRefreshWrapperListener = loadListener;
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mOnRefreshWrapperListener.onRefresh();
            }
        });
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        super.setOnRefreshListener(listener);
    }

    /**
     * 加载更多的监听器
     *
     * @author mrsimple
     */
    public interface OnRefreshWrapperListener {
        void onRefresh();
        void onLoadMore();
    }

}