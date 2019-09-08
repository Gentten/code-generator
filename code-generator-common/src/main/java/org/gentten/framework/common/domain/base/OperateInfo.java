package org.gentten.framework.common.domain.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : duanzhiqiang
 * @date : 2019-07-30 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "OperateInfo对象", description = "操作人信息封装，不需要传递，从securityContext中获取")
public class OperateInfo {

    @ApiModelProperty("操作人ID")
    private Long operatorId;

    @ApiModelProperty("操作者名称")
    private String operatorName;

    @ApiModelProperty("操作IP")
    private String operateIp;

    @ApiModelProperty("操作时间")
    private Date operateTime;
}
