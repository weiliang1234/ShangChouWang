package com.pdsu.wl.crowd.constant;

/**
 * 定义常量
 * @author wl
 * @Date 2021/7/21 17:07
 */
public class CrowdConstant {

    public static final String MESSAGE_LOGIN_FAILED = "抱歉！账号或密码错误！请重新输入！";
    public static final String MESSAGE_LOGIN_ALREADY_IN_USE = "抱歉！这个账号已经被使用了！";
    public static final String MESSAGE_ACCESS_FORBIDEN = "请登录以后再访问！";
    public static final String MESSAGE_STRING_INVALIDATE = "字符串不合法！请不要传入空字符串！";
    public static final Object MESSAGE_CODE_NOT_EXISTS = "验证码不存在,请重新发送!";
    public static final Object MESSAGE_CODE_INVALID = "验证码不正确";

    public static final String ATTR_NAME_EXCEPTION = "exception";
    public static final String ATTR_NAME_LOGIN_ADMIN = "loginAdmin";
    public static final String ATTR_NAME_PAGE_INFO = "pageInfo";
    public static final String REDIS_CODE_PREFIX = "REDIS_CODE_PREFIX_";
    public static final String ATTR_NAME_MESSAGE = "message";
    public static final String ATTR_NAME_LOGIN_MEMBER = "loginMember";
}
