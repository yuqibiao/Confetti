package com.yyyu.confetti.presenter;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.model.NetaseContentBiz;
import com.yyyu.confetti.model.inter.INetaseContentBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.INetaseContentActPresenter;
import com.yyyu.confetti.view.inter.INetaseContentView;

/**
 * 功能：对应NetaseContentActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/22
 */

public class NetaseContentActPresenter implements INetaseContentActPresenter{

    private static final String TAG = "NetaseContentActPresent";

    private INetaseContentView contentView;
    private INetaseContentBiz contentBiz;

    public NetaseContentActPresenter(INetaseContentView contentView){
        this.contentView = contentView;
        contentBiz = new NetaseContentBiz();
    }

    @Override
    public void getData(String id) {
        contentBiz.getData(new IRequestCallback<NetaseContent>() {
            @Override
            public void onSuccess(NetaseContent result) {
                contentView.setData(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e(TAG , "网络请求出错："+ throwable.getMessage());
            }
        } , id);
    }

    @Override
    public void getComment(String id) {
        contentBiz.getComment(new IRequestCallback<ContentCom>() {
            @Override
            public void onSuccess(ContentCom result) {
                contentView.setComment(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e("出错===="+throwable.getMessage());
            }
        }, id , 0);
    }
}
