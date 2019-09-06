package com.act.framework.common.domain.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础实体类 带操作属性的
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseOperatorEntity对象", description = "xxx")
public class BaseOperatorEntity<T extends Model> extends BaseEntity {

    @ApiModelProperty(value = "最后操作人员ID")
    @TableField(value = "operator_id", fill = FieldFill.INSERT)
    private Long operatorId;

    @ApiModelProperty(value = "最后操作人员")
    @TableField(value = "operator_name", fill = FieldFill.INSERT)
    private String operatorName;

    @ApiModelProperty(value = "最后操作IP")
    @TableField(value = "operator_ip", fill = FieldFill.INSERT)
    private String operateIp;
}