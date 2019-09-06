package com.act.framework.common.domain.query.pagination;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 排序
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:56
 */
@Data
public class Sorter {
    @ApiModelProperty("需要排序的字段")
    private String field;
    /**
     * 升序/降序
     */
    @ApiModelProperty("升序（包含asc）或者降序（包含desc），不区分大小写,默认降序")
    private String order;
}
