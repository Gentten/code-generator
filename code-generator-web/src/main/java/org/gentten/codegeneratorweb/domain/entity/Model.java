package org.gentten.codegeneratorweb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * model元数据（描述数据的数据）
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:03
 */
@Data
@Builder
@ApiModel("model元数据")
@EqualsAndHashCode(callSuper = false)
public class Model extends BaseOperatorEntity {

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

    /**
     * 以下为非数据库字段
     */
    @TableField(exist = false)
    @ApiModelProperty("字段集合")
    private List<Field> fields;
}
