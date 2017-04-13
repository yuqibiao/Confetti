package com.yyyu.confetti.bean;

import java.util.List;

/**
 * 功能：内容的评论（四处来源统一封装）
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class ContentCom {

    private List<Comment> commentList;


    public static class  Comment{

        String comId;
        String comContent;
        String comTime;
        String userName;
        String userIcon;
        int  likeNum;

        public String getComId() {
            return comId;
        }

        public void setComId(String comId) {
            this.comId = comId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getComContent() {
            return comContent;
        }

        public void setComContent(String comContent) {
            this.comContent = comContent;
        }

        public String getComTime() {
            return comTime;
        }

        public void setComTime(String comTime) {
            this.comTime = comTime;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
