package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.gentten.codegeneratorweb.domain.enums.DataType;

/**
 * 模型字段编辑表单
 *
 * @author : duanzhiqiang
 * @date : 2019-10-21 10:12
 */
@Data
public class FieldEditForm {
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
}
