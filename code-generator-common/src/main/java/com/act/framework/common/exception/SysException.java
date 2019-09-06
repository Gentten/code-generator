package com.act.framework.common.exception;

import com.act.framework.common.enums.IResponseEnum;
import com.act.framework.common.enums.ResponseEnum;
import lombok.Getter;

/**
 * 系统异常
 * RuntimeException 可以使用断言机制
 *
 * @author duanzhiqinag
 * @date 2019-07-30 16:47
 */
@Getter
public class SysException extends RuntimeException implements ISysException {

    /**
     * 返回状态
     */
    private IResponseEnum errResponseEnum;
    /**
     * 参数
     */
    private Object[] args;
    /**
     * 原因
     */
    private Throwable cause;


    public SysException(IResponseEnum errorEnum, Object... args) {
        this.errResponseEnum = errorEnum;
        this.args = args;
    }

    public SysException(IResponseEnum errorEnum, Throwable cause, Object... args) {
        this.errResponseEnum = errorEnum;
        this.cause = cause;
        this.args = args;
    }

    public SysException(String message) {
        this.errResponseEnum = ResponseEnum.FAIL;
        this.args = new Object[]{message};
    }

    @Override
    public String getMessage() {
        return args.length > 0 ? String.format(errResponseEnum.getDesc(), args) : errResponseEnum.getDesc();
    }
}
