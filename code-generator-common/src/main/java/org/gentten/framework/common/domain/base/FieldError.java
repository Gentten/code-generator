package org.gentten.framework.common.domain.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 验证字段错误
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:34
 */
@Data
@Builder
@AllArgsConstructor
public class FieldError {
    private String field;
    private String message;
    private Object value;
}
