package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.ClassifyData;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public interface IClassifyItemFrgView extends IBaseView{

    void onRefresh(ClassifyData classifyData);
    void onRefreshFinished();
    void onLoadMore(ClassifyData classifyData);
    void onLoadMoreFinished();

}
