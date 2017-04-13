package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.CollBean;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

/**
 * 功能：收藏文章业务逻辑操作
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/5
 */

public interface ICollBeanBiz {

    void saveColl(CollBean collBean , IRequestCallback<CollBean> callback);

    void getCollByCateId(String cateId , IRequestCallback<List<CollBean>> callback);

}
