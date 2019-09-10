package org.gentten.codegeneratorweb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;

/**
 * 代码模块
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 13:33
 */
@Data
@Builder
@ApiModel("模块")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CodeModule extends BaseOperatorEntity {

    @ApiModelProperty(value = "模块中文名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "除去model包名之后,包名，即用于指定相对model包名之后代码路径")
    private String packageName;

    @ApiModelProperty("代码模板名字，对应于templates中的模板名")
    private String codeModuleName;

    @ApiModelProperty("是否包含模板的名字")
    private Boolean isContainsName;

    /**
     * 以下未非数据库字段
     */
    @ApiModelProperty("领域对象")
    @TableField(exist = false)
    private Model model;
}
