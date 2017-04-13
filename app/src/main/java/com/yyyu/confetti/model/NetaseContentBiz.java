package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.model.inter.INetaseContentBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：NetaseContentActivity 业务逻辑具体实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/22
 */

public class NetaseContentBiz implements INetaseContentBiz{

    private final APIMethodManager apiMethodManager;

    public NetaseContentBiz(){
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    public void getData(IRequestCallback<NetaseContent> callback, String id) {
        apiMethodManager.getNetaseContentById(callback , id);
    }

    @Override
    public void getComment(IRequestCallback<ContentCom> callback, String id, int page) {
        apiMethodManager.getContentComById(callback , 1 ,  id ,page );
    }

}
