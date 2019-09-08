/**
 * 自定义参数校验规范
 * group 指定参数校验的分组
 * 如果需要增加自己的校验逻辑如下操作即可，具体参考正则表达式校验 {@link org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator}
 * 1、定义注解
 * 2、实现ConstraintValidator 来实现校验逻辑
 *
 * @author duanzhiqinag
 * @date 2019-07-30 16:47
 */
package org.gentten.framework.common.validation;