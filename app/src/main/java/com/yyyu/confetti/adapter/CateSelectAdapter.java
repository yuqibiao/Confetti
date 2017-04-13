package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.CollCate;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/5
 */

public class CateSelectAdapter extends RecyclerView.Adapter<CateSelectAdapter.ItemHolder> {

    private Context mContext;
    private List<CollCate> mData;

    private int selectedPos = 0;

    public CateSelectAdapter(Context context, List<CollCate> data) {
        this.mContext = context;
        this.mData = data;
    }


    @Override
    public CateSelectAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pt_item_coll_cate, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CateSelectAdapter.ItemHolder holder, int position) {
        holder.cbCate.setText(mData.get(position).getCateTitle());
        if(position == selectedPos){
            holder.cbCate.setChecked(true);
        }else{
            holder.cbCate.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        CheckBox cbCate;

        public ItemHolder(View itemView) {
            super(itemView);
            cbCate = (CheckBox) itemView.findViewById(R.id.cb_cate);
        }


    }

}
