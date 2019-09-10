package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-10 13:57
 */
@Data
public class CodeModuleEditForm {

    @ApiModelProperty(value = "表id")
    private String id;

    @ApiModelProperty(value = "模块中文名字", required = true)
    private String name;

    @ApiModelProperty(value = "描述", required = true)
    private String description;

    @ApiModelProperty(value = "除去model包名之后,包名，即用于指定相对model包名之后代码路径", required = true)
    private String packageName;

    @ApiModelProperty(value = "代码模板名字，对应于templates中的模板名", required = true)
    private String codeModuleName;
}
