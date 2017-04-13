package com.yyyu.baselibrary.view.recyclerview.listener;

import android.support.v4.os.TraceCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.yyyu.baselibrary.view.recyclerview.adapter.HeaderAndFooterWrapper;


/**
 * 功能：对RecyclerView 点击时间&长按事件的封装
 *
 * why:
 * 为什么官方不实现点击Item的点击事件？
 * RecyclerView负责控制/框架，LayoutManager负责计算布局，假设将ItemClickListener放到RecyclerView上，
 * 如果要实现点击事件，首先需要确定每一个item的点击区域。但是RecyclerView无法知道每一个item的点击区域，
 * 因为LayoutManager是可以由开发者来实现的，也就是说两个View的区域是允许重叠的。如果点了A和B重叠区域
 * 到底是触发A还是B，又必须要由LayoutManager来决定。以还不如直接放到LayoutManager中，但如果放到
 * LayoutManager中的话需要给RecyclerView添加OnTouchListener，看上去又很别扭（要是外部给RecyclerView
 * 设置OnTouchListener会覆盖掉这个导致ItemClickListener失效）。所以干脆交给开发者。
 *
 * how:
 *1.通过 RecyclerView已有的方法 addOnItemTouchListener()实现。
 *2.在创建 ItemView时添加点击监听。
 *3.当 ItemViewAttach RecyclerView时实现。
 * 本demo使用的是方法1
 *
 * key:
 * 1.使用GestureDetectorCompat来接管触摸事件，不用自己去判断用户的触摸行为去确定是什么类型的时间
 *    该工具类里面已经封装好了单击、长按、双击等事件。
 * 2.SimpleOnGestureListener 是OnGestureListener的一个空实现。
 * 3.RecyclerView 的findChildViewUnder(e.getX() , e.getY());通过位置得到子view。
 * 4.RecyclerView的getChildViewHolder方法通过传入子view得到子iew的ViewHolder。
 *5.兼容HeaderAndFooterWrapper，Header的Item个数去掉。且设置Header和Footer不可点击。
 *
 * 参考：http://www.jianshu.com/p/c5596537aa34
 *
 * Created by yyyu on 2016/12/23.
 */

public abstract class OnRvItemClickListener implements RecyclerView.OnItemTouchListener {

    GestureDetectorCompat mGestureDetector;

    public OnRvItemClickListener(final RecyclerView recyclerView){
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        View cView  = recyclerView.findChildViewUnder(e.getX() , e.getY());
                        if (cView == null) return true;
                        RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(cView);
                        if(vh != null ){
                            int oldPosition = vh.getAdapterPosition();
                            if(adapter instanceof HeaderAndFooterWrapper){
                                if ( ((HeaderAndFooterWrapper) adapter).isHeaderPosition(oldPosition)
                                        ||((HeaderAndFooterWrapper) adapter).isFooterPosition(oldPosition) ){
                                    return false;
                                }else{
                                    onItemClick(cView , oldPosition- ((HeaderAndFooterWrapper) adapter).getHeaderCount());
                                }
                            }else{
                                onItemClick(cView , oldPosition);
                            }
                        }
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        View cView  = recyclerView.findChildViewUnder(e.getX() , e.getY());
                        if (cView== null) return ;
                        RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(cView);
                        if(vh != null){
                            int oldPosition = vh.getAdapterPosition();
                            if(adapter instanceof HeaderAndFooterWrapper){
                                if ( ((HeaderAndFooterWrapper) adapter).isHeaderPosition(oldPosition)
                                        ||((HeaderAndFooterWrapper) adapter).isFooterPosition(oldPosition) ){
                                }else{
                                    onItemLongClick(cView , oldPosition- ((HeaderAndFooterWrapper) adapter).getHeaderCount());
                                }
                            }else{
                                onItemLongClick(cView , oldPosition);
                            }
                        }
                    }
                });

    }



    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract  void onItemClick(View itemView , int position);

    public abstract  void onItemLongClick(View itemView , int position);

}
