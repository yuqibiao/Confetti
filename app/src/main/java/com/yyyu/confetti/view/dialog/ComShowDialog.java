package com.yyyu.confetti.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.baselibrary.view.BottomSheetDialogWrapper;
import com.yyyu.baselibrary.view.recyclerview.refresh.RefreshLayout;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CommentAdapter;
import com.yyyu.confetti.presenter.CommentPresenter;
import com.yyyu.confetti.presenter.inter.ICommentPresenter;
import com.yyyu.confetti.view.inter.IComDialogView;

import java.util.List;

/**
 * 功能：评论展示Dialog
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class ComShowDialog implements IComDialogView {

    private Context mContext;

    private final BottomSheetDialogWrapper bottomSheetDialog;
    private final RecyclerView rvCom;
    private final ICommentPresenter presenter;
    private String comId;
    private final RefreshLayout rlCom;
    private CommentAdapter adapter;

    int type;

    /**
     * @param context
     * @param type       0：知乎日报 1：网易公开课 2：简书 3：CSDN
     * @param peekHeight
     * @param maxHeight
     * @param comId
     */
    public ComShowDialog(Context context, int type, int peekHeight, int maxHeight, String comId) {
        this.mContext = context;
        this.type = type;
        this.comId = comId;
        presenter = new CommentPresenter(this);
        bottomSheetDialog = new BottomSheetDialogWrapper(mContext, peekHeight, maxHeight);

        View bottom = LayoutInflater.from(mContext).inflate(R.layout.pt_com_rv, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WindowUtils.getSize(mContext)[0], maxHeight);
        bottom.setLayoutParams(lp);

        rvCom = (RecyclerView) bottom.findViewById(R.id.rv_com);
        rlCom = (RefreshLayout) bottom.findViewById(R.id.rl_com);
        rvCom.setLayoutManager(new LinearLayoutManager(mContext));
        bottomSheetDialog.setContentView(bottom);

        if(type==1){
            rlCom.setEnabled(false);
        }

        initListener();
        initData();
    }

    private void initListener() {
        rlCom.setOnRefreshWrapper(new RefreshLayout.OnRefreshWrapperListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.getData(type, 1, comId);
            }
        });
    }

    private void initData() {
        presenter.getData(type , 0, comId);
    }


    public void show() {
        bottomSheetDialog.show();
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
