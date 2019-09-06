package com.act.framework.common.constant;

/**
 * 正则表达式
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
public class RegexConstants {
    //环境正则
    public static final String DEV_ENV = ".*dev$";
    public static final String TEST_ENV = ".*test$";
    public static final String PROD_ENV = ".*prod$";
    public static final String SORT_PATTERN = ".*_.*$";

    //参数校验
    public static final String CAMEL_INVALID = "^[a-z][a-zA-Z0-9]*$";
    public static final String USERNAME_INVALID = "^[a-zA-Z][a-zA-Z0-9_-]{3,19}$";//字母开头 4-20位
    public static final String NICKNAME_INVALID = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,10}$";//2-10位 可以中文字母数字下划线
    public static final String NAME_INVALID = "^.{2,30}$";//2-30位 任意字符
    public static final String PASSWORD_INVALID = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{6,20}$";
    public static final String MOBILE = "^(1)\\d{10}$";
    public static final String BANK_CARD_INVALID = "^(622848)(\\d{9}|\\d{13})$";
    public static final String ID_CARD_INVALID = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
    public static final String EXCEL_INVALID = ".+\\.(xlsx|xls)";
    public static final String IMAGE_INVALID = ".+\\.(jpg|jpeg|png)";
    public static final String EMAIL_INVALID = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+$";
    public static final String DESCRIPTION_INVALID = "^.{0,50}$";
    //常量 允许%s
    public static final String CONSTANT_WITH_VAR_INVALID = "^[A-Z%s][%sA-Z0-9_]*$";
    //常量 大写字母开头
    public static final String CONSTANT_INVALID = "^[A-Z][A-Z0-9_]*$";
    //整数 包括正整数 0 负整数
    public static final String INTEGER_INVALID = "^(0|[1-9][0-9]*|-[1-9][0-9]*)$";
    //正整数
    public static final String POSITIVE_INTEGER_INVALID = "^[1-9]{1,}[0-9]*$";

    //AUTO GENERATED START
    //手机号格式错误
    public static final String MOBILE_INVALID = "^(1)\\d{10}$";

}