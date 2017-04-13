package com.yyyu.confetti.model.user_state;

import android.app.Activity;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.utils.RxBus;
import com.yyyu.confetti.view.inter.IHomeFrgView;
import com.yyyu.confetti.view.inter.IMainView;

/**
 * 功能：登录状态
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class LoginState implements IUserState{

    private static final String TAG = "LoginState";

    public LoginState(){
    }

    @Override
    public void oprFabEdit(IHomeFrgView homeFrgView) {
      homeFrgView.showAddCollDialog();
    }

    @Override
    public void oprLeftUserIcon(Activity act) {

    }

    @Override
    public void oprCollMenu(IMainView mainView) {
        mainView.toCollFragment();
    }


    /**
     * 通知登录成功（header修改什么的）
     */
    public void notifyLogin(UserInfo user){
        RxBus.getInstance().post(user);
    }



}
