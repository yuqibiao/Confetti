package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应RegisterActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public interface IRegisterBiz {

    void toRegister(UserInfo userInfo , IRequestCallback<UserInfo> callback);

}
