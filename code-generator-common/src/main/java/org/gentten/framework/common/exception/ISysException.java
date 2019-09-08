package org.gentten.framework.common.exception;

import org.gentten.framework.common.enums.IResponseEnum;

/**
 * 系统的异常需要提供 {@link IResponseEnum}
 *
 * @author duanzhiqinag
 * @date 2019-07-30 16:47
 */
public interface ISysException {
    /**
     * 错误代码
     *
     * @return 错误代码
     */
    IResponseEnum getErrResponseEnum();

    /**
     * 参数
     *
     * @return 参数
     */
    Object[] getArgs();

    /**
     * 原因
     *
     * @return 原因
     */
    Throwable getCause();
}
