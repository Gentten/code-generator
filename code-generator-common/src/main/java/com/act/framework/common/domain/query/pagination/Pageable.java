package com.act.framework.common.domain.query.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

import lombok.NoArgsConstructor;

/**
 * 分页bean
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable {

    @Min(value = 1, message = "当前页码不合法")
    private int pageNum = 1;

    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;

    private Sorter sorter;
}
