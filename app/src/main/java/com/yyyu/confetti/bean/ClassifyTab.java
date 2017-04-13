package com.yyyu.confetti.bean;

import java.util.List;

/**
 * 功能：某一分类详情Tab数据
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class ClassifyTab {

    private List<TabBean> tabList;

    public static class TabBean{
        String id;
        String title;

        public TabBean() {

        }

        public TabBean(String id, String title) {
            this.id = id;
            this.title = title;
        }

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
    }

    public List<TabBean> getTabList() {
        return tabList;
    }

    public void setTabList(List<TabBean> tabList) {
        this.tabList = tabList;
    }
}
