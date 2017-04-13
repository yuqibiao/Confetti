package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.LoginFormBean;

/**
 * 功能：对应LoginActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public interface ILoginView extends IBaseView{

    LoginFormBean getInputInfo();

    void close();

}
