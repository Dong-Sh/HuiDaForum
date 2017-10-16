package com.huidaforum.bean;

import java.util.List;

/**
 * Created by xiaojiu on 2017/10/14.
 * 我的评论bean类
 */

public class MineCommentBean {

    /**
     * id : null
     * createTime : 2017-10-16 14:34:05
     * ywUserId : 50fe35a63fa54180bb7d49dee689bca1
     * ownerContentId : 44bbb296e78945b3b1380c9a2678de6c
     * ownerAnswerId : null
     * ownerText : 66666
     * title : qwer
     * nickName : YL
     * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
     * photoFlvPath : null
     * contentCode : null
     * offical : 0
     * officialFlag : no
     * laud : null
     * shouchang : null
     * list : null
     * children : []
     */

    private Object id;
    private String createTime;
    private String ywUserId;
    private String ownerContentId;
    private Object ownerAnswerId;
    private String ownerText;
    private String title;
    private String nickName;
    private String headPhoto;
    private Object photoFlvPath;
    private Object contentCode;
    private String offical;
    private String officialFlag;
    private Object laud;
    private Object shouchang;
    private Object list;
    private List<?> children;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public String getOwnerContentId() {
        return ownerContentId;
    }

    public void setOwnerContentId(String ownerContentId) {
        this.ownerContentId = ownerContentId;
    }

    public Object getOwnerAnswerId() {
        return ownerAnswerId;
    }

    public void setOwnerAnswerId(Object ownerAnswerId) {
        this.ownerAnswerId = ownerAnswerId;
    }

    public String getOwnerText() {
        return ownerText;
    }

    public void setOwnerText(String ownerText) {
        this.ownerText = ownerText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Object getPhotoFlvPath() {
        return photoFlvPath;
    }

    public void setPhotoFlvPath(Object photoFlvPath) {
        this.photoFlvPath = photoFlvPath;
    }

    public Object getContentCode() {
        return contentCode;
    }

    public void setContentCode(Object contentCode) {
        this.contentCode = contentCode;
    }

    public String getOffical() {
        return offical;
    }

    public void setOffical(String offical) {
        this.offical = offical;
    }

    public String getOfficialFlag() {
        return officialFlag;
    }

    public void setOfficialFlag(String officialFlag) {
        this.officialFlag = officialFlag;
    }

    public Object getLaud() {
        return laud;
    }

    public void setLaud(Object laud) {
        this.laud = laud;
    }

    public Object getShouchang() {
        return shouchang;
    }

    public void setShouchang(Object shouchang) {
        this.shouchang = shouchang;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
