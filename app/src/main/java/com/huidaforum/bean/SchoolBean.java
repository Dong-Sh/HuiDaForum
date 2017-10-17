package com.huidaforum.bean;

/**
 * Created by lenovo on 2017/10/13.
 */

public class SchoolBean {

    /**
     * id : 7d424454de3a454cb810ef0380fbc835
     * createTime : 2017-09-25 20:26:21
     * createUserId : 02abe791-fb78-4062-9dee-a62aa7784daa
     * createUserName : 王振
     * schoolName : 燕山大学
     * schoolDesc : 燕山大学（Yanshan University）简称“燕大”（YSU），坐落于河北省秦皇岛市，北依燕山，南临渤海，故得名燕山大学，是河北省人民政府、教育部、工业和信息化部、国家国防科技工业局四方共建的全国重点大学，河北省重点支持的国家一流大学和世界一流学科建设高校，北京高科大学联盟成员[1]  ，入选了“海外高层次人才引进计划”、“国家建设高水平大学公派研究生项目”、“国家大学生创新性实验计划”、“卓越工程师教育培养计划”。
     * provinceId : 8da52749-9cfa-4d04-a7a3-0ae690eecc5a
     * provinceName : 河北省
     * cityId : 83642957-e1ea-4c5a-b214-ef61fef952af
     * cityName : 秦皇岛
     * schoolPhoto : null
     * schoolLunboDetail : null
     */

    private String id;
    private String createTime;
    private String createUserId;
    private String createUserName;
    private String schoolName;
    private String schoolDesc;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String schoolPhoto;
    private Object schoolLunboDetail;

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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolDesc() {
        return schoolDesc;
    }

    public void setSchoolDesc(String schoolDesc) {
        this.schoolDesc = schoolDesc;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSchoolPhoto() {
        return schoolPhoto;
    }

    public void setSchoolPhoto(String schoolPhoto) {
        this.schoolPhoto = schoolPhoto;
    }

    public Object getSchoolLunboDetail() {
        return schoolLunboDetail;
    }

    public void setSchoolLunboDetail(Object schoolLunboDetail) {
        this.schoolLunboDetail = schoolLunboDetail;
    }

    @Override
    public String toString() {
        return "SchoolBean{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolDesc='" + schoolDesc + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", schoolPhoto=" + schoolPhoto +
                ", schoolLunboDetail=" + schoolLunboDetail +
                '}';
    }
}
