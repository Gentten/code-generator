package org.gentten.framework.common.domain.query;


import org.gentten.framework.common.domain.query.pagination.Pageable;
import org.gentten.framework.common.domain.query.pagination.Sorter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.text.ParseException;
import java.util.Date;


/**
 * 基础查询条件
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:56
 */
@Getter
@Setter
public abstract class BaseQuery<T> {
    @ApiModelProperty(value = "主键ID", example = "123")
    private Long id;
    @ApiModelProperty(value = "是否删除")
    private Boolean delFlag;

    @ApiModelProperty(value = "插入时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "查询模式（默认分页）,false:分页; true:不分页")
    private Boolean listMode = false;

    @Min(value = 1, message = "当前页码不合法")
    private int pageNum = 1;

    @Min(value = 1, message = "每页展示数量不合法")
    private int pageSize = 10;
    @ApiModelProperty(value = "需要排序字段")
    private Sorter sorter;

//    @ApiModelProperty(value = "查询字段(语法：search:{filed}={value})，目前只支持=", example = "search.id=123456")
//    private Map<String, Object> search;

    /**
     * 通过QueryWrapper进行复杂查询 参考官网https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
     *
     * @return QueryWrapper
     * @throws ParseException
     */
    public abstract QueryWrapper<T> buildWrapper() throws ParseException;

    public IPage buildPage() {
        return new Page(pageNum, pageSize);
    }

    public Pageable buildPageable() {
        return new Pageable(pageNum, pageSize, sorter);
    }

    public static String toLikeString(String value) {
        return "%" + value + "%";
    }
}