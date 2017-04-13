package com.yyyu.confetti.bean;

import java.util.List;

/**
 * 功能：Top数据统一封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public class HomeTop {

    public List<HomeItem> topData;

    public List<HomeItem> getTopData() {
        return topData;
    }

    public void setTopData(List<HomeItem> topData) {
        this.topData = topData;
    }
}
