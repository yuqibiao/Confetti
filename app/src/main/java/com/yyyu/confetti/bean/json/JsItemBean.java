package com.yyyu.confetti.bean.json;

/**
 * 功能：简书条目对应的数据
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/29
 */

public class JsItemBean {

    private String authorName;          // 作者
    private String time;                // 时间
    private String wrapImg;          // 主图
    private String avatarImg;           // 头像
    private String title;               // 标题
    private String titleLink;           // 标题链接
    private String content;             // 内容
    private String collectionTagLink;   // 专题链接
    private String readNum;             // 阅读量
    private String talkNum;             // 评论
    private String likeNum;             // 喜欢
    private String collectionTag;       // 专题

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWrapImg() {
        return wrapImg;
    }

    public void setWrapImg(String wrapImg) {
        this.wrapImg = wrapImg;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCollectionTagLink() {
        return collectionTagLink;
    }

    public void setCollectionTagLink(String collectionTagLink) {
        this.collectionTagLink = collectionTagLink;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getTalkNum() {
        return talkNum;
    }

    public void setTalkNum(String talkNum) {
        this.talkNum = talkNum;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getCollectionTag() {
        return collectionTag;
    }

    public void setCollectionTag(String collectionTag) {
        this.collectionTag = collectionTag;
    }
}
