package com.yyyu.confetti.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yyyu.baselibrary.view.recyclerview.refresh.RefreshLayout;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CommentAdapter;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.presenter.CommentPresenter;
import com.yyyu.confetti.presenter.inter.ICommentPresenter;
import com.yyyu.confetti.view.inter.IComDialogView;

import java.util.List;

/**
 * 功能：网易公开课评论 Dialog
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/25
 */

public class NetaseContentShowDialog extends BaseBottomDialog implements IComDialogView{

    private CommentAdapter adapter;
    private  RecyclerView rvCom;
    private RefreshLayout rlCom;
    private  ICommentPresenter presenter;

    public NetaseContentShowDialog(Context context, int peekHeight, int maxHeight, String comId) {
        super(context, peekHeight, maxHeight , comId);
    }

    @Override
    protected void initView() {
        rvCom = (RecyclerView) bottomView.findViewById(R.id.rv_com);
        rlCom = (RefreshLayout) bottomView.findViewById(R.id.rl_com);
        rvCom.setLayoutManager(new LinearLayoutManager(mContext));
        rlCom.setEnabled(false);

        presenter = new CommentPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pt_com_rv;
    }

    @Override
    protected void initListener() {
        rlCom.setOnRefreshWrapper(new RefreshLayout.OnRefreshWrapperListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.getData(1, 1, comId);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getData(1 , 0, comId);
    }

    @Override
    public void onRefresh(List<ContentCom.Comment> commentLis) {
        adapter = new CommentAdapter(mContext, commentLis);
        rvCom.setAdapter(adapter);
    }

    @Override
    public void onLoadMore(List<ContentCom.Comment> commentLis) {
        List<ContentCom.Comment> data = adapter.getmData();
        data.addAll(commentLis);
        adapter.setmData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFinished() {
        rlCom.setLoadFinished();
    }
}
