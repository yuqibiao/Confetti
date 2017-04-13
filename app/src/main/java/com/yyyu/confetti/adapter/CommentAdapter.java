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
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.R;

import java.util.List;

/**
 * 功能：评论Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ComHolder>{

    private Context mContext;
    private List<ContentCom.Comment> mData;

    public CommentAdapter(Context context , List<ContentCom.Comment> data){
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ComHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_comment , parent , false);
        return new ComHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComHolder holder, int position) {
        ContentCom.Comment comment = mData.get(position);
        if(!TextUtils.isEmpty(comment.getUserIcon())){
            Glide.with(mContext).load(comment.getUserIcon()).into(holder.getIvComUserIcon());
        }
        holder.getTvUsername().setText(comment.getUserName());
        holder.getTvComContent().setText(comment.getComContent());
        holder.getTvComTime().setText(comment.getComTime());
        holder.getTvLikeNum().setText(comment.getLikeNum()+"");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ComHolder extends RecyclerView.ViewHolder{

        private ImageView ivComUserIcon;
        private TextView tvUsername;
        private TextView tvComContent;
        private TextView tvComTime;
        private TextView tvLikeNum;

        public ComHolder(View itemView) {
            super(itemView);
            ivComUserIcon = (ImageView) itemView.findViewById(R.id.iv_com_user_icon);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_com_username);
            tvComContent = (TextView) itemView.findViewById(R.id.tv_com_content);
            tvComTime = (TextView) itemView.findViewById(R.id.tv_com_time);
            tvLikeNum = (TextView) itemView.findViewById(R.id.tv_com_like);
        }

        public ImageView getIvComUserIcon() {
            return ivComUserIcon;
        }

        public void setIvComUserIcon(ImageView ivComUserIcon) {
            this.ivComUserIcon = ivComUserIcon;
        }

        public TextView getTvUsername() {
            return tvUsername;
        }

        public void setTvUsername(TextView tvUsername) {
            this.tvUsername = tvUsername;
        }

        public TextView getTvComContent() {
            return tvComContent;
        }

        public void setTvComContent(TextView tvComContent) {
            this.tvComContent = tvComContent;
        }

        public TextView getTvComTime() {
            return tvComTime;
        }

        public void setTvComTime(TextView tvComTime) {
            this.tvComTime = tvComTime;
        }

        public TextView getTvLikeNum() {
            return tvLikeNum;
        }

        public void setTvLikeNum(TextView tvLikeNum) {
            this.tvLikeNum = tvLikeNum;
        }
    }

    public List<ContentCom.Comment> getmData() {
        return mData;
    }

    public void setmData(List<ContentCom.Comment> mData) {
        this.mData = mData;
    }
}
