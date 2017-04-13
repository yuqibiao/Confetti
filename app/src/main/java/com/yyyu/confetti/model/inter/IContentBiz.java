package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：ContentActivity对应业务逻辑接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/19
 */

public interface IContentBiz {

    /**
     * 获取详情页数据
     *
     * @param iRequestCallback
     * @param type 0：知乎日报 1：网易公开课 2：简书 3：CSDN
     * @param id
     */
    void getData(IRequestCallback<ItemContent> iRequestCallback , int type , String id , String username);

}
