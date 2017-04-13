package com.yyyu.confetti.bean.json;

import java.util.List;

/**
 * 功能：网易公开课详情
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/22
 */

public class NetaseContent {

    private DataBean data;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        private String title;
        private String subtitle;
        private String plid;
        private String imgPath;
        private int playCount;
        private int updatedPlayCount;
        private String type;
        private String description;
        private int hits;
        private String largeImgurl;
        private long ltime;
        private List<VideoListBean> videoList;
        private List<RecommendListBean> recommendList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPlid() {
            return plid;
        }

        public void setPlid(String plid) {
            this.plid = plid;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }


        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public int getUpdatedPlayCount() {
            return updatedPlayCount;
        }

        public void setUpdatedPlayCount(int updatedPlayCount) {
            this.updatedPlayCount = updatedPlayCount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public String getLargeImgurl() {
            return largeImgurl;
        }

        public void setLargeImgurl(String largeImgurl) {
            this.largeImgurl = largeImgurl;
        }

        public long getLtime() {
            return ltime;
        }

        public void setLtime(long ltime) {
            this.ltime = ltime;
        }

        public List<VideoListBean> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<VideoListBean> videoList) {
            this.videoList = videoList;
        }

        public List<RecommendListBean> getRecommendList() {
            return recommendList;
        }

        public void setRecommendList(List<RecommendListBean> recommendList) {
            this.recommendList = recommendList;
        }

        public static class VideoListBean {

            private String mid;
            private String plid;
            private String title;
            private int mLength;
            private String imgPath;
            private int pNumber;
            private String commentId;
            private String subtitle;
            private String subtitleLanguage;
            private int hits;
            private String mp4SdUrl;
            private String mp4HdUrl;
            private String mp4ShdUrl;
            private String mp4SdUrlOrign;
            private String mp4HdUrlOrign;
            private String mp4ShdUrlOrign;
            private boolean isStore;
            private List<?> subList;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getPlid() {
                return plid;
            }

            public void setPlid(String plid) {
                this.plid = plid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getMLength() {
                return mLength;
            }

            public void setMLength(int mLength) {
                this.mLength = mLength;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public int getPNumber() {
                return pNumber;
            }

            public void setPNumber(int pNumber) {
                this.pNumber = pNumber;
            }

            public String getCommentId() {
                return commentId;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getSubtitleLanguage() {
                return subtitleLanguage;
            }

            public void setSubtitleLanguage(String subtitleLanguage) {
                this.subtitleLanguage = subtitleLanguage;
            }


            public int getHits() {
                return hits;
            }

            public void setHits(int hits) {
                this.hits = hits;
            }

            public String getMp4SdUrl() {
                return mp4SdUrl;
            }

            public void setMp4SdUrl(String mp4SdUrl) {
                this.mp4SdUrl = mp4SdUrl;
            }

            public String getMp4HdUrl() {
                return mp4HdUrl;
            }

            public void setMp4HdUrl(String mp4HdUrl) {
                this.mp4HdUrl = mp4HdUrl;
            }

            public String getMp4ShdUrl() {
                return mp4ShdUrl;
            }

            public void setMp4ShdUrl(String mp4ShdUrl) {
                this.mp4ShdUrl = mp4ShdUrl;
            }

            public String getMp4SdUrlOrign() {
                return mp4SdUrlOrign;
            }

            public void setMp4SdUrlOrign(String mp4SdUrlOrign) {
                this.mp4SdUrlOrign = mp4SdUrlOrign;
            }

            public String getMp4HdUrlOrign() {
                return mp4HdUrlOrign;
            }

            public void setMp4HdUrlOrign(String mp4HdUrlOrign) {
                this.mp4HdUrlOrign = mp4HdUrlOrign;
            }

            public String getMp4ShdUrlOrign() {
                return mp4ShdUrlOrign;
            }

            public void setMp4ShdUrlOrign(String mp4ShdUrlOrign) {
                this.mp4ShdUrlOrign = mp4ShdUrlOrign;
            }

            public boolean isIsStore() {
                return isStore;
            }

            public void setIsStore(boolean isStore) {
                this.isStore = isStore;
            }

            public List<?> getSubList() {
                return subList;
            }

            public void setSubList(List<?> subList) {
                this.subList = subList;
            }

        }

        public static class RecommendListBean {
            private String plid;
            private String rid;
            private int rtype;
            private String title;
            private String description;
            private String courseType;
            private String tagBgColor;
            private String quantity;
            private long publishTime;
            private String picUrl;
            private String pageUrl;
            private int viewcount;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCourseType() {
                return courseType;
            }

            public void setCourseType(String courseType) {
                this.courseType = courseType;
            }

            public String getTagBgColor() {
                return tagBgColor;
            }

            public void setTagBgColor(String tagBgColor) {
                this.tagBgColor = tagBgColor;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getPageUrl() {
                return pageUrl;
            }

            public void setPageUrl(String pageUrl) {
                this.pageUrl = pageUrl;
            }

            public int getViewcount() {
                return viewcount;
            }

            public void setViewcount(int viewcount) {
                this.viewcount = viewcount;
            }

        }
    }
}
