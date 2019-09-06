package com.act.framework.common.domain.tree;

import com.act.framework.common.json.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 树节点
 *
 * @author : duanzhiqiang
 * @date : 2019-08-16 16:46
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("树节点实体")
@Data
public class TreeNode<T> {
    @ApiModelProperty("节点值")
    private T value;

    @ApiModelProperty("节点名称")
    private String title;

    @ApiModelProperty("节点键")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long key;

    @ApiModelProperty("子节点")
    private List<TreeNode<T>> children;
}
