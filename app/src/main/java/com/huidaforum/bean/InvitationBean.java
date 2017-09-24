package com.huidaforum.bean;

import java.util.List;

/**
 * 首页三个按钮跳转时的bean
 */

public class InvitationBean {

    /**
     * cookieValue :
     * success : true
     * data : [{"id":"3c0d8001cb8644209399c71ad267ab42","createTime":"2017-09-20 17:49:01","ywUserId":"1f93bfc0e7844f19933a2f1e049858c2","contentCode":"CONTENT201709200000000001","contentState":"publish","publishTime":"2017-09-20 17:49:01","contentType":"picture","title":"","photoFlvPath":"D:\\drawcloud_huidaluntan\\WebContent\\phone150590094065644.jpg","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"no","jingpinContent":"no","contentText":null,"lookCount":0,"contentTextShort":"大兔子和小兔子一起吃饭。小兔子捧着饭碗，对大兔子说：\u201c想你。.......","guanzhuUserId":null,"nickName":null,"headPhoto":""}]
     * errMsg :
     * fieldError : null
     */

    private String cookieValue;
    private boolean success;
    private String errMsg;
    private Object fieldError;
    private List<DataBean> data;

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getFieldError() {
        return fieldError;
    }

    public void setFieldError(Object fieldError) {
        this.fieldError = fieldError;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3c0d8001cb8644209399c71ad267ab42
         * createTime : 2017-09-20 17:49:01
         * ywUserId : 1f93bfc0e7844f19933a2f1e049858c2
         * contentCode : CONTENT201709200000000001
         * contentState : publish
         * publishTime : 2017-09-20 17:49:01
         * contentType : picture
         * title :
         * photoFlvPath : D:\drawcloud_huidaluntan\WebContent\phone150590094065644.jpg
         * zanCount : 0
         * collectCount : 0
         * commentCount : 0
         * officialFlag : no
         * awardCount : 0
         * awardTotalMoney : 0.0
         * hotContent : no
         * jingpinContent : no
         * contentText : null
         * lookCount : 0
         * contentTextShort : 大兔子和小兔子一起吃饭。小兔子捧着饭碗，对大兔子说：“想你。.......
         * guanzhuUserId : null
         * nickName : null
         * headPhoto :
         */

        private String id;
        private String createTime;
        private String ywUserId;
        private String contentCode;
        private String contentState;
        private String publishTime;
        private String contentType;
        private String title;
        private String photoFlvPath;
        private int zanCount;
        private int collectCount;
        private int commentCount;
        private String officialFlag;
        private int awardCount;
        private double awardTotalMoney;
        private String hotContent;
        private String jingpinContent;
        private Object contentText;
        private int lookCount;
        private String contentTextShort;
        private Object guanzhuUserId;
        private Object nickName;
        private String headPhoto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getYwUserId() {
            return ywUserId;
        }

        public void setYwUserId(String ywUserId) {
            this.ywUserId = ywUserId;
        }

        public String getContentCode() {
            return contentCode;
        }

        public void setContentCode(String contentCode) {
            this.contentCode = contentCode;
        }

        public String getContentState() {
            return contentState;
        }

        public void setContentState(String contentState) {
            this.contentState = contentState;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPhotoFlvPath() {
            return photoFlvPath;
        }

        public void setPhotoFlvPath(String photoFlvPath) {
            this.photoFlvPath = photoFlvPath;
        }

        public int getZanCount() {
            return zanCount;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getOfficialFlag() {
            return officialFlag;
        }

        public void setOfficialFlag(String officialFlag) {
            this.officialFlag = officialFlag;
        }

        public int getAwardCount() {
            return awardCount;
        }

        public void setAwardCount(int awardCount) {
            this.awardCount = awardCount;
        }

        public double getAwardTotalMoney() {
            return awardTotalMoney;
        }

        public void setAwardTotalMoney(double awardTotalMoney) {
            this.awardTotalMoney = awardTotalMoney;
        }

        public String getHotContent() {
            return hotContent;
        }

        public void setHotContent(String hotContent) {
            this.hotContent = hotContent;
        }

        public String getJingpinContent() {
            return jingpinContent;
        }

        public void setJingpinContent(String jingpinContent) {
            this.jingpinContent = jingpinContent;
        }

        public Object getContentText() {
            return contentText;
        }

        public void setContentText(Object contentText) {
            this.contentText = contentText;
        }

        public int getLookCount() {
            return lookCount;
        }

        public void setLookCount(int lookCount) {
            this.lookCount = lookCount;
        }

        public String getContentTextShort() {
            return contentTextShort;
        }

        public void setContentTextShort(String contentTextShort) {
            this.contentTextShort = contentTextShort;
        }

        public Object getGuanzhuUserId() {
            return guanzhuUserId;
        }

        public void setGuanzhuUserId(Object guanzhuUserId) {
            this.guanzhuUserId = guanzhuUserId;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }
    }
}
