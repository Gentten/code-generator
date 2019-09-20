package  [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

[# th:each = "importName:${imports}" th:if="${importName}"]
import [(${importName})];
[/]
/**
 * [(${model.comment})]([(${model.className})])查询封装简单比较查询条件封装
 * 简单的逐字段eq条件查询
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Data
@ApiModel(value = "[(${model.comment})]([(${model.className})])简单比较查询")
public class [(${model.className})]Search{
    [# th:each = "field:${model.fields}"][# th:if = "${field.dataType.name} eq 'Date'"]
    //todo: 改成自己需要的字符串转时间格式(spring提供的)默认年月日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //todo: 改成自己需要的时间序列化转化格式（jackson）
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")[/]
    @ApiModelProperty(value = "[(${field.comment})]")
    private [(${field.dataType.name})] [(${field.name})];
    [/]
}
