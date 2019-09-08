package org.gentten.codegeneratorweb.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;

/**
 * 模板组,一系类模板的组合
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 13:13
 */
@Data
@Builder
@ApiModel("模块组")
@EqualsAndHashCode(callSuper = false)
public class ModuleGroup extends BaseOperatorEntity {

    @ApiModelProperty(value = "模块元数据名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "包名，模板组的优先级大于模板的包名的优先级")
    private String packageName;
}
