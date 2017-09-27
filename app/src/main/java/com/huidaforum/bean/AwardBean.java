package com.huidaforum.bean;

/**
 * Created by zhang on 2017/9/24.
 */

public class AwardBean {
        /**
         * id : ad53ccc10da645abb44ee41fe23d49da
         * createTime : 2017-09-20 10:01:44
         * ywUserId : 29bc80538c3547acadae68ad685530f6
         * awardedUserId : b36d7357ffa14451a27384da33fe8edc
         * ownerContentId : 66
         * awardMoney : 66.0
         * awardState : 1
         * payTime : 2017-09-22 20:58:34
         * nickName : 昵称
         * headPhoto : 头像
         */

        private String id;
        private String createTime;
        private String ywUserId;
        private String awardedUserId;
        private String ownerContentId;
        private double awardMoney;
        private String awardState;
        private String payTime;
        private String nickName;
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

        public String getAwardedUserId() {
            return awardedUserId;
        }

        public void setAwardedUserId(String awardedUserId) {
            this.awardedUserId = awardedUserId;
        }

        public String getOwnerContentId() {
            return ownerContentId;
        }

        public void setOwnerContentId(String ownerContentId) {
            this.ownerContentId = ownerContentId;
        }

        public double getAwardMoney() {
            return awardMoney;
        }

        public void setAwardMoney(double awardMoney) {
            this.awardMoney = awardMoney;
        }

        public String getAwardState() {
            return awardState;
        }

        public void setAwardState(String awardState) {
            this.awardState = awardState;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
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
}
