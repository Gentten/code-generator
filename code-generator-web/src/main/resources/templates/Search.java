package  [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.form.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



/**
 * [(${model.comment})]查询封装简单比较查询条件封装
 * 简单的逐字段eq条件查询
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Data
@ApiModel(value = "[(${model.comment})]简单比较查询")
public class [(${model.className})]Search{

    [# th:each = "field:${model.fields}"]
    @ApiModelProperty(value = "[(${field.comment})]")
    private [(${field.dataType.name})] [(${field.name})];

    [/]

}
