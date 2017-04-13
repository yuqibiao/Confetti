package com.yyyu.confetti.bean;

/**
 * 功能：详情页数据封装bean
 *
 * 将知乎日报、网易公开课、简书、CSDN数据统一封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class ItemContent {

    private String id;
    private String title;
    private String iconPath;
    private String body;//文本类容

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
