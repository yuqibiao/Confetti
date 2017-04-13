package com.yyyu.confetti;

import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MySPUtils;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.di.component.ApplicationComponent;
import com.yyyu.confetti.di.component.DaggerApplicationComponent;
import com.yyyu.confetti.di.module.ApplicationModule;
import com.yyyu.confetti.model.user_state.LoginState;
import com.yyyu.confetti.model.user_state.UserStateContext;

import cn.bmob.v3.Bmob;

/**
 * 功能：自定义Application
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/12
 */

public class MyApplication extends Application{

    private static final String TAG = "MyApplication";

    private ApplicationComponent mAppComponent;
    private UserStateContext userStateContext;
    private UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        //---初始化ApplicationComponent
        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        //---登录
        userStateContext = new UserStateContext();
        //---Bomb
        Bmob.initialize(this, "331fe689b8f141a309b3e71708c667f2");
        getUserInfoIfLogin();

        MyLog.e(TAG ,"userInfo=="+ userInfo);

    }

    private void getUserInfoIfLogin() {
        String userInfoStr = (String) MySPUtils.get(this , "USER_INFO" , null);
        if(TextUtils.isEmpty(userInfoStr)){
            return;
        }
        userInfo = new Gson().fromJson(userInfoStr , UserInfo.class);
        if(userInfo !=null){
            LoginState loginState = new LoginState();
            userStateContext.setUserState(loginState);
        }
    }

    public ApplicationComponent getAppComponent(){
        return mAppComponent;
    }

    public UserStateContext getUserStateContext() {
        getUserInfoIfLogin();
        return userStateContext;
    }

    public void setUserStateContext(UserStateContext userStateContext) {
        this.userStateContext = userStateContext;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
