package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.ContentCom;

import java.util.List;

/**
 * 功能：对应ComShowDialog
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public interface IComDialogView {
    void onRefresh(List<ContentCom.Comment> commentLis);
    void onLoadMore(List<ContentCom.Comment> commentLis);
    void onLoadFinished();

}
