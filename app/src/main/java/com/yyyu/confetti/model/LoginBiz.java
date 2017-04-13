package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.LoginFormBean;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.inter.ILoginBiz;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class LoginBiz implements ILoginBiz{

    @Override
    public void toLogin(LoginFormBean loginFormBean, final IRequestCallback<UserInfo>callback) {
        String username = loginFormBean.getUsername();
        String password = loginFormBean.getPwd();
        BmobQuery<UserInfo> bmobQuery = new BmobQuery<UserInfo>();
        bmobQuery.addWhereEqualTo("username" , username);
        bmobQuery.addWhereEqualTo("password" , password);
        bmobQuery.findObjects(new FindListener<UserInfo>() {
            @Override
            public void done(List<UserInfo> list, BmobException e) {
                if(list!=null && list.size()>0){//登录成功
                    callback.onSuccess(list.get(0));
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }

}
