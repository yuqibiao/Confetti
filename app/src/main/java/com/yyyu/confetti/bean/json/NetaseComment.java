package com.yyyu.confetti.bean.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 功能：某一视频对应的评论
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class NetaseComment {

    private int code;
    private int againstLock;
    private int againstcount;

    private List<PostBean> newPosts;

    public class PostBean{
        @SerializedName("1")
        FuckNetase fuck;//什么垃圾网易 毁我青春 属性名数字开头

        public FuckNetase getFuck() {
            return fuck;
        }

        public void setFuck(FuckNetase fuck) {
            this.fuck = fuck;
        }
    }

    public class FuckNetase{
        @SerializedName("f")
        String userName;
        @SerializedName("t")
        String comTime;
        @SerializedName("b")
        String comContent;
        @SerializedName("v")
        int likeNum;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getComTime() {
            return comTime;
        }

        public void setComTime(String comTime) {
            this.comTime = comTime;
        }

        public String getComContent() {
            return comContent;
        }

        public void setComContent(String comContent) {
            this.comContent = comContent;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getAgainstLock() {
        return againstLock;
    }

    public void setAgainstLock(int againstLock) {
        this.againstLock = againstLock;
    }

    public int getAgainstcount() {
        return againstcount;
    }

    public void setAgainstcount(int againstcount) {
        this.againstcount = againstcount;
    }

    public List<PostBean> getNewPosts() {
        return newPosts;
    }

    public void setNewPosts(List<PostBean> newPosts) {
        this.newPosts = newPosts;
    }
}