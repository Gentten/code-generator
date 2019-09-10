package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-10 13:57
 */
@Data
public class CodeModuleGroupEditForm {

    @ApiModelProperty(value = "表id")
    private String id;

    @ApiModelProperty(value = "代码模块组名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "包名，模板组的优先级大于模板的包名的优先级")
    private String packageName;
}
