package com.yyyu.confetti.bean;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/17
 */

public class HomeItem {

    /**
     * 标识数据来源
     * 0：知乎日报
     * 1：网易公开课
     * 2：简书
     * 3：CSDN博客
     */
    private int type;
    private String id;
    private String title;
    private String subTitle;//非必需
    private String username; // 非必需
    private String time;//非必需
    private String contentIconPath;//非必需
    private String imgPath;

    public HomeItem(int type, String id, String title, String imgPath) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContentIconPath() {
        return contentIconPath;
    }

    public void setContentIconPath(String contentIconPath) {
        this.contentIconPath = contentIconPath;
    }
}
