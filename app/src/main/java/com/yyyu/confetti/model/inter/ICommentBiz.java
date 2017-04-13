package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：评论数据获取
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public interface ICommentBiz {

    void getComment(IRequestCallback<ContentCom> callback ,int type ,  String id, int page);

}
