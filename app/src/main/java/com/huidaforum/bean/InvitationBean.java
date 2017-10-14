package com.huidaforum.bean;

import java.util.List;

/**
 * 首页三个按钮跳转时的bean
 */

public class InvitationBean {

    /**
     * cookieValue :
     * success : true
     * data : [{"id":"2dc53982878c4323afad1e5912e10749","createTime":"2017-10-14 14:17:36","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"12333","contentState":"publish","publishTime":"2017-10-14 14:17:59","contentType":"picture","title":"sdff","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/7ec3426219de44dcae7af8467b229e5f","zanCount":0,"collectCount":17,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"yes","jingpinContent":"yes","contentText":null,"lookCount":1,"contentTextShort":"有一个人作了","videoPictureThumbnail":null,"ownerContentId":"ROOT","forwardContentText":null,"nickName":"YL","headPhoto":"http://114.115.208.130:80/phone/20170924\\c91c9d471a6f7c4acf43c72e9734d446.jpg","manyTime":"1小时前","ghuanzhu":"no","laud":"no","shouchang":"no","answer":"no"},{"id":"5395b6422a334529b83809c998ebc7c5","createTime":"2017-10-14 14:29:27","ywUserId":"073e6b9c098d409aac18702b6ba13164","contentCode":"21233","contentState":"publish","publishTime":"2017-10-14 14:29:55","contentType":"picture","title":"ewewe","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/7ec3426219de44dcae7af8467b229e5f","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"yes","jingpinContent":"yes","contentText":null,"lookCount":6,"contentTextShort":"个人作了","videoPictureThumbnail":null,"ownerContentId":"ROOT","forwardContentText":null,"nickName":"YL","headPhoto":null,"manyTime":"1小时前","ghuanzhu":"no","laud":"no","shouchang":"no","answer":"no"},{"id":"c6df0d08774e4539b5df8002de66eb15","createTime":"2017-10-14 14:34:12","ywUserId":"27f11dd3fc504d86b697b39f8dd38511","contentCode":"sdfdfs","contentState":"publish","publishTime":"2017-10-14 14:34:51","contentType":"pictue","title":"sddsf","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/7ec3426219de44dcae7af8467b229e5f","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"yes","jingpinContent":"yes","contentText":null,"lookCount":0,"contentTextShort":"进到第一层楼","videoPictureThumbnail":null,"ownerContentId":"ROOT","forwardContentText":null,"nickName":"1111","headPhoto":"http://114.115.208.130:80/phone/20170924\\c91c9d471a6f7c4acf43c72e9734d446.jpg","manyTime":"1小时前","ghuanzhu":"no","laud":"no","shouchang":"no","answer":"no"},{"id":"5369b89d423a472eb31052d682e3b608","createTime":"2017-10-13 15:58:35","ywUserId":"50fe35a63fa54180bb7d49dee689bca1","contentCode":"CONTENT201710130000000010","contentState":"publish","publishTime":"2017-10-13 15:58:35","contentType":"picture","title":"aa","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/5369b89d423a472eb31052d682e3b608","zanCount":0,"collectCount":2,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"yes","jingpinContent":"yes","contentText":null,"lookCount":0,"contentTextShort":"有一个人经过热闹的火车站前，看到一个双腿残障的人摆设铅笔小摊","videoPictureThumbnail":"","ownerContentId":"ROOT","forwardContentText":"","nickName":"YL","headPhoto":"http://114.115.208.130:80/phone/20170924\\c91c9d471a6f7c4acf43c72e9734d446.jpg","manyTime":"昨天","ghuanzhu":"no","laud":"no","shouchang":"no","answer":"no"},{"id":"7ec3426219de44dcae7af8467b229e5f","createTime":"2017-10-13 15:57:30","ywUserId":"073e6b9c098d409aac18702b6ba13164","contentCode":"CONTENT201710130000000009","contentState":"publish","publishTime":"2017-10-13 15:57:30","contentType":"picture","title":"aa","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/7ec3426219de44dcae7af8467b229e5f","zanCount":0,"collectCount":0,"commentCount":0,"officialFlag":"no","awardCount":0,"awardTotalMoney":0,"hotContent":"yes","jingpinContent":"yes","contentText":null,"lookCount":0,"contentTextShort":"有一个人作了一个梦，梦中他来到一间二层楼的屋子。进到第一层楼","videoPictureThumbnail":"","ownerContentId":"ROOT","forwardContentText":"","nickName":"YL","headPhoto":null,"manyTime":"昨天","ghuanzhu":"no","laud":"no","shouchang":"no","answer":"no"}]
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
         * id : 2dc53982878c4323afad1e5912e10749
         * createTime : 2017-10-14 14:17:36
         * ywUserId : 50fe35a63fa54180bb7d49dee689bca1
         * contentCode : 12333
         * contentState : publish
         * publishTime : 2017-10-14 14:17:59
         * contentType : picture
         * title : sdff
         * photoFlvPath : http://owx7eunef.bkt.clouddn.com/7ec3426219de44dcae7af8467b229e5f
         * zanCount : 0
         * collectCount : 17
         * commentCount : 0
         * officialFlag : no
         * awardCount : 0
         * awardTotalMoney : 0.0
         * hotContent : yes
         * jingpinContent : yes
         * contentText : null
         * lookCount : 1
         * contentTextShort : 有一个人作了
         * videoPictureThumbnail : null
         * ownerContentId : ROOT
         * forwardContentText : null
         * nickName : YL
         * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
         * manyTime : 1小时前
         * ghuanzhu : no
         * laud : no
         * shouchang : no
         * answer : no
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
        private Object videoPictureThumbnail;
        private String ownerContentId;
        private Object forwardContentText;
        private String nickName;
        private String headPhoto;
        private String manyTime;
        private String ghuanzhu;
        private String laud;
        private String shouchang;
        private String answer;

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

        public Object getVideoPictureThumbnail() {
            return videoPictureThumbnail;
        }

        public void setVideoPictureThumbnail(Object videoPictureThumbnail) {
            this.videoPictureThumbnail = videoPictureThumbnail;
        }

        public String getOwnerContentId() {
            return ownerContentId;
        }

        public void setOwnerContentId(String ownerContentId) {
            this.ownerContentId = ownerContentId;
        }

        public Object getForwardContentText() {
            return forwardContentText;
        }

        public void setForwardContentText(Object forwardContentText) {
            this.forwardContentText = forwardContentText;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public String getManyTime() {
            return manyTime;
        }

        public void setManyTime(String manyTime) {
            this.manyTime = manyTime;
        }

        public String getGhuanzhu() {
            return ghuanzhu;
        }

        public void setGhuanzhu(String ghuanzhu) {
            this.ghuanzhu = ghuanzhu;
        }

        public String getLaud() {
            return laud;
        }

        public void setLaud(String laud) {
            this.laud = laud;
        }

        public String getShouchang() {
            return shouchang;
        }

        public void setShouchang(String shouchang) {
            this.shouchang = shouchang;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
