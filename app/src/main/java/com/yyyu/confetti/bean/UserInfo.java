package com.yyyu.confetti.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 功能：用户信息
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class UserInfo extends BmobObject implements Serializable{

    private String username;
    private String password;
    private String intro;
    private String iconPath;

    public UserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
