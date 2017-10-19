package com.huidaforum.base;

/**
 * Created by zhang on 2017/10/19.
 */

public class RecordBean {
        /**
         * id : db8d5bea05274a8cbe3cdbaf13319b66
         * createTime : 2017-09-22 18:53:00
         * moneyType : DS
         * moneyRemark :
         * moneyInOut : MONEYOUT
         * ownerBillId : a9f210af5e1142d3a803eb3b727ae164
         * ywUserId : 29bc80538c3547acadae68ad685530f6
         * moneyDeal : 1113333.0
         * moneyBeforeDeal : 403.0
         * moneyAfterDeal : -1112930.0
         * nickName : 昵称
         * headPhoto : 头像
         */

        private String id;
        private String createTime;
        private String moneyType;
        private String moneyRemark;
        private String moneyInOut;
        private String ownerBillId;
        private String ywUserId;
        private double moneyDeal;
        private double moneyBeforeDeal;
        private double moneyAfterDeal;
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

        public String getMoneyType() {
            return moneyType;
        }

        public void setMoneyType(String moneyType) {
            this.moneyType = moneyType;
        }

        public String getMoneyRemark() {
            return moneyRemark;
        }

        public void setMoneyRemark(String moneyRemark) {
            this.moneyRemark = moneyRemark;
        }

        public String getMoneyInOut() {
            return moneyInOut;
        }

        public void setMoneyInOut(String moneyInOut) {
            this.moneyInOut = moneyInOut;
        }

        public String getOwnerBillId() {
            return ownerBillId;
        }

        public void setOwnerBillId(String ownerBillId) {
            this.ownerBillId = ownerBillId;
        }

        public String getYwUserId() {
            return ywUserId;
        }

        public void setYwUserId(String ywUserId) {
            this.ywUserId = ywUserId;
        }

        public double getMoneyDeal() {
            return moneyDeal;
        }

        public void setMoneyDeal(double moneyDeal) {
            this.moneyDeal = moneyDeal;
        }

        public double getMoneyBeforeDeal() {
            return moneyBeforeDeal;
        }

        public void setMoneyBeforeDeal(double moneyBeforeDeal) {
            this.moneyBeforeDeal = moneyBeforeDeal;
        }

        public double getMoneyAfterDeal() {
            return moneyAfterDeal;
        }

        public void setMoneyAfterDeal(double moneyAfterDeal) {
            this.moneyAfterDeal = moneyAfterDeal;
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
