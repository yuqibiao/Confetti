package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.model.inter.ICommentBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：获取评论数据具体实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class CommentBiz implements ICommentBiz{

    private APIMethodManager manager;

    public CommentBiz(){
        this.manager = APIMethodManager.getInstance();
    }

    @Override
    public void getComment(IRequestCallback<ContentCom> callback, int type, String id, int page) {
        manager.getContentComById(callback , type , id , page);
    }
}
