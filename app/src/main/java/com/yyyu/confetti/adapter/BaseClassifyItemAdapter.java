package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.baselibrary.anim.AlphaInAnim;
import com.yyyu.baselibrary.anim.BottomInAnim;
import com.yyyu.baselibrary.anim.ItemAnim;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ClassifyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：分类中tem对应的Base Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public abstract  class BaseClassifyItemAdapter extends RecyclerView.Adapter<BaseClassifyItemAdapter.ItemHolder>{

    protected Context mContext;

    protected List<ClassifyItem> mData = new ArrayList<>();

    public BaseClassifyItemAdapter(Context context , List<ClassifyItem> data){
        this.mContext = context;
        this.mData = data;
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  itemView = LayoutInflater.from(mContext).inflate(getViewId(), parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {

        switch (mData.get(position).getType()){
            case 0:
            case 1:
                ItemAnim itemAnim = ItemAnim.getInstance(mContext);
                itemAnim.setAnim(new BottomInAnim());
                itemAnim.startAnimator(holder.itemView , position);
                break;
            case 2:
            case 3:
                ItemAnim itemAnim1 = ItemAnim.getInstance(mContext);
                itemAnim1.setAnim(new AlphaInAnim());
                itemAnim1.startAnimator(holder.itemView , position);
                break;
        }

        holder.getTvItemTitle().setText(mData.get(position).getTitle());
        holder.getTvItemSubtitle().setText(mData.get(position).getSubTitle());
        Glide.with(mContext)
                .load(mData.get(position).getImgPath())
                .into(holder.getIvItemIcon());
        if(holder.getTvItemTag()!=null){
            holder.getTvItemTag().setText("来自："+mData.get(position).getUsername());
        }
        if(holder.getTvItemTime()!=null){
            holder.getTvItemTime().setText(mData.get(position).getTime());
        }
        if (holder.getIvContentIcon() !=null  ){
            if(!TextUtils.isEmpty(mData.get(position).getContentIconPath())){
                holder.getIvContentIcon().setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(mData.get(position).getContentIconPath())
                        .into(holder.getIvContentIcon());
            }else{
                holder.getIvContentIcon().setVisibility(View.GONE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    protected abstract  int getViewId();

    class ItemHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView tvItemTitle;
        private TextView tvItemSubtitle;
        private TextView tvItemTag;
        private TextView tvItemTime;
        private ImageView ivItemIcon;
        private ImageView ivContentIcon;

        public ItemHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvItemTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvItemSubtitle = (TextView) itemView.findViewById(R.id.tv_item_subtitle);
            tvItemTag = (TextView) itemView.findViewById(R.id.tv_item_tag);
            tvItemTime = (TextView) itemView.findViewById(R.id.tv_item_time);
            ivItemIcon = (ImageView) itemView.findViewById(R.id.iv_item_icon);
            ivContentIcon = (ImageView) itemView.findViewById(R.id.iv_content_icon);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getTvItemTitle() {
            return tvItemTitle;
        }

        public TextView getTvItemSubtitle() {
            return tvItemSubtitle;
        }

        public ImageView getIvItemIcon() {
            return ivItemIcon;
        }

        public TextView getTvItemTag() {
            return tvItemTag;
        }

        public void setTvItemTag(TextView tvItemTag) {
            this.tvItemTag = tvItemTag;
        }

        public TextView getTvItemTime() {
            return tvItemTime;
        }

        public void setTvItemTime(TextView tvItemTime) {
            this.tvItemTime = tvItemTime;
        }

        public ImageView getIvContentIcon() {
            return ivContentIcon;
        }

        public void setIvContentIcon(ImageView ivContentIcon) {
            this.ivContentIcon = ivContentIcon;
        }
    }

    public List<ClassifyItem> getmData() {
        return mData;
    }

    public void setmData(List<ClassifyItem> mData) {
        this.mData = mData;
    }
}
