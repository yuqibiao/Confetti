package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：知乎日报分类 数据
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ZhihuClassify {

    private int limit;
    private List<OthersBean> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<OthersBean> getOthers() {
        return others;
    }

    public void setOthers(List<OthersBean> others) {
        this.others = others;
    }

    public static class OthersBean {

        private int color;
        private String thumbnail;
        private String description;
        private int id;
        private String name;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
