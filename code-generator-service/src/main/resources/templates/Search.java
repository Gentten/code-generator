package  [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

[# th:each = "importName:${imports}" th:if="${importName}"]
import [(${importName})];
[/]
/**
 * [(${model.comment})]([(${model.className})])数据库字段查询封装
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Data
@ApiModel(value = "[(${model.comment})]([(${model.className})])数据库字段查询封装")
public class [(${model.className})]Search{
    [# th:each = "field:${model.fields}"][# th:if = "${field.dataType.name} eq 'Date'"]
    //todo: 改成自己需要的字符串转时间格式(spring提供的)默认按照数据库类型对应
    // 关于时间的表示如果是1.8及以后建议使用LocalDateTime（日期时间） LocalDate（日期）  LocalTime（时间），线程安全不带时区信息
    @DateTimeFormat(pattern = "[(${field.dataType.pattern})]")
    //todo: 改成自己需要的时间序列化转化格式（jackson）默认按照数据库类型对应
    @JsonFormat(pattern = "[(${field.dataType.pattern})]", timezone = "GMT+8")[/]
    @ApiModelProperty(value = "[(${field.comment})]")
    private [(${field.dataType.name})] [(${field.name})];
    [/]
}
