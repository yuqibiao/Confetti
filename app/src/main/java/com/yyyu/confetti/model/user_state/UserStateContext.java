package com.yyyu.confetti.model.user_state;

import android.app.Activity;
import android.content.Context;

import com.yyyu.confetti.view.inter.IHomeFrgView;
import com.yyyu.confetti.view.inter.IMainView;

/**
 * 功能：用户状态管理
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class UserStateContext {

    private IUserState userState;

    public UserStateContext() {
        userState = new NotLoginState();
    }

    public void setUserState(IUserState userState) {
        this.userState = userState;
    }

    public void oprFabEdit(IHomeFrgView homeFrgView) {
        userState.oprFabEdit(homeFrgView);
    }

    public void oprLeftUserIcon(Activity act) {
        userState.oprLeftUserIcon(act);
    }

    public void oprCollMenu(IMainView mainView ){
        userState.oprCollMenu(mainView);
    }

}
