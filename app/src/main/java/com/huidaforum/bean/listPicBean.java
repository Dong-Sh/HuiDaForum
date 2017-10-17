package com.huidaforum.bean;

/**
 * Created by lenovo on 2017/10/17.
 */

public class listPicBean {

    /**
     * id : kasjdfi234sdjfadjf
     * picUrlPath : C:\Users\ienovo\Pictures\Camera Roll
     * picFilePath : C:\Users\ienovo\Pictures\Camera Roll\22.jpg
     * ownerModel : 37e49a6c94794bec907817d10d10b53a
     * ownerUserId : adfadfadfeadfcv23d
     * createTime : 2017-08-30 09:07:36
     * createUsername : 李春辉
     */

    private String id;
    private String picUrlPath;
    private String picFilePath;
    private String ownerModel;
    private String ownerUserId;
    private String createTime;
    private String createUsername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicUrlPath() {
        return picUrlPath;
    }

    public void setPicUrlPath(String picUrlPath) {
        this.picUrlPath = picUrlPath;
    }

    public String getPicFilePath() {
        return picFilePath;
    }

    public void setPicFilePath(String picFilePath) {
        this.picFilePath = picFilePath;
    }

    public String getOwnerModel() {
        return ownerModel;
    }

    public void setOwnerModel(String ownerModel) {
        this.ownerModel = ownerModel;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    @Override
    public String toString() {
        return "listPicBean{" +
                "id='" + id + '\'' +
                ", picUrlPath='" + picUrlPath + '\'' +
                ", picFilePath='" + picFilePath + '\'' +
                ", ownerModel='" + ownerModel + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUsername='" + createUsername + '\'' +
                '}';
    }
}
