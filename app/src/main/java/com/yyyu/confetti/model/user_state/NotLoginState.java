package com.yyyu.confetti.model.user_state;

import android.app.Activity;
import android.content.Context;

import com.yyyu.confetti.view.activity.LoginActivity;
import com.yyyu.confetti.view.inter.IHomeFrgView;
import com.yyyu.confetti.view.inter.IMainView;

/**
 * 功能：未登录状态
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class NotLoginState implements IUserState{
    @Override
    public void oprFabEdit(IHomeFrgView homeFrgView ) {
        homeFrgView.toLoginAct();
    }

    @Override
    public void oprLeftUserIcon(Activity act) {
        LoginActivity.startAction(act);
    }

    @Override
    public void oprCollMenu(IMainView mainView) {
        mainView.toLoginAct();
    }
}
