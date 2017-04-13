package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.RegisterFormBean;

/**
 * 功能：对应RegisterActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public interface IRegisterView extends IBaseView{

    RegisterFormBean getInputInfo();

    void toLoginAct();

}
