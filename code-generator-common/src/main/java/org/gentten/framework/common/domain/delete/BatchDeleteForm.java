package org.gentten.framework.common.domain.delete;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 批量更加id集合删除表单封装
 *
 * @author : duanzhiqiang
 * @date : 2019-08-27 14:47
 */
@Data
@ApiModel("批量更加id集合删除表单封装")
public class BatchDeleteForm {
    @ApiModelProperty("需要删除的id集合")
    List<Long> ids;
}
