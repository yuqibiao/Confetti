package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeTop;

/**
 * 功能：view层 HomeFragment接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public interface IHomeFrgView extends IBaseView{

    void onRefresh(HomeData homeData);
    void onRefreshFinished();
    void onLoadMore(HomeData homeData);
    void onLoadMoreFinished();
    void setTopData(HomeTop top);
    void toLoginAct();
    void showAddCollDialog();

}
