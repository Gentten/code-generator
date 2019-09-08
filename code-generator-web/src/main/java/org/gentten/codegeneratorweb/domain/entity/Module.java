package org.gentten.codegeneratorweb.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;

/**
 * 模块
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 13:33
 */
@Data
@Builder
@ApiModel("模块")
@EqualsAndHashCode(callSuper = false)
public class Module extends BaseOperatorEntity {

    @ApiModelProperty(value = "模块名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "包名")
    private String packageName;
}
