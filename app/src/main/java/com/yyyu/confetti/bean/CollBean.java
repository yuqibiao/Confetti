package com.yyyu.confetti.bean;

import cn.bmob.v3.BmobObject;

/**
 * 功能：收藏的文章
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollBean extends BmobObject{

    private String userId;
    private String cateId;
    private String collTitle;
    private String collUrl;
    private int itemType ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCollTitle() {
        return collTitle;
    }

    public void setCollTitle(String collTitle) {
        this.collTitle = collTitle;
    }

    public String getCollUrl() {
        return collUrl;
    }

    public void setCollUrl(String collUrl) {
        this.collUrl = collUrl;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
