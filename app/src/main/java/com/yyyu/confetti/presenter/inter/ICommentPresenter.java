package com.yyyu.confetti.presenter.inter;

/**
 * 功能：评论
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public interface ICommentPresenter {

    /**
     * 获取数据
     * @param type 1：网易公开课 2：简书 3：CSDN
     * @param getDataType 0：刷新 1：加载更多
     * @param  id
     */
    void getData(int type , final int getDataType , String id);

    /**
     * 获取数据 （没有分页的）
     * @param type
     * @param id
     */
    void getData(int type , String id);

}
