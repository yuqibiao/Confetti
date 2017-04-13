package com.yyyu.confetti.model.user_state;

import android.app.Activity;

import com.yyyu.confetti.view.inter.IHomeFrgView;
import com.yyyu.confetti.view.inter.IMainView;

/**
 * 功能：用户的状态接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public interface IUserState {

    /**
     * 点击Home页的Fab按钮操作
     */
    void oprFabEdit(IHomeFrgView homeFrgView);

    /**
     * 点击侧滑菜单Icon
     */
    void oprLeftUserIcon(Activity act);

    /**
     * 点击我的收藏菜单时
     */
    void oprCollMenu(IMainView mainView);


}
