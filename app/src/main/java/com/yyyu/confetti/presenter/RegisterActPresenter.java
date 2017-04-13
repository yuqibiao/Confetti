package com.yyyu.confetti.presenter;

import android.text.TextUtils;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.RegisterFormBean;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.RegisterBiz;
import com.yyyu.confetti.model.inter.IRegisterBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IRegisterActPresenter;
import com.yyyu.confetti.view.inter.IRegisterView;

/**
 * 功能：对应RegisterActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class RegisterActPresenter implements IRegisterActPresenter{

    private static final String TAG = "RegisterActPresenter";

    private IRegisterView registerView;
    private IRegisterBiz registerBiz;

    public RegisterActPresenter(IRegisterView registerView){
        this.registerView = registerView;
        this.registerBiz = new RegisterBiz();
    }

    @Override
    public void register() {
        RegisterFormBean inputBean = registerView.getInputInfo();
        String username = inputBean.getUsername();
        String pwd = inputBean.getPwd();
        String rePwd = inputBean.getRePwd();
        if(TextUtils.isEmpty(username)){
           registerView.showSnackBar("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            registerView.showSnackBar("密码不能为空");
            return;
        }
        //---表单验证
        if(!pwd.equals(rePwd)){
            registerView.showSnackBar("密码不一致");
            return;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(pwd);
        registerBiz.toRegister(userInfo, new IRequestCallback<UserInfo>() {
            @Override
            public void onSuccess(UserInfo result) {
                //---do something
                registerView.showSnackBar("注册成功，快去登录吧！");
                registerView.toLoginAct();
            }

            @Override
            public void onFailure(Throwable throwable) {
                //---注册失败
                throwable.printStackTrace();
                MyLog.e(TAG , throwable.getMessage());
                registerView.showSnackBar("注册失败，"+throwable.getMessage());
            }
        });
    }

}
