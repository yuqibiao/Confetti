package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.CollCate;

import java.util.List;

/**
 * 功能：收藏分类项对应的Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollCateAdapter extends RecyclerView.Adapter<CollCateAdapter.ItemHolder>{

    private Context mContext;

    private List<CollCate> mData;

    int checkedPos = 0;

    public CollCateAdapter(Context context , List<CollCate> data){
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_coll_cate , parent ,false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if (position == checkedPos){
            holder.tvCollTitle.setBackgroundColor(mContext.getResources().getColor(R.color.bgGray));
            holder.tvCollTitle.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }else{
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.myWhite));
            holder.tvCollTitle.setTextColor(mContext.getResources().getColor(R.color.tvGray));
        }
        holder.getTvCollTitle().setText(mData.get(position).getCateTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setCheckedPos(int checkedPos) {
        notifyDataSetChanged();
        this.checkedPos = checkedPos;
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        private TextView tvCollTitle;

        public ItemHolder(View itemView) {
            super(itemView);
            tvCollTitle = (TextView) itemView.findViewById(R.id.tv_coll_title);
        }

        public TextView getTvCollTitle() {
            return tvCollTitle;
        }
    }

}
