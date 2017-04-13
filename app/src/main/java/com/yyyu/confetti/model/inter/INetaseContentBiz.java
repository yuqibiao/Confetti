package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：网易公开课 NetaseContentActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/22
 */

public interface INetaseContentBiz {

    void getData(IRequestCallback<NetaseContent> callback, String id);
    void getComment(IRequestCallback<ContentCom> callback ,String id,int page);

}
