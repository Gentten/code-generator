package org.gentten.codegeneratorweb.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;

/**
 * 代码模板组,一系类模板的组合
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 13:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("代码模板组")
@EqualsAndHashCode(callSuper = false)
public class CodeTemplateGroup extends BaseOperatorEntity {

    @ApiModelProperty(value = "代码模块组名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "包名，模板组的优先级大于模板的包名的优先级")
    private String packageName;
}
