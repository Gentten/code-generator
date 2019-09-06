package com.act.framework.common.annotation;

import java.lang.annotation.*;

/**
 * 指定表单转化实体那个字段上
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 21:37
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Transform {
    String value() default "";
}
