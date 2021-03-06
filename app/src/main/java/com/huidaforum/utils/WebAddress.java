package com.huidaforum.utils;

import android.os.Environment;

import java.io.File;

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
    private static String guanzhu = "/ywguanzhu";//我的关注
    private static String dianzan="/ywlaud";//点赞
    private static String ywguanzhu="/ywguanzhu";//点赞
    private static String pic="/pic";//轮播图
    private static String ywanswer = "/ywanswer";
    //用户
    public final static String register = http + user + "/reg";//注册
    public final static String login = http + user + "/login";//登录
    public final static String updateYwUserDetailInfo = http + user + "/updateYwUserDetailInfo";//修改个人信息
    public final static String updateUserPassword = http + user + "/updateUserPassword";//登录后用户修改密码
    public final static String updateFindPwdByPhone = http + user + "/updateFindPwdByPhone";//通过手机号修改用户密码
    public final static String updateFindPwdByEmail = http + user + "/updateFindPwdByEmail";//通过邮箱找回密码
    public final static String listYwUserDetailInfo = http + user + "/listYwUserDetailInfo";// 显示当前用户详细信息
    public final static String getMoneyNum = http + user + "/getMoneyNum";// 查询我的账号
    public final static String createTxRec = http + "/txrec/createTxRec";// 提现
    public final static String queryMoneyRecList = http + "/moneyrec/queryMoneyRecList";//交易记录



    public final static String getBySchoolName = http + school + "/getBySchoolName";//根据学校名称进行搜索学校(支持模糊查询)
    public final static String listSchoolNames = http + school + "/listSchoolNames";//显示所有的学校名称信息
    public final static String bySchoolNameFindContent = http + content + "/bySchoolNameFindContent";//所属学校会员的发文信息

    public final static String listAllContents = http + content + "/listAllContents";//显示所有文章信息
    public final static String getContentsById = http + content + "/getContentsById";//通过id获取到当前用户发表过的文章信息
    public final static String listForCountDetailInfo = http + content + "/listForCountDetailInfo";//获取帖子详情
    public final static String listForContentAnswer = "http://114.115.208.130/yw/ywanswer/listForContentAnswer";//显示帖子所有评论
    public final static String saveContent = http + content + "/saveContent";//发表文章，包含上传图片视频
    public final static String saveContentDraft = http + content + "/saveContentDraft";//保存草稿，包含上传图片视频

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
    public final static String getshouchang = http + shoucang + "/getshouchang";//用户添加我的收藏
    public final static String shouchangdelect = http + shoucang + "/shouchangdelect";//用户删除我的收藏
    public final static String getguanzhu = http + ywguanzhu + "/getguanzhu";//用户添加我的关注
    public final static String guanzhudelect = http + ywguanzhu + "/guanzhudelect";//用户删除我的关注
    public final static String getzan=http+dianzan+"/getlaud";//用户点赞
    public final static String lauddelect=http+dianzan+"/lauddelect";//用户点赞
    //短信
    public final static String sendUpdatePwdCode = http + phonemsg + "/sendUpdatePwdCode";//发送修改密码手机验证码
    public final static String sendBindNewPhoneCode = http + phonemsg + "/sendBindNewPhoneCode";//发送绑定新手机验证码
    public final static String sendBindOraPhoneCode = http + phonemsg + "/sendBindOraPhoneCode";//发送绑定原机验证码
    public final static String sendRegistCode = http + phonemsg + "/sendRegistCode";//发送注册短信验证码
    public final static String sendFindPwdCode = http + phonemsg + "/sendFindPwdCode";//发送找回密码手机验证码
    //关注
    public final static String mineFocus = http+guanzhu+"/listPage";//用户分页查看我的关注（ｙｌ）
    public final static String deleteMineFocus = http+guanzhu+"/guanzhudelect";//删除我的关注
    //我的评论
    public final static String answerPage = http +ywanswer+"/listPage";
    //sdcard 基础保存位置
    public final static String CachePath = Environment.getExternalStorageDirectory().getPath()+
            File.separator+"HuidaForum";
    //Sdcard 保存头像位置
    public final static String IconPath = CachePath + File.separator+"icon";


    public final static String listPicForSchool = http+pic+"/listPicForModel";//轮播图

}
