package com.yyyu.confetti.presenter.inter;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public interface IClassifyItemFragPresenter {

    /**
     * 得到数据
     * @param getDataType 0：刷新 1：加载更多
     * @param type 0:知乎日报  1：网易公开课 2：简书 3：CSDN
     */
    void getData(int getDataType , int type ,  String tabId);

}
