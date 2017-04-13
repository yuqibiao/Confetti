package com.yyyu.confetti.presenter;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.model.HomeFrgBiz;
import com.yyyu.confetti.model.inter.IHomeFrgBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IHomeFrgPresenter;
import com.yyyu.confetti.view.inter.IHomeFrgView;

/**
 * 功能：HomeFragment presenter实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class HomeFrgPresenter implements IHomeFrgPresenter {

    private static final String TAG = "HomeFrgPresenter";

    private IHomeFrgView homeFrgView;
    private IHomeFrgBiz homeFrgBiz;

    public HomeFrgPresenter(IHomeFrgView homeFrgView) {
        this.homeFrgView = homeFrgView;
        this.homeFrgBiz = new HomeFrgBiz();
    }

    /**
     * 获取数据
     *
     * @param type 0：刷新  1：加载下一页
     */
    private int pageNow = 0;

    public void getDate(final int type) {

        homeFrgView.showProgress();

        if (type == 0) {
            pageNow = 0;
        } else if (type == 1) {
            pageNow++;
        }

        homeFrgBiz.getHomeDataByDate(new IRequestCallback<HomeData>() {


            @Override
            public void onSuccess(HomeData result) {
                if (type == 0) {
                    homeFrgView.onRefresh(result);
                    homeFrgView.onRefreshFinished();
                } else if (type == 1) {
                    homeFrgView.onLoadMore(result);
                    homeFrgView.onLoadMoreFinished();
                }
                homeFrgView.hiddenProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e(TAG , "getDate："+throwable.getMessage());
            }
        }, pageNow);

    }

    @Override
    public void getTopData() {
        homeFrgBiz.getTopData(new IRequestCallback<HomeTop>() {
            @Override
            public void onSuccess(HomeTop result) {
                homeFrgView.setTopData(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                MyLog.e(TAG , "getTopData："+throwable.getMessage());
            }
        });
    }

}
