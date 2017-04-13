package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.baselibrary.utils.DimensChange;
import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.baselibrary.view.AdapterLinearLayout;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.presenter.NetaseContentActPresenter;
import com.yyyu.confetti.presenter.inter.INetaseContentActPresenter;
import com.yyyu.confetti.view.dialog.NetaseContentShowDialog;
import com.yyyu.confetti.view.inter.INetaseContentView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import tcking.github.com.giraffeplayer.GiraffePlayer;

public class NetaseDetailActivity extends BaseActivity implements INetaseContentView {

    private static final String TAG = "NetaseDetailActivity";

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.all_sub_video)
    AdapterLinearLayout allSubVideo;
    @BindView(R.id.all_comment)
    AdapterLinearLayout allComment;

    GiraffePlayer player;
    private String id;
    private INetaseContentActPresenter presenter;
    private NetaseContent.DataBean.VideoListBean firstBean;
    private List<NetaseContent.DataBean.VideoListBean> videoList;

    @Override
    public void beforeSetContentView() {
        setTransition();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_netase_detail;
    }

    @Override
    public void beforeInit() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void initView() {
        player = new GiraffePlayer(this);
    }

    @Override
    protected void initListener() {
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.toggleFullScreen();
            }
        });
        allSubVideo.setOnItemClickListener(new AdapterLinearLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                player.play(getPlayUrl(position));
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new NetaseContentActPresenter(this);
        presenter.getData(id);
    }

    @OnClick(R.id.tv_show_more_com)
    public void showMoreCom(View v) {
        int screenHeight = WindowUtils.getSize(this)[1];
        int bottomHeight = screenHeight - DimensChange.dp2px(this, 210);
        if (firstBean != null) {
            new NetaseContentShowDialog(this, bottomHeight, bottomHeight, firstBean.getCommentId()).show();
        }

    }


    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void setData(NetaseContent netaseContent) {
        NetaseContent.DataBean bean = netaseContent.getData();
        tvTitle.setText(bean.getTitle());
        tvDes.setText(bean.getDescription());
        Glide.with(this).load(bean.getImgPath()).into(ivIcon);

        videoList = bean.getVideoList();
        player.play(getPlayUrl(0));
        presenter.getComment(firstBean.getCommentId());

        allSubVideo.setAdapter(new AdapterLinearLayout.LinearAdapter() {
            @Override
            public int getItemCount() {
                return videoList.size();
            }

            @Override
            public View getView(ViewGroup parent, int position) {
                View item = LayoutInflater.from(NetaseDetailActivity.this)
                        .inflate(R.layout.pt_sub_video_item, parent, false);
                ImageView ivIcon = (ImageView) item.findViewById(R.id.iv_sub_video_icon);
                TextView tvTitle = (TextView) item.findViewById(R.id.tv_sub_video_title);
                Glide.with(NetaseDetailActivity.this).load(videoList.get(position).getImgPath()).into(ivIcon);
                tvTitle.setText(videoList.get(position).getTitle());
                return item;
            }
        });

    }

    private String getPlayUrl(int position) {
        firstBean = videoList.get(position);
        String videoUrl = null;
        if (!TextUtils.isEmpty(firstBean.getMp4HdUrl())) {
            videoUrl = firstBean.getMp4HdUrl();
        } else if (!TextUtils.isEmpty(firstBean.getMp4SdUrl())) {
            videoUrl = firstBean.getMp4SdUrl();
        } else if (!TextUtils.isEmpty(firstBean.getMp4ShdUrl())) {
            videoUrl = firstBean.getMp4ShdUrl();
        } else if (!TextUtils.isEmpty(firstBean.getMp4HdUrlOrign())) {
            videoUrl = firstBean.getMp4HdUrlOrign();
        } else if (!TextUtils.isEmpty(firstBean.getMp4SdUrlOrign())) {
            videoUrl = firstBean.getMp4SdUrlOrign();
        } else if (!TextUtils.isEmpty(firstBean.getMp4ShdUrlOrign())) {
            videoUrl = firstBean.getMp4ShdUrlOrign();
        }
        return videoUrl;
    }

    @Override
    public void setComment(final ContentCom contentCom) {
        final List<ContentCom.Comment> commentList = contentCom.getCommentList();
        allComment.setAdapter(new AdapterLinearLayout.LinearAdapter() {
            @Override
            public int getItemCount() {
                return commentList.size();
            }

            @Override
            public View getView(ViewGroup parent, int position) {
                View view = LayoutInflater.from(NetaseDetailActivity.this).inflate(R.layout.item_comment, parent, false);
                TextView tvComUsername = (TextView) view.findViewById(R.id.tv_com_username);
                TextView tvComContent = (TextView) view.findViewById(R.id.tv_com_content);
                TextView tvComTime = (TextView) view.findViewById(R.id.tv_com_time);
                TextView tvComLike = (TextView) view.findViewById(R.id.tv_com_like);
                ContentCom.Comment comment = contentCom.getCommentList().get(position);
                tvComUsername.setText(comment.getUserName());
                tvComContent.setText(comment.getComContent());
                tvComTime.setText(comment.getComTime());
                tvComLike.setText("" + comment.getLikeNum());
                return view;
            }
        });
    }

    public static void startAction(Activity activity, String id, ImageView ivShare) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, ivShare, activity.getResources().getString(R.string.netase_item_img_share)).toBundle();
        Intent intent = new Intent(activity, NetaseDetailActivity.class);
        intent.putExtra("id", id);
        ActivityCompat.startActivity(activity, intent, bundle);
    }
    public static void startAction(Activity activity, String id) {
        Intent intent = new Intent(activity, NetaseDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
        if (player.getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            ivIcon.setVisibility(View.GONE);
        } else {
            ivIcon.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }


}
