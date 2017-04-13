package com.yyyu.confetti.presenter;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.model.CommentBiz;
import com.yyyu.confetti.model.inter.ICommentBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.ICommentPresenter;
import com.yyyu.confetti.view.inter.IComDialogView;

/**
 * 功能：评论
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class CommentPresenter implements ICommentPresenter{

    private IComDialogView mComDialogView;
    private ICommentBiz commentBiz;

    public CommentPresenter(IComDialogView comDialogView){
        this.mComDialogView = comDialogView;
        commentBiz = new CommentBiz();
    }


    private  int pageNow =0;
    @Override
    public void getData(final int type , final int getDataType , String id) {
        if(getDataType==0){//刷新
            pageNow = 0;
        }else if(getDataType==1){//加载更多
            pageNow++;
        }
        commentBiz.getComment(new IRequestCallback<ContentCom>() {
            @Override
            public void onSuccess(ContentCom result) {
                if (getDataType==0){
                    mComDialogView.onRefresh(result.getCommentList());
                }else if(getDataType==1 && type!=0){//---知乎日报没有加载更多
                    mComDialogView.onLoadMore(result.getCommentList());
                    mComDialogView.onLoadFinished();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e("====错误=="+throwable.getMessage());
            }
        } , type , id , pageNow);


    }

    @Override
    public void getData(int type, String id) {
        commentBiz.getComment(new IRequestCallback<ContentCom>() {
            @Override
            public void onSuccess(ContentCom result) {
                mComDialogView.onRefresh(result.getCommentList());
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e("====错误=="+throwable.getMessage());
            }
        } , type , id , 0);
    }

}
