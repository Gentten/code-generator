package org.gentten.codegeneratorweb.domain.form.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 代码模板组查询封装简单比较查询条件封装
 * 简单的逐字段eq条件查询
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:43:53 CST 2019
 */
@Data
@ApiModel(value = "代码模板组简单比较查询")
public class CodeModuleGroupSearch{

    
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "最后操作人")
    private String operatorId;

    @ApiModelProperty(value = "操作人名")
    private String operatorName;

    @ApiModelProperty(value = "操作的IP地址")
    private String operatorIp;

    @ApiModelProperty(value = "逻辑删除字段 1删除0未删除")
    private Integer delFlag;

    @ApiModelProperty(value = "代码模板中文名")
    private String name;

    @ApiModelProperty(value = "代码模板描述")
    private String description;

    @ApiModelProperty(value = "代码生成类的包名")
    private String packageName;

    
}
