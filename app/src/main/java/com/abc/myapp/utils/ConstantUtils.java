package com.abc.myapp.utils;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class ConstantUtils {

    /**
     * 简单的大陆手机号格式正则表达式
     */
    public static final String MAINLAND_PHONE =
            "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
    public static final int OK = 200;
    public static final int PAGE_SIZE = 20;
    /**
     * token过期
     */
    public static final int TOKEN_EXPIRED = 700;
    /**
     * 其他设备登录
     */
    public static final int OTHER_DEVICE_LOGIN = 701;
    public static final String OFFLINE_RECEIVER = "offline_receiver";

//    /**
//     * 简单的登录密码格式正则表达式, 6-18位字符, 包含字母或数字
//     */
//    public static final String LOGIN_PWD_FORMAT = "^[a-zA-Z0-9]{6,18}$";

    /**
     * 简单的登录密码格式正则表达式, 6-18位字母+数字
     */
    public static final String LOGIN_PWD_FORMAT = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";

    /**
     * 6位数字
     */
    public static final String PAY_PWD_FORMAT = "^[0-9]{6}$";
    public static final String MY_DEBUG = "my_debug";
}
