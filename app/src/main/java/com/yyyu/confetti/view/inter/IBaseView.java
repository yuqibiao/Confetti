package com.yyyu.confetti.view.inter;

/**
 * 功能：view层基类接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/12
 */

public interface IBaseView {

    void showToast(String msg);
    void showProgress();
    void hiddenProgress();
    void showSnackBar(String msg);

}
