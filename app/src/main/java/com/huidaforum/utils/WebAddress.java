package com.huidaforum.utils;

/**
 * Created by lenovo on 2017/9/19.
 */

public class WebAddress {

    private static String http = "http://114.115.208.130/yw";

    private static String user = "/ywuser";///用户相关

    private static String school = "/ywschool";//学校相关

    private static String content = "/content";

    public final static String register = http + user + "/reg";//注册
    public final static String login = http + user + "/login";//登录
    public final static String updateYwUserDetailInfo = http + user + "/updateYwUserDetailInfo";//修改个人信息
    public final static String updateUserPassword = http + user + "/updateUserPassword";//登录后用户修改密码
    public final static String updateFindPwdByPhone = http + user + "/updateFindPwdByPhone";//通过手机号修改用户密码
    public final static String updateFindPwdByEmail = http + user + "/updateFindPwdByEmail";//通过邮箱找回密码
    public final static String listYwUserDetailInfo = http + user + "/listYwUserDetailInfo";// 显示当前用户详细信息

    public final static String getBySchoolName = http + school + "/getBySchoolName";//根据学校名称进行搜索学校(支持模糊查询)
    public final static String listSchoolNames = http + school + "/listSchoolNames";//显示所有的学校名称信息

    public final static String listAllContents = http + content + "/listAllContents";//显示所有文章信息
    public final static String getContentsById = http + content + "/getContentsById";//通过id获取到当前用户发表过的文章信息
    public final static String saveContent = http + content + "/saveContent";//将编辑的文章信息保存到草稿箱或者发表，包含上传图片视频
    public final static String updateContent = http + content + "/updateContent";//修改草稿箱的文章信息
    public final static String deleteContentByUser = http + content + "/deleteContentByUser";//删除登录用户自己发表的文章记录
    public final static String seleteByContentState = http + content + "/seleteByContentState";//我的草稿箱显示
    public final static String seleteByContentJingpin = http + content + "/seleteByContentJingpin";//精品好帖显示
    public final static String seleteByContentHot = http + content + "/seleteByContentHot";//热门好帖显示
    public final static String selectByCountTime = http + content + "/selectByCountTime";//最新帖子查看
}
