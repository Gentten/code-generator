package org.gentten.codegeneratorweb.domain.form.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-09 16:59
 */
@Data
@ApiModel("通过数据连接生成代码表单")
public class CodeGeneratorForm {
    @NotEmpty
    @ApiModelProperty(value = "数据库连接,目前只支持mysql", example = "jdbc:mysql://localhost:3306", required = true)
    private String jdbcUrl;

    @NotEmpty
    @ApiModelProperty(value = "数据库名", required = true, example = "code-generator")
    private String dbName;

    @NotEmpty
    @ApiModelProperty(value = "数据库用户名", required = true, example = "root")
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "数据库密码", required = true, example = "123456")
    private String password;

    @NotEmpty
    @ApiModelProperty(value = "表名,数据库中表名字", example = "sys_user", required = true)
    private String tableName;

    @ApiModelProperty(value = "模板名需要英文，即在包名下划分模块", example = "user")
    private String moduleName;

    @NotEmpty
    @ApiModelProperty(value = "包名需要英文，全小写逗号隔开", example = "com.act.framework", required = true)
    private String packageName;

}
