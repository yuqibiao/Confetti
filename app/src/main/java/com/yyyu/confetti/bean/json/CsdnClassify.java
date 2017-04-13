package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：CSDN分类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public class CsdnClassify {


    private int code;
    private String message;
    private String sessionId;
    private String sessionExpired;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(String sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int id;
        private String name;
        private String alias;
        private int orderColumn;
        private List<TagsBean> tags;

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

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getOrderColumn() {
            return orderColumn;
        }

        public void setOrderColumn(int orderColumn) {
            this.orderColumn = orderColumn;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {

            private int id;
            private String label;
            private int cateId;
            private String keywords;
            private String desc;
            private int inUse;
            private long createAt;
            private long updateAt;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getInUse() {
                return inUse;
            }

            public void setInUse(int inUse) {
                this.inUse = inUse;
            }

            public long getCreateAt() {
                return createAt;
            }

            public void setCreateAt(long createAt) {
                this.createAt = createAt;
            }

            public long getUpdateAt() {
                return updateAt;
            }

            public void setUpdateAt(long updateAt) {
                this.updateAt = updateAt;
            }
        }
    }
}
