package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * model元数据 模型
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModelEditForm {

    @ApiModelProperty(value = "model名字")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "生成类的名字")
    private String className;

    @ApiModelProperty(value = "注释")
    private String comment;

    @NotEmpty
    @ApiModelProperty(value = "包名，需要按包名生成代码时起作用")
    private String packageName;

    @ApiModelProperty("模块名，当需要在包名下划分模块时使用")
    private String moduleName;

    @ApiModelProperty("字段集合")
    private List<FieldEditForm> fields;
}
