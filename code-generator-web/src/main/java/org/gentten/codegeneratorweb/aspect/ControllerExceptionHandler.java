package org.gentten.codegeneratorweb.aspect;


import org.gentten.framework.common.domain.base.FieldError;
import org.gentten.framework.common.domain.respone.R;
import org.gentten.framework.common.enums.ResponseEnum;
import org.gentten.framework.common.exception.ValidateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;


/**
 * controller异常处理
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 17:19
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 处理所有未知异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(e);
    }

    /**
     * 处理404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handleNoControllerException(Exception e) {
        log.error(e.getMessage(), e);
        R res = new R<>(ResponseEnum.NO_PATH_FOUND, null);
        res.setExtra(e.getMessage());
        return res;
    }

    /**
     * 由于bean validation增加 MethodValidationPostProcessor之后 无法处理 且exception message为空 所以 增加这个方法来处理结果
     * 全局处理ConstraintViolationException
     * 错误的情况下返回200 前端自行处理异常信息
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBindExceptions(final BindException e) {
        log.error(e.getMessage(), e);
        R res = new R<>(ResponseEnum.ARGUMENT_BIND_ERROR, null);
        res.setExtra(e.getMessage());
        return res;
    }

    /**
     * 参数校验异常，中文化
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBindExceptions(final MethodArgumentNotValidException e) {
        R res;
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        List<FieldError> fieldErrors = Lists.newArrayList();
        objectErrors.forEach(objectError -> {
            org.springframework.validation.FieldError error = (org.springframework.validation.FieldError) objectError;
            fieldErrors.add(FieldError.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .value(error.getRejectedValue())
                    .build());
        });

        res = R.error(new ValidateException(fieldErrors));
        return res;
    }

    /**
     * 无权限异常
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R handleAccessDeniedExceptions(final AccessDeniedException e) {
        R res = R.error(ResponseEnum.NO_PERMISSION);
        res.setExtra(e.getMessage());
        return res;
    }


}
