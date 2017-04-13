package com.yyyu.baselibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 功能：对那些动态添加Item的LinearLayout的封装
 *            使其使用方式和ListView等控件相似
 *
 *
 * Created by yyyu on 2016/9/22.
 */
public class AdapterLinearLayout extends LinearLayout {

    private LinearAdapter mAdapter;
    private OnItemClickListener mOnItemClickListener;

    public AdapterLinearLayout(Context context) {
        super(context);
    }

    public AdapterLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(LinearAdapter adapter){
        this.mAdapter = adapter;
        removeAllViews();//---每次设Adapter先清空所有Item
        //---根据适配器中的数据添加item
        for(int i = 0 ; i<mAdapter.getItemCount() ; i++){
            View cView = mAdapter.getView(this , i);
            addView(cView);
            final int clickPosition = i;
            cView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener !=null){
                        mOnItemClickListener.onItemClick(clickPosition);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    /**
     * 设置Item点击事件回调
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    /**
     * 用来设置数据源
     */
    public static  abstract class LinearAdapter {
        public LinearAdapter(){

        }
        public abstract int  getItemCount();
        public abstract View getView(ViewGroup parent , int position);
    }

}
