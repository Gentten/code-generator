package com.act.framework.common.constant;

/**
 * 常用返回消息
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
public class MessageConstants {

    //此字段值不能为空
    public static final String FILED_REQUIRED = "此字段值不能为空";

    //ID不能为空
    public static final String ID_REQUIRED = "ID不能为空";

    //ID必须为空
    public static final String ID_MUST_NULL = "ID必须为空";

    //手机号必须为空
    public static final String MOBILE_MUST_NULL = "手机号只能单独修改";

    //邮箱不能为空
    public static final String EMAIL_REQUIRED = "请输入邮箱";

    //邮箱必须为空
    public static final String EMAIL_MUST_NULL = "邮箱只能单独修改";


    //USER ID不能为空
    public static final String ROLE_ID_REQUIRED = "角色ID不能为空";

    //USER ID不能为空
    public static final String ROLE_NAME_REQUIRED = "角色名称不能为空";

    //oldPassword 错误
    public static final String OLD_PASSWORD_INVALID = "旧密码错误";

    //claims不能为空
    public static final String CLAIMS_REQUIRED = "claims不能为空";

    //状态不能为空
    public static final String STATUS_REQUIRED = "状态不能为空";


    //产品期限不能为空
    public static final String PRODUCTDEADLINE_REQUIRED = "产品期限不能为空";


    //状态必须为空
    public static final String STATUS_MUST_NULL = "状态必须为空";

    //需要用户名6-20字符
    public static final String USERNAME_REQUIRED = "请输入用户名";
    public static final String USERNAME_INVALID = "用户名必须以字母开头，至少需要4个字符，不能超过20个字符";

    //用户名必须为空
    public static final String USERNAME_MUST_NULL = "用户名不可修改";

    //用户名已存在
    public static final String USERNAME_EXISTED = "用户名已存在";

    //需要昵称2-20字符
    public static final String NICKNAME_REQUIRED = "请输入昵称";

    //需要密码
    public static final String PASSWORD_REQUIRED = "请输入密码";

    //密码必须为空
    public static final String PASSWORD_MUST_NULL = "不能传密码";

    //需要名称2-20字符
    public static final String NAME_REQUIRED = "请输入名称";
    public static final String NAME_EXISTED = "名称已存在";

    public static final String TYPE_REQUIRE_SELECT = "请选择规则类型";
    public static final String NAME_FIELD_MAX_30 = "name字段最多30个字符";
    public static final String PLURAL_NAME_MAX_30 = "名称(复数形式)最多30个字符";
    public static final String MOBILE_INVALID = "手机号格式错误";
    public static final String EMAIL_INVALID = "邮箱格式不合法";
    public static final String DESCRIPTION_MAX_50 = "描述最多50个字符";
    public static final String NICKNAME_INVALID = "昵称长度2-20位，可以使用中文字母数字下划线";
    public static final String PASSWORD_INVALID = "密码由6到20位字符组成，可使用英文大小写、数字、下划线";
    public static final String DESCRIPTION_REQUIRED = "请输入描述";

}
