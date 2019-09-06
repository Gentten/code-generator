package com.act.framework.common.domain.query;

import java.lang.annotation.*;

/**
 * 封装查询参数
 *
 * @author : duanzhiqiang
 * @date : 2019-08-16 14:08
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Search {
}
