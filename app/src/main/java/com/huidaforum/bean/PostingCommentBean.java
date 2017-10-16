package com.huidaforum.bean;

import java.util.List;

/**
 * Created by zhang on 2017/10/14.
 */

public class PostingCommentBean {

        /**
         * id : 3
         * createTime : 2017-09-30 23:56:40
         * ywUserId : 50fe35a63fa54180bb7d49dee689bca1
         * ownerContentId : c6df0d08774e4539b5df8002de66eb15
         * ownerAnswerId : ROOT
         * ownerText : OK
         * title : null
         * nickName : YL
         * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
         * photoFlvPath : null
         * contentCode : sdfdfs
         * offical : null
         * officialFlag : null
         * children : [{"id":"2","createTime":"2017-09-13 17:16:11","ywUserId":"27f11dd3fc504d86b697b39f8dd38511","ownerContentId":"c6df0d08774e4539b5df8002de66eb15","ownerAnswerId":"50fe35a63fa54180bb7d49dee689bca1","ownerText":"2222222222222222","title":null,"nickName":"1111","headPhoto":"http://114.115.208.130:80/phone/20170924\\c91c9d471a6f7c4acf43c72e9734d446.jpg","photoFlvPath":null,"contentCode":"sdfdfs","offical":null,"officialFlag":null,"children":[]}]
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
        private List<ChildrenBean> children;

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

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * id : 2
             * createTime : 2017-09-13 17:16:11
             * ywUserId : 27f11dd3fc504d86b697b39f8dd38511
             * ownerContentId : c6df0d08774e4539b5df8002de66eb15
             * ownerAnswerId : 50fe35a63fa54180bb7d49dee689bca1
             * ownerText : 2222222222222222
             * title : null
             * nickName : 1111
             * headPhoto : http://114.115.208.130:80/phone/20170924\c91c9d471a6f7c4acf43c72e9734d446.jpg
             * photoFlvPath : null
             * contentCode : sdfdfs
             * offical : null
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
}
