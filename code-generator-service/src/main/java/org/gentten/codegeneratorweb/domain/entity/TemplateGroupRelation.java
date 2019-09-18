package org.gentten.codegeneratorweb.domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 代码模板组关联表
 *
 * @author : code-generator
 * @date : Tue Sep 10 11:45:45 CST 2019
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("代码模板组关联表")
@EqualsAndHashCode(callSuper = false)
public class TemplateGroupRelation extends BaseOperatorEntity {


    @ApiModelProperty(value = "代码模板组id")
    private String groupId;

    @ApiModelProperty(value = "代码模板id")
    private String moduleId;

    @ApiModelProperty(value = "代码模板文件名，冗余字段")
    private String codeTemplateName;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除字段 1删除0未删除")
    private Integer delFlag;
}
