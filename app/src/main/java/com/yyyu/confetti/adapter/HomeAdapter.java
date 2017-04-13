package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.baselibrary.anim.AlphaInAnim;
import com.yyyu.baselibrary.anim.ItemAnim;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：首页数据Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/17
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ItemHolder> {

    private static final String TAG = "HomeAdapter";

    private Context mContext;
    private List<HomeItem> mData = new ArrayList<>();

    public HomeAdapter(Context context) {
        this.mContext = context;
    }

    public HomeAdapter(Context context, HomeData data) {
        this.mContext = context;
        this.mData = data.getHomeDataList();
    }

    @Override
    public HomeAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        ItemHolder viewHolder = null;
        switch (viewType) {
            case 0://知乎日报
                itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_zhrb, parent, false);
                break;
            case 1://网易公开课
                itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_netase, parent, false);
                break;
            case 2://简书
                itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_js, parent, false);
                break;
            case 3://CSDN
                itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_csdn, parent, false);
                break;
            case 100://tip指示条
                itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_tip, parent , false);
                break;
        }
        if (itemView == null) {
            throw new UnsupportedOperationException("传入参数viewType值有误");
        }
        viewHolder = new ItemHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ItemHolder holder, int position) {
        ItemAnim itemAnim = ItemAnim.getInstance(mContext);
        itemAnim.setAnim(new AlphaInAnim());
        itemAnim.startAnimator(holder.itemView , position);
        switch (mData.get(position).getType()){
            case 1:
            case 3:
                break;
            case 0:
            case 2:
                setFullSpan(holder);
                break;
            case 100:
                setFullSpan(holder);
                break;
        }
        if(holder.getTvItemTitle()!=null){
            holder.getTvItemTitle().setText(mData.get(position).getTitle());
        }
        if(holder.getTvItemSubtitle()!=null){
            holder.getTvItemSubtitle().setText(mData.get(position).getSubTitle());
        }
        if(holder.getIvItemIcon()!=null){
            Glide.with(mContext)
                    .load(mData.get(position).getImgPath())
                    .into(holder.getIvItemIcon());
        }
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

    private void setFullSpan(ItemHolder holder) {
        ViewGroup.LayoutParams lp =  holder.itemView.getLayoutParams();
        if (lp!=null && lp instanceof StaggeredGridLayoutManager.LayoutParams){
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    public void setmData(List<HomeItem> mData) {
        this.mData = mData;
    }


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

        public TextView getTvItemTime() {
            return tvItemTime;
        }

        public ImageView getIvContentIcon() {
            return ivContentIcon;
        }
    }
}
