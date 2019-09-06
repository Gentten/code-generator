package com.act.framework.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 返回的消息枚举
 *
 *
 * 错误码定义：
 * 成功：200
 * 参数错误 需要前端表单按字段显示错误：400
 * 未认证 需要前端跳转登录页：401
 * 未授权 需要前端跳转403页面：403
 *
 * @author duanzhiqinag
 * @date 2019-07-04 16:47
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements IResponseEnum {
    //成功
    SUCCESS(200, "ok"),

    /**
     * 系统级错误code xxx 三位数
     * 包含 传参校验错误、认证相关错误、授权相关错误、系统资源相关错误
     */
    // 1.传参校验错误 400
    ARGUMENT_BIND_ERROR(400, "参数校验失败"),
    BODY_NOT_READABLE(400, "请提供请求体,并以'application/json'提交"),

    // 2.认证相关错误
    // 401 告诉前端跳login
    // 1000+ 认证错误 不用调login的
    AUTH_NOT_LOGIN(401, "未登录"),
    //认证已过期  被顶了/退出了/到期了/登录信息失效（修改密码导致）
    AUTH_EXPIRED(401, "%s"),
    BAD_CREDENTIALS(401, "坏的凭证"),
    NO_PATH_FOUND(404, "找不到资源"),
    AUTH_EXITED(1401, "已登出"),
    AUTH_FAIL(1402, "用户名或密码错误"),
    AUTH_DISABLED(1403, "您的账号已停用"),
    OLD_PASSWORD_INVALID(1404, "旧密码错误"),
    AUTH_VERIFY_CODE_ERROR(1405, "用户名或验证码错误"),
    AUTH_NO_USER_FAIL(1406, "用户不存在"),
    // 权限问题
    PERMISSION_DENIED(3000, "operation is forbidden, please confirm your permission!"),
    // session超时
    TIME_OUT(4000, "please login again!"),

    // 3.授权相关错误 403
    NO_PERMISSION(403, "权限不足"),


    //其他 出现了意想不到的错误
    //不允许返回此错误 所有的异常必须处理成SysException
    UNKNOWN(5555, "系统内部错误"),

    // 短信服务错误
    SMS_SERVER_ERROR(1101, "短信服务连接错误"),
    SMS_CLIENT_ERROR(1102, "短信请求错误"),

    // 失败
    FAIL(-1, "%s"),
    // 参数问题
    PARAMETER(1000, ""),
    // 账号问题
    ACCOUNT(2000, ""),
    // 首次登陆，需要完善个人信息
    ACCOUNT_FIRST_LOGIN(2001, ""),
    // 账号被冻结
    ACCOUNT_FREEZE(2002, ""),
    // 密码已失效
    PASSWORD_EXPIRE(2003, ""),
    // 密码 快失效 需要提醒
    PASSWORD_EXPIRE_REMINDER(2004, ""),
    // 系统异常
    SYSTEM(5000, ""),
    // 其他问题
    OTHER_ERROR(6000, "");

    private Integer code;
    private String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return desc;
    }
}