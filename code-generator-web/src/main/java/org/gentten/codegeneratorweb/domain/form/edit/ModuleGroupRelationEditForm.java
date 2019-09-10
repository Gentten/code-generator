package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-10 13:57
 */
@Data
public class ModuleGroupRelationEditForm {
    @ApiModelProperty(value = "表id")
    private String id;

    @NotEmpty
    @ApiModelProperty(value = "代码模板组id")
    private String groupId;

    @NotEmpty
    @ApiModelProperty(value = "代码模板id")
    private String moduleId;

    @ApiModelProperty(value = "代码模板文件名，冗余字段")
    private String codeModuleName;
}
