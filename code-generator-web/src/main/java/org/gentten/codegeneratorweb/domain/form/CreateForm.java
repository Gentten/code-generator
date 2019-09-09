package org.gentten.codegeneratorweb.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-09 16:59
 */
@Data
@ApiModel("通过数据连接生成代码")
public class CreateForm {
    @ApiModelProperty(value = "数据库连接,目前只支持mysql", example = "jdbc:mysql://localhost:3306/database")
    private String jdbcUrl;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "表名,下滑线形式即数据库中的形式", example = "sys_user")
    private String tableName;

    @ApiModelProperty(value = "模板名需要英文", example = "user")
    private String moduleName;

    @ApiModelProperty(value = "包名需要英文", example = "com.act.framework")
    private String packageName;


}
