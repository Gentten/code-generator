package org.gentten.codegeneratorweb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import org.gentten.codegeneratorweb.domain.enums.DataType;
import org.gentten.codegeneratorweb.domain.enums.JavaType;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 字段元数据
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("字段元数据")
@EqualsAndHashCode(callSuper = false)
public class Field extends BaseOperatorEntity {

    @ApiModelProperty(value = "modelId,属于那一个模型的")
    private String modelId;

    @ApiModelProperty(value = "字段名")
    private String name;

    @ApiModelProperty(value = "注释说明")
    private String comment;

    @ApiModelProperty(value = "字段类型")
    private DataType dataType;

    @ApiModelProperty(value = "是否必须")
    private Boolean require;

    @ApiModelProperty(value = "数据库列名，默认驼峰转下滑线")

    private String columnName;

    @TableField(exist = false)
    @ApiModelProperty(value = "字段对应的java类型")
    private JavaType javaType;

}
