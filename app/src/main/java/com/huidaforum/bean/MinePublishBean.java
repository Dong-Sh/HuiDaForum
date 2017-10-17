package com.huidaforum.bean;

import java.util.List;

/**
 * Created by xiaojiu on 2017/10/16.
 * 我的发表bean类
 */

public class MinePublishBean {


    /**
     * id : 44bbb296e78945b3b1380c9a2678de6c
     * createTime : 2017-10-15 15:25:22
     * ywUserId : 50fe35a63fa54180bb7d49dee689bca1
     * contentCode : CONTENT201710150000000013
     * contentState : publish
     * publishTime : 2017-10-15 15:25:22
     * contentType : picture
     * title : qwer
     * photoFlvPath : http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c
     * zanCount : 0
     * collectCount : 0
     * commentCount : 0
     * officialFlag : no
     * awardCount : 0
     * awardTotalMoney : 0
     * hotContent : no
     * jingpinContent : no
     * contentText : null
     * lookCount : 3
     * contentTextShort : qwer
     * videoPictureThumbnail : http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c?imageView2/1/w/300/h/300
     * ownerContentId : ROOT
     * forwardContentText :
     * nickName : YL
     * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
     * manyTime : 昨天
     * ghuanzhu : no
     * laud : no
     * shouchang : no
     * answer : yes
     * contentPics : [{"id":"99a70486db50478bb9eb6fe2848a3362","ownerContentId":"44bbb296e78945b3b1380c9a2678de6c","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c","videoPictureThumbnail":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c?imageView2/1/w/300/h/300","title":"qwer","shortText":"qwer","nickName":null,"officialFlag":null,"createTime":null,"publishTime":null,"contentType":null},{"id":"d3f0e84478a6494a8459933838a640d2","ownerContentId":"44bbb296e78945b3b1380c9a2678de6c","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c","videoPictureThumbnail":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c?imageView2/1/w/300/h/300","title":"qwer","shortText":"qwer","nickName":null,"officialFlag":null,"createTime":null,"publishTime":null,"contentType":null},{"id":"b653adefd0894dd4b194181e05d64866","ownerContentId":"44bbb296e78945b3b1380c9a2678de6c","photoFlvPath":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c","videoPictureThumbnail":"http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c?imageView2/1/w/300/h/300","title":"qwer","shortText":"qwer","nickName":null,"officialFlag":null,"createTime":null,"publishTime":null,"contentType":null}]
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
    private int awardTotalMoney;
    private String hotContent;
    private String jingpinContent;
    private Object contentText;
    private int lookCount;
    private String contentTextShort;
    private String videoPictureThumbnail;
    private String ownerContentId;
    private String forwardContentText;
    private String nickName;
    private String headPhoto;
    private String manyTime;
    private String ghuanzhu;
    private String laud;
    private String shouchang;
    private String answer;
    private List<ContentPicsBean> contentPics;

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

    public int getAwardTotalMoney() {
        return awardTotalMoney;
    }

    public void setAwardTotalMoney(int awardTotalMoney) {
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

    public String getVideoPictureThumbnail() {
        return videoPictureThumbnail;
    }

    public void setVideoPictureThumbnail(String videoPictureThumbnail) {
        this.videoPictureThumbnail = videoPictureThumbnail;
    }

    public String getOwnerContentId() {
        return ownerContentId;
    }

    public void setOwnerContentId(String ownerContentId) {
        this.ownerContentId = ownerContentId;
    }

    public String getForwardContentText() {
        return forwardContentText;
    }

    public void setForwardContentText(String forwardContentText) {
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

    public List<ContentPicsBean> getContentPics() {
        return contentPics;
    }

    public void setContentPics(List<ContentPicsBean> contentPics) {
        this.contentPics = contentPics;
    }

    public static class ContentPicsBean {
        /**
         * id : 99a70486db50478bb9eb6fe2848a3362
         * ownerContentId : 44bbb296e78945b3b1380c9a2678de6c
         * photoFlvPath : http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c
         * videoPictureThumbnail : http://owx7eunef.bkt.clouddn.com/44bbb296e78945b3b1380c9a2678de6c?imageView2/1/w/300/h/300
         * title : qwer
         * shortText : qwer
         * nickName : null
         * officialFlag : null
         * createTime : null
         * publishTime : null
         * contentType : null
         */

        private String id;
        private String ownerContentId;
        private String photoFlvPath;
        private String videoPictureThumbnail;
        private String title;
        private String shortText;
        private Object nickName;
        private Object officialFlag;
        private Object createTime;
        private Object publishTime;
        private Object contentType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwnerContentId() {
            return ownerContentId;
        }

        public void setOwnerContentId(String ownerContentId) {
            this.ownerContentId = ownerContentId;
        }

        public String getPhotoFlvPath() {
            return photoFlvPath;
        }

        public void setPhotoFlvPath(String photoFlvPath) {
            this.photoFlvPath = photoFlvPath;
        }

        public String getVideoPictureThumbnail() {
            return videoPictureThumbnail;
        }

        public void setVideoPictureThumbnail(String videoPictureThumbnail) {
            this.videoPictureThumbnail = videoPictureThumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShortText() {
            return shortText;
        }

        public void setShortText(String shortText) {
            this.shortText = shortText;
        }

        public Object getNickName() {
            return nickName;
        }

        public void setNickName(Object nickName) {
            this.nickName = nickName;
        }

        public Object getOfficialFlag() {
            return officialFlag;
        }

        public void setOfficialFlag(Object officialFlag) {
            this.officialFlag = officialFlag;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Object publishTime) {
            this.publishTime = publishTime;
        }

        public Object getContentType() {
            return contentType;
        }

        public void setContentType(Object contentType) {
            this.contentType = contentType;
        }
    }
}
