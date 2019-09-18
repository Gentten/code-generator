package org.gentten.codegeneratorweb.domain.metadata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 表元数据（描述数据的数据）
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:03
 */
@Data
@Builder
@ApiModel("表元数据")
@EqualsAndHashCode(callSuper = false)
public class Table {

    @ApiModelProperty(value = "表名字")
    private String name;

    @ApiModelProperty(value = "注释")
    private String comment;

    @ApiModelProperty(value = "数据库")
    private String database;

    @ApiModelProperty(value = "键,主键要求表有一个为id的主键")
    private Column id;
}
