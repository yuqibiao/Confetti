package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：HomeFragment 业务逻辑接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public interface IHomeFrgBiz extends IBaseBiz {

    /**
     * @param iRequestCallback 结果回调
     * @param page 当前页
     * @return
     */
    void getHomeDataByDate(IRequestCallback<HomeData> iRequestCallback , int  page );

    /**
     * 得到轮播图（Top）数据
     * @param callback
     */
    void getTopData(IRequestCallback<HomeTop> callback);

}
