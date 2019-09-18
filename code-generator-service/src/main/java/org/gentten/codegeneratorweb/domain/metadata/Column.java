package org.gentten.codegeneratorweb.domain.metadata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.gentten.codegeneratorweb.domain.enums.DataType;


/**
 * 字段元数据
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:13
 */
@Data
@Builder
@ApiModel("列元数据")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Column {

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "字段名")
    private String name;

    @ApiModelProperty(value = "注释说明")
    private String comment;

    @ApiModelProperty(value = "字段类型")
    private DataType dataType;

    @ApiModelProperty(value = "是否可以为null")
    private String isNullable;

    @ApiModelProperty(value = "数据库")
    private String database;

    @ApiModelProperty(value = "位置")
    private Integer index;

}
