package com.huidaforum.bean;

import java.util.List;

/**
 * Created by gui on 2017/9/24.
 */

public class AllContentsBean {

    /**
     * cookieValue :
     * success : true
     * data : [{"id":"5cc635eb0b5043a6aa32142a35723775","createTime":"2017-09-24 00:26:23","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"CONTENT201709240000000003","contentState":"publish","publishTime":"2017-09-24 00:26:23","contentType":"flv","title":"aa","photoFlvPath":"http://114.115.208.130:80/phone/20170924\\32c189bda55b4159a02285f19aff9f34.mp4","zanCount":0,"collectCount":1,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"no","jingpinContent":"no","contentText":null,"lookCount":0,"contentTextShort":"图片","guanzhuUserId":null,"nickName":null,"headPhoto":""},{"id":"5395b6422a334529b83809c998ebc7c5","createTime":"2017-09-24 00:28:10","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"CONTENT201709240000000004","contentState":"publish","publishTime":"2017-09-24 00:28:10","contentType":"picture","title":"aa","photoFlvPath":"http://114.115.208.130:80/phone/20170924\\2f1361773abf4d43bf3c86f894aa0b7c.png","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"no","jingpinContent":"no","contentText":null,"lookCount":0,"contentTextShort":"图片","guanzhuUserId":null,"nickName":null,"headPhoto":""},{"id":"c6df0d08774e4539b5df8002de66eb15","createTime":"2017-09-24 00:29:32","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"CONTENT201709240000000005","contentState":"publish","publishTime":"2017-09-24 00:29:32","contentType":"flv","title":"aa","photoFlvPath":"http://114.115.208.130:80/phone/20170924\\c89cfb2de5af4996bf3504e08e99372e.mp4","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"no","jingpinContent":"no","contentText":null,"lookCount":0,"contentTextShort":"图片","guanzhuUserId":null,"nickName":null,"headPhoto":""},{"id":"2dc53982878c4323afad1e5912e10749","createTime":"2017-09-23 23:51:11","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"CONTENT201709230000000002","contentState":"publish","publishTime":"2017-09-23 23:51:11","contentType":"word","title":"aa","photoFlvPath":null,"zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"no","jingpinContent":"no","contentText":null,"lookCount":0,"contentTextShort":"中文","guanzhuUserId":null,"nickName":null,"headPhoto":""}]
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
         * id : 5cc635eb0b5043a6aa32142a35723775
         * createTime : 2017-09-24 00:26:23
         * ywUserId : 50fe35a63fa54180bb7d49dee689bca1
         * contentCode : CONTENT201709240000000003
         * contentState : publish
         * publishTime : 2017-09-24 00:26:23
         * contentType : flv
         * title : aa
         * photoFlvPath : http://114.115.208.130:80/phone/20170924\32c189bda55b4159a02285f19aff9f34.mp4
         * zanCount : 0
         * collectCount : 1
         * commentCount : 0
         * officialFlag : no
         * awardCount : 0
         * awardTotalMoney : 0.0
         * hotContent : no
         * jingpinContent : no
         * contentText : null
         * lookCount : 0
         * contentTextShort : 图片
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
