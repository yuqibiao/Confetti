package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.CollBean;

import java.util.List;

/**
 * 功能：收藏详情页对应Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollDetailAdapter extends RecyclerView.Adapter<CollDetailAdapter.ItemHolder>{

    private Context mContext;
    private List<CollBean> mData;

    public CollDetailAdapter(Context context , List<CollBean>data){
        this.mContext = context;
        this.mData = data;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext ).inflate(R.layout.rv_coll_detail , parent , false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.tvCollTitle.setText(mData.get(position).getCollTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
