package com.huidaforum.bean;

import java.util.List;

/**
 * Created by zhang on 2017/9/24.
 */

public class ReplyBean {

        /**
         * id : null
         * createTime : 2017-09-30 16:28:54
         * ywUserId : 27f11dd3fc504d86b697b39f8dd38511
         * ownerContentId : 5395b6422a334529b83809c998ebc7c5
         * ownerAnswerId : null
         * ownerText : 1111111111111
         * title : null
         * nickName : 1111
         * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
         * photoFlvPath : null
         * contentCode : null
         * offical : 1
         * officialFlag : null
         * children : []
         */

        private String id;
        private String createTime;
        private String ywUserId;
        private String ownerContentId;
        private String ownerAnswerId;
        private String ownerText;
        private String title;
        private String nickName;
        private String headPhoto;
        private String photoFlvPath;
        private String contentCode;
        private String offical;
        private String officialFlag;
        private List<?> children;

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

        public String getOwnerContentId() {
            return ownerContentId;
        }

        public void setOwnerContentId(String ownerContentId) {
            this.ownerContentId = ownerContentId;
        }

        public String getOwnerAnswerId() {
            return ownerAnswerId;
        }

        public void setOwnerAnswerId(String ownerAnswerId) {
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

        public String getPhotoFlvPath() {
            return photoFlvPath;
        }

        public void setPhotoFlvPath(String photoFlvPath) {
            this.photoFlvPath = photoFlvPath;
        }

        public String getContentCode() {
            return contentCode;
        }

        public void setContentCode(String contentCode) {
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

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
}
