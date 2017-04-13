package com.yyyu.confetti.bean;

/**
 * 功能：LoginActivity表单数据封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/1
 */

public class LoginFormBean {

    private String username;
    private String pwd;

    public LoginFormBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
