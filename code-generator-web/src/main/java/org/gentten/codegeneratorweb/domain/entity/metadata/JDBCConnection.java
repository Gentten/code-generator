package org.gentten.codegeneratorweb.domain.entity.metadata;

import io.swagger.annotations.ApiModelProperty;


/**
 * JDBC连接
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 16:02
 */
public class JDBCConnection {
    @ApiModelProperty("jdbc连接id")
    private String connectionId;

    @ApiModelProperty(value = "数据库表名")
    private String name;

}
