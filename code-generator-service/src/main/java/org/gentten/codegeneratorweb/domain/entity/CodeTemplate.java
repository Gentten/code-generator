package org.gentten.codegeneratorweb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;

/**
 * 代码模板实体
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 13:33
 */
@Data
@Builder
@ApiModel("代码模板")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CodeTemplate extends BaseOperatorEntity {

    @ApiModelProperty(value = "模块中文名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "除去model包名之后,包名，即用于指定相对model包名之后代码路径")
    private String packageName;

    @ApiModelProperty("代码模板名字，对应于templates中的模板名")
    private String codeTemplateName;

    @ApiModelProperty("生成文件是否拼接模板的名字")
    private Boolean isContainsName;

    @ApiModelProperty("生成文件的后缀名，即文件类型")
    private String fileSuffix;
    /**
     * 以下未非数据库字段
     */
    @JsonIgnore
    @ApiModelProperty("领域对象，模型")
    @TableField(exist = false)
    private Model model;
}
