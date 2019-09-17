package org.gentten.codegeneratorweb.domain.form.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 代码模板组关联表查询封装简单比较查询条件封装
 * 简单的逐字段eq条件查询
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:44:37 CST 2019
 */
@Data
@ApiModel(value = "代码模板组关联表简单比较查询")
public class ModuleGroupRelationSearch{

    
    @ApiModelProperty(value = "比较查询条件：主键")
    private String id;

    @ApiModelProperty(value = "比较查询条件：创建时间")
    private Date createTime;

    @ApiModelProperty(value = "比较查询条件：更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "比较查询条件：最后操作人")
    private String operatorId;

    @ApiModelProperty(value = "比较查询条件：操作人名")
    private String operatorName;

    @ApiModelProperty(value = "比较查询条件：操作的IP地址")
    private String operatorIp;

    @ApiModelProperty(value = "比较查询条件：逻辑删除字段 1删除0未删除")
    private Integer delFlag;

    @ApiModelProperty(value = "比较查询条件：代码模板组id")
    private String groupId;

    @ApiModelProperty(value = "比较查询条件：代码模板id")
    private String moduleId;

    @ApiModelProperty(value = "代码模板文件名，冗余字段")
    private String codeModuleName;

    
}
