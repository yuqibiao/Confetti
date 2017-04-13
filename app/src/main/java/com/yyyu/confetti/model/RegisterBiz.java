package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.inter.IRegisterBiz;
import com.yyyu.confetti.net.IRequestCallback;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 功能：对应RegisterActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class RegisterBiz implements IRegisterBiz{

    @Override
    public void toRegister(final UserInfo userInfo, final IRequestCallback<UserInfo> callback) {
        userInfo.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    callback.onSuccess(userInfo);
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }
}
