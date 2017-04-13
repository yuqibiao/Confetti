package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.CollCate;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

/**
 * 功能：收藏分类业务逻辑接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/5
 */

public interface ICollCateBiz {

    void saveCollCate(CollCate collCate , IRequestCallback<CollCate> callback);

    void getAllCollCate(String userId , IRequestCallback<List<CollCate>> callback );

}
