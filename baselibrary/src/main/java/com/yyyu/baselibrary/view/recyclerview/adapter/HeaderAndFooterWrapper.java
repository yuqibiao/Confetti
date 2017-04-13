package com.yyyu.baselibrary.view.recyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyyu.baselibrary.utils.MyLog;

/**
 * 功能：扩展Adapter 使得能添加Header和Footer
 *
 * key：
 * 1.装饰者模式对原有的Adapter进行扩展
 * 2.核心思路是通过getItemViewType的返回在onCreateViewHolder方法里判断viewType的值
 * 来确定是头部还是内容或者是底部Item
 *
 *
 * 参考：http://blog.csdn.net/lmj623565791/article/details/51854533
 *
 * Created by yyyu on 2016/12/23.
 */

public class HeaderAndFooterWrapper extends RecyclerView.Adapter{

    private static final String TAG = "HeaderAndFooterWrapper";

    private final static int BASE_HEADER_NUM = 200000;
    private final static int BASE_FOOTER_NUM  = BASE_HEADER_NUM+100000;
    private RecyclerView.Adapter innerAdapter;
    private SparseArray<View> headerList;
    private SparseArray<View> footerList;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter){
        this.innerAdapter = adapter;
        headerList = new SparseArray<>();
        footerList = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(headerList.get(viewType) !=null){
            return new TempViewHolder(headerList.get(viewType));
        }else if(footerList.get(viewType) != null){
            return new TempViewHolder(footerList.get(viewType));
        }else{
            return innerAdapter.onCreateViewHolder(parent , viewType);
        }

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isHeaderPosition(position) || isFooterPosition(position)){
            return;
        }else{
            innerAdapter.onBindViewHolder(holder , position-headerList.size());
        }
    }
    @Override
    public int getItemCount() {
        return headerList.size()+getRealItemCount()+footerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)){
            return headerList.keyAt(position);
        }else if (isFooterPosition(position)){
            return footerList.keyAt(position-headerList.size()-getRealItemCount());
        }
        return innerAdapter.getItemViewType(position-headerList.size());
    }

    public boolean isFooterPosition(int position){
        return position>=headerList.size()+getRealItemCount();
    }

    public boolean isHeaderPosition(int position){
        return position<headerList.size();
    }

    private class TempViewHolder extends RecyclerView.ViewHolder{
        public TempViewHolder(View itemView) {
            super(itemView);
        }
    }
    /**
     * 得到Header的个数
     *
     * @return
     */
    public int  getHeaderCount(){
        return headerList.size();
    }
    /**
     * 添加Footer
     *
     * @param footerView
     */
    public void addFooter(View footerView){
        footerList.put(BASE_FOOTER_NUM+footerList.size(), footerView);
    }

    /**
     * 移除Footer
     */
    public void removeLastFooter(){
        if(footerList.size()>=1){
            footerList.remove(BASE_FOOTER_NUM+footerList.size()-1);
        }
    }

    /**
     * 添加Header
     *
     * @param headerView
     */
    public void addHeader(View headerView){
        headerList.put(BASE_HEADER_NUM+headerList.size() ,headerView );
    }
    /**
     * 得到内容item的格式
     * @return
     */
    public int getRealItemCount(){
        return innerAdapter.getItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        innerAdapter.onAttachedToRecyclerView(recyclerView);
        //处理GridLayoutManager时Header和Footer不单独占一行
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //Returns the number of span occupied by the item at position.
                    //返回1表示占一个格子，为Header或者Footer的时候设置为Grid的列数也就是一行。
                    if(isHeaderPosition(position) || isFooterPosition(position)){
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }else{
                        return 1;
                    }
                }
            });
        }
    }
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        innerAdapter.onViewAttachedToWindow(holder);
        //处理StaggerGridLayoutManager的Header和Footer不跨行的情况。
        int position = holder.getLayoutPosition();
        if (isHeaderPosition(position) || isFooterPosition(position)){
           ViewGroup.LayoutParams lp =  holder.itemView.getLayoutParams();
            if (lp!=null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }
}
