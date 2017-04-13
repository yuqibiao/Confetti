package com.yyyu.confetti.bean;

import cn.bmob.v3.BmobObject;

/**
 * 功能：收藏的分类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollCate extends BmobObject{

    private String userId;
    private String cateTitle;
    private String cateIntro;

    public String getCateTitle() {
        return cateTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCateTitle(String cateTitle) {
        this.cateTitle = cateTitle;
    }

    public String getCateIntro() {
        return cateIntro;
    }

    public void setCateIntro(String cateIntro) {
        this.cateIntro = cateIntro;
    }
}
