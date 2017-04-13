package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.LoginFormBean;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应LoginActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public interface ILoginBiz {

    void toLogin(LoginFormBean loginFormBean , IRequestCallback<UserInfo> callback);

}
