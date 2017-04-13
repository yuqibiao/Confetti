package com.yyyu.confetti.bean;

import java.util.List;

/**
 * 功能：首页数据对应Bean
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/17
 */

public class HomeData {

    private List<HomeItem> homeDataList;

    public List<HomeItem> getHomeDataList() {
        return homeDataList;
    }

    public void setHomeDataList(List<HomeItem> homeDataList) {
        this.homeDataList = homeDataList;
    }
}
