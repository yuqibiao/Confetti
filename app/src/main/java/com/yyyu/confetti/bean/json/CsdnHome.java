package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：CSDN主页数据
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public class CsdnHome {

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

        private int blogId;
        private int articleId;
        private String title;
        private String userName;
        private String nickname;
        private String avatar;
        private String description;
        private int viewCount;
        private int commentCount;
        private String tags;
        private String postTime;
        private String url;

        public int getBlogId() {
            return blogId;
        }

        public void setBlogId(int blogId) {
            this.blogId = blogId;
        }

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getPostTime() {
            return postTime;
        }

        public void setPostTime(String postTime) {
            this.postTime = postTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
