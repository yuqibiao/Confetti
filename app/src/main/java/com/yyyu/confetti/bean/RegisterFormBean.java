package com.yyyu.confetti.bean;

/**
 * 功能：RegisterActivity表单数据封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/1
 */

public class RegisterFormBean {

    private String username;
    private String pwd;
    private String rePwd;

    public RegisterFormBean() {
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

    public String getRePwd() {
        return rePwd;
    }

    public void setRePwd(String rePwd) {
        this.rePwd = rePwd;
    }
}
