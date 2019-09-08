package org.gentten.codegeneratorweb.domain.entity.metadata;

import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 数据库元数据
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:56
 */
@Data
@Builder
@ApiModel("数据库元数据")
@EqualsAndHashCode(callSuper = false)
public class DataBase extends BaseOperatorEntity {


    @ApiModelProperty(value = "连接名")
    private String name;

}
