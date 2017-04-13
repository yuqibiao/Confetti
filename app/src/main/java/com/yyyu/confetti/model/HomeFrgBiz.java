package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.model.inter.IHomeFrgBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：HomeFragment 业务逻辑具体实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class HomeFrgBiz implements IHomeFrgBiz{

    private final APIMethodManager manager;

    public HomeFrgBiz(){
        manager = APIMethodManager.getInstance();
    }


    @Override
    public  void getHomeDataByDate(IRequestCallback<HomeData> iRequestCallback, int page) {
        manager.getHomeDataByPage(iRequestCallback, page);
    }

    @Override
    public void getTopData(IRequestCallback<HomeTop> callback) {
        manager.getTopData(callback);
    }
}
