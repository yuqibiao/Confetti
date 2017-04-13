package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：简书具体某一分类下的数据集合
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/29
 */

public class JsClassifyData {

    List<JsItemBean> jsItemBeanList;

    public List<JsItemBean> getJsItemBeanList() {
        return jsItemBeanList;
    }

    public void setJsItemBeanList(List<JsItemBean> jsItemBeanList) {
        this.jsItemBeanList = jsItemBeanList;
    }
}
