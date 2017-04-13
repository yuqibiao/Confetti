package com.yyyu.confetti.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CommentAdapter;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.presenter.CommentPresenter;
import com.yyyu.confetti.presenter.inter.ICommentPresenter;
import com.yyyu.confetti.view.inter.IComDialogView;

import java.util.List;

/**
 * 功能：知乎日报评论
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/25
 */

public class ZhihuContentShowDialog extends BaseBottomDialog implements IComDialogView{

    private CommentAdapter adapter;
    private RecyclerView rvCom;
    private ICommentPresenter presenter;


    public ZhihuContentShowDialog(Context context, int peekHeight, int maxHeight , String comId) {
        super(context, peekHeight, maxHeight, comId);
    }

    @Override
    protected void initView() {
        rvCom = (RecyclerView) bottomView.findViewById(R.id.rv_com);
        rvCom.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public int getLayoutId() {
        return R.layout.pt_com_zhihu_rv;
    }

    @Override
    protected void initData() {
        presenter = new CommentPresenter(this);
        presenter.getData(0 , comId);
    }

    @Override
    public void onRefresh(List<ContentCom.Comment> commentLis) {
        adapter = new CommentAdapter(mContext, commentLis);
        rvCom.setAdapter(adapter);
    }

    @Override
    public void onLoadMore(List<ContentCom.Comment> commentLis) {

    }

    @Override
    public void onLoadFinished() {

    }
}
