package com.yyyu.confetti.presenter.inter;

import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：ContentActivity对应Presenter层接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public interface IContentActPresenter {

    void getData( int type , String id , String username);

}
