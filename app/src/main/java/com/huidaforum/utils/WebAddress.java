package com.huidaforum.utils;

/**
 * Created by lenovo on 2017/9/19.
 */

public class WebAddress {

    private static String http = "http://114.115.208.130/yw";
    private static String user = "/ywuser";///用户相关
    private static String school = "/ywschool";//学校相关
    private static String shoucang = "/ywshouchang";
    private static String me = "/ywreplyme";
    private static String system = "/sitemsg";
    private static String phonemsg = "/phonemsg";
    private static String content = "/content";//帖子相关
    private static String award = "/award";//打赏相关
    //用户
    public final static String register = http + user + "/reg";//注册
    public final static String login = http + user + "/login";//登录
    public final static String updateYwUserDetailInfo = http + user + "/updateYwUserDetailInfo";//修改个人信息
    public final static String updateUserPassword = http + user + "/updateUserPassword";//登录后用户修改密码
    public final static String updateFindPwdByPhone = http + user + "/updateFindPwdByPhone";//通过手机号修改用户密码
    public final static String updateFindPwdByEmail = http + user + "/updateFindPwdByEmail";//通过邮箱找回密码
    public final static String listYwUserDetailInfo = http + user + "/listYwUserDetailInfo";// 显示当前用户详细信息

    public final static String getBySchoolName = http + school + "/getBySchoolName";//根据学校名称进行搜索学校(支持模糊查询)
    public final static String listSchoolNames = http + school + "/listSchoolNames";//显示所有的学校名称信息
    public final static String bySchoolNameFindContent = http + content + "/bySchoolNameFindContent";//所属学校会员的发文信息

    public final static String listAllContents = http + content + "/listAllContents";//显示所有文章信息
    public final static String getContentsById = http + content + "/getContentsById";//通过id获取到当前用户发表过的文章信息
    public final static String saveContent = http + content + "/saveContent";//将编辑的文章信息保存到草稿箱或者发表，包含上传图片视频
    public final static String updateContent = http + content + "/updateContent";//修改草稿箱的文章信息
    public final static String deleteContentByUser = http + content + "/deleteContentByUser";//删除登录用户自己发表的文章记录
    public final static String seleteByContentState = http + content + "/seleteByContentState";//我的草稿箱显示
    public final static String seleteByContentJingpin = http + content + "/seleteByContentJingpin";//精品好帖显示
    public final static String seleteByContentHot = http + content + "/seleteByContentHot";//热门好帖显示
    public final static String selectByCountTime = http + content + "/selectByCountTime";//最新帖子查看
    //打赏
    public final static String createAward = http + award + "/createAward";//创建打赏订单
    public final static String selectAward = http + award + "/queryAward";//查询我的打赏
    public final static String selectGetAward = http + award + "/queryGetAward";//查询谁打赏的我
    //系统消息
    public final static String systemMsg = http + system + "/listPage";
    //评论 、回复
    public final static String replyMe = http + me + "/listPage";//谁回复我的
    public final static String commentyMe = http + me + "/pinglunme";//评论我的

    public final static String shoucanglistPages = http + shoucang + "/listPages";//我的收藏

    //短信
    public final static String sendUpdatePwdCode = http + phonemsg + "/sendUpdatePwdCode";//发送修改密码手机验证码
    public final static String sendBindNewPhoneCode = http + phonemsg + "/sendBindNewPhoneCode";//发送绑定新手机验证码
    public final static String sendBindOraPhoneCode = http + phonemsg + "/sendBindOraPhoneCode";//发送绑定原机验证码
    public final static String sendRegistCode = http + phonemsg + "/sendRegistCode";//发送注册短信验证码
    public final static String sendFindPwdCode = http + phonemsg + "/sendFindPwdCode";//发送找回密码手机验证码
}
