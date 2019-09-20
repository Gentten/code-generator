package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
[# th:each = "importName:${imports}" th:if="${importName}"]
import [(${importName})];
[/]
/**
 * [(${model.comment})] ([(${model.className})])创建增加表单封装  并且根据不同的校验组检验不同的数据
 * 封装前端传递的数据 增加或者是修改
 * 1.@NotNull(groups = {Update.class}, message = MessageConstants.ID_REQUIRED) 表示更新实体时，id不能为空
 * 2.@Null(groups = {Create.class}, message = MessageConstants.ID_MUST_NULL) 表示创建实体时，id必须为空
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Data
@ApiModel(value = "[(${model.comment})]([(${model.className})])编辑创建表单")
public class [(${model.className})]EditForm {
    [# th:each = "field:${model.fields}"][# th:if = "${field.dataType.name} eq 'Date'"]
    //todo: 改成自己需要的字符串转时间格式(spring提供的)默认年月日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //todo: 改成自己需要的时间序列化转化格式（jackson）默认年月日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")[/]
    @ApiModelProperty(value = "[(${field.comment})]")
    private [(${field.dataType.name})] [(${field.name})];
    [/]
}
