package com.act.framework.common.exception;


import com.act.framework.common.domain.base.FieldError;
import com.act.framework.common.enums.ResponseEnum;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

/**
 * 参数校验异常
 * 此异常还需返回 参数校验错误消息 单独处理
 *
 * @author duanzhiqinag
 * @date 2019-07-30 16:47
 */
@Getter
public class ValidateException extends SysException {
    private List<FieldError> errors;

    public ValidateException(List<FieldError> errors) {
        super(ResponseEnum.ARGUMENT_BIND_ERROR);
        this.errors = errors;
    }

    /**
     * @param errorField      错误字段名称
     * @param errorMessage    错误消息
     * @param errorFieldValue 错误字段值
     */
    public ValidateException(String errorField, String errorMessage, Object errorFieldValue) {
        super(ResponseEnum.ARGUMENT_BIND_ERROR);
        this.errors = Lists.newArrayList();
        FieldError fieldError = FieldError.builder()
                .field(errorField)
                .message(errorMessage)
                .value(errorFieldValue)
                .build();
        this.errors.add(fieldError);
    }
}
