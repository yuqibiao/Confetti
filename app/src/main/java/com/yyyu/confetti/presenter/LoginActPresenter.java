package com.yyyu.confetti.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MySPUtils;
import com.yyyu.confetti.bean.LoginFormBean;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.LoginBiz;
import com.yyyu.confetti.model.inter.ILoginBiz;
import com.yyyu.confetti.model.user_state.LoginState;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.ILoginActPresenter;
import com.yyyu.confetti.view.inter.ILoginView;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/1
 */

public class LoginActPresenter implements ILoginActPresenter {
    private static final String TAG = "LoginActPresenter";

    ILoginView loginView;
    ILoginBiz loginBiz;

    public LoginActPresenter(ILoginView loginView){
        this.loginView = loginView;
        this.loginBiz = new LoginBiz();
    }

    @Override
    public void login(final Context context) {
        LoginFormBean loginFormBean = loginView.getInputInfo();
        loginBiz.toLogin(loginFormBean, new IRequestCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
                //---do success thing
                LoginState loginState = new LoginState();
                ((MyApplication)context.getApplicationContext()).getUserStateContext().setUserState(loginState);
                loginState.notifyLogin(result);
                MyLog.e(TAG , "result==="+result.getUsername());
                MySPUtils.put(context , "USER_INFO" , new Gson().toJson(result));
                MyLog.e("userInfo==="+MySPUtils.get(context , "USER_INFO" , null));
                ((MyApplication)context.getApplicationContext()).setUserInfo(result);
                loginView.close();
            }

            @Override
            public void onFailure(Throwable throwable) {
                loginView.showSnackBar("登录失败 ");
            }
        });
    }
}
