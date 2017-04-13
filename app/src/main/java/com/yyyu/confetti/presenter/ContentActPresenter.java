package com.yyyu.confetti.presenter;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.model.ContentBiz;
import com.yyyu.confetti.model.inter.IContentBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IContentActPresenter;
import com.yyyu.confetti.view.inter.IContentView;

/**
 * 功能：ContentShowActivity业务逻辑具体实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class ContentActPresenter implements IContentActPresenter{

    private static final String TAG = "ContentActPresenter";

    private IContentView mContentView;
    private IContentBiz mContentBiz;

    public ContentActPresenter(IContentView contentView){
        this.mContentView = contentView;
        this.mContentBiz = new ContentBiz();
    }

    @Override
    public void getData( int type , String id , String username) {
        mContentBiz.getData(new IRequestCallback<ItemContent>() {
            @Override
            public void onSuccess(ItemContent result) {
                mContentView.setData(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
                MyLog.e(TAG, "网络请求出错==="+throwable.getMessage());
            }
        }, type,id , username);
    }
}
