package org.gentten.codegeneratorweb.domain.entity;

import lombok.*;
import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 数据库元数据
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据库元数据")
@EqualsAndHashCode(callSuper = false)
public class DataBase extends BaseOperatorEntity {


    @ApiModelProperty(value = "连接名")
    private String name;

}
