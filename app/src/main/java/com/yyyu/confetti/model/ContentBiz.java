package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.ZhihuContent;
import com.yyyu.confetti.model.inter.IContentBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.net.zhihu.ZhihuMethods;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/19
 */

public class ContentBiz implements IContentBiz {

    private APIMethodManager manager;

    public ContentBiz() {
        manager = APIMethodManager.getInstance();
    }

    @Override
    public void getData(final IRequestCallback<ItemContent> callback, int type, String id , String username) {
        manager.getContentById(new IRequestCallback<ItemContent>() {
            @Override
            public void onSuccess(ItemContent result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }
        }, type, id , username);
    }
}
