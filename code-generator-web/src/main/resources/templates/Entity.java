package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.entity;

import org.gentten.framework.common.domain.base.BaseOperatorEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

[# th:each = "importName:${imports}" th:if="${importName}"]
import [(${importName})];
[/]
/**
 * [(${model.name})]
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Data
@Builder
@ApiModel("[(${model.comment})]")
@EqualsAndHashCode(callSuper = false)
public class [(${model.className})] extends BaseOperatorEntity {
    [# th:each = "field:${model.fields}"]
    @ApiModelProperty(value = "[(${field.comment})]")
    private [(${field.dataType.name})] [(${field.name})];

    [/]
}
