package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：网易公开课首页数据
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class NetaseHome {

    private String cursor;
    private int code;
    private List<DataBean> data;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String plid;
        private String rid;
        private int rtype;
        private String courseType;
        private String title;
        private Object description;
        private int viewcount;
        private String picUrl;
        private long publishTime;
        private String quantity;
        private Object tagBgColor;
        private int flag;
        private int playcount;
        private Object pageUrl;

        public String getPlid() {
            return plid;
        }

        public void setPlid(String plid) {
            this.plid = plid;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public int getRtype() {
            return rtype;
        }

        public void setRtype(int rtype) {
            this.rtype = rtype;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public int getViewcount() {
            return viewcount;
        }

        public void setViewcount(int viewcount) {
            this.viewcount = viewcount;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Object getTagBgColor() {
            return tagBgColor;
        }

        public void setTagBgColor(Object tagBgColor) {
            this.tagBgColor = tagBgColor;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getPlaycount() {
            return playcount;
        }

        public void setPlaycount(int playcount) {
            this.playcount = playcount;
        }

        public Object getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(Object pageUrl) {
            this.pageUrl = pageUrl;
        }
    }
}
