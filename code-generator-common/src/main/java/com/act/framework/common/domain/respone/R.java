package com.act.framework.common.domain.respone;


import com.act.framework.common.enums.IResponseEnum;
import com.act.framework.common.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回信息
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
@Data
@ApiModel("统一返回结果")
public class R<T> {
    @ApiModelProperty("自定义状态码")
    private Integer code;

    @ApiModelProperty("结果处理之后的数据")
    private T data;
    /**
     * 用户看的消息
     */
    @ApiModelProperty("用户看的消息")
    private String message;
    /**
     * 原始报错信息
     */
    @ApiModelProperty("原始报错信息")
    private String extra;
    /**
     * 令牌
     */
    @ApiModelProperty("令牌")
    private String token;

    public R(IResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getDesc();
        this.data = data;
    }

    public R(IResponseEnum responseEnum, String message, T data) {
        this.code = responseEnum.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功消息
     */
    public static <T> R<T> success() {
        return new R<>(ResponseEnum.SUCCESS, null);
    }

    /**
     * 返回成功消息
     *
     * @param data 处理的数据
     * @return 封装的响应结果
     */
    public static <T> R<T> success(T data) {
        return new R<>(ResponseEnum.SUCCESS, data);
    }

    public static <T> R<T> error(String message, T data) {
        return new R<>(ResponseEnum.FAIL, message, data);
    }

    public static <T> R<T> error(IResponseEnum errorEnum, String message, T data) {
        R<T> rm = new R<>(errorEnum, data);
        rm.setMessage(message);
        return rm;
    }

    /**
     * 支持消息格式化
     *
     * @param errorEnum 返回类型
     * @param args      参数
     * @return 结果
     * @see java.util.Formatter
     */
    public static <T> R<T> error(IResponseEnum errorEnum, Object... args) {
        R<T> rm = new R<>(errorEnum, null);
        if (args.length > 0) {
            rm.setMessage(String.format(errorEnum.getDesc(), args));
        }
        return rm;
    }

    public static <T> R<T> error(Throwable e) {

        R<T> rm;
        //默认失败的消息
        //意料之外的异常 一般是数据库异常等
        //不允许到达！不允许到达！不允许到达！
        rm = R.error(ResponseEnum.UNKNOWN);
        //rm.message 为“系统内部错误” 方便前端展示 原始错误信息放在extra里 方便调试
        rm.setExtra(e.getMessage());
        return rm;
    }
}
