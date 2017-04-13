package com.yyyu.confetti.bean.json;

/**
 * 功能：CSDN文章详情
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public class CsdnContent {

    private int code;
    private String message;
    private DataBean data;
    private String sessionId;
    private String sessionExpired;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {

        private String type;
        private int id;
        private String title;
        private String username;
        private boolean is_digged;
        private int level;
        private String description;
        private boolean can_dig;
        private Object markdowncontent;
        private String articlemore;
        private int comment_count;
        private String tags;
        private int status;
        private String nickname;
        private int is_fav;
        private int digg;
        private int bury;
        private String create_at;
        private String comment_allowed;
        private String avatar;
        private String create;
        private String url;
        private int articleedittype;
        private String content;
        private boolean is_buryed;
        private String categories;
        private Object markdowndirectory;
        private int view_count;
        private int channel;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isIs_digged() {
            return is_digged;
        }

        public void setIs_digged(boolean is_digged) {
            this.is_digged = is_digged;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isCan_dig() {
            return can_dig;
        }

        public void setCan_dig(boolean can_dig) {
            this.can_dig = can_dig;
        }

        public Object getMarkdowncontent() {
            return markdowncontent;
        }

        public void setMarkdowncontent(Object markdowncontent) {
            this.markdowncontent = markdowncontent;
        }

        public String getArticlemore() {
            return articlemore;
        }

        public void setArticlemore(String articlemore) {
            this.articlemore = articlemore;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIs_fav() {
            return is_fav;
        }

        public void setIs_fav(int is_fav) {
            this.is_fav = is_fav;
        }

        public int getDigg() {
            return digg;
        }

        public void setDigg(int digg) {
            this.digg = digg;
        }

        public int getBury() {
            return bury;
        }

        public void setBury(int bury) {
            this.bury = bury;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getComment_allowed() {
            return comment_allowed;
        }

        public void setComment_allowed(String comment_allowed) {
            this.comment_allowed = comment_allowed;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCreate() {
            return create;
        }

        public void setCreate(String create) {
            this.create = create;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getArticleedittype() {
            return articleedittype;
        }

        public void setArticleedittype(int articleedittype) {
            this.articleedittype = articleedittype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIs_buryed() {
            return is_buryed;
        }

        public void setIs_buryed(boolean is_buryed) {
            this.is_buryed = is_buryed;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public Object getMarkdowndirectory() {
            return markdowndirectory;
        }

        public void setMarkdowndirectory(Object markdowndirectory) {
            this.markdowndirectory = markdowndirectory;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }
    }
}
