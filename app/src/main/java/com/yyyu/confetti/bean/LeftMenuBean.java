package com.yyyu.confetti.bean;

/**
 * 功能：Left Menu 封装bean
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/14
 */

public class LeftMenuBean {

    private int iconId ;
    private String title;

    public LeftMenuBean(int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public String getTitle() {
        return title;
    }

}
