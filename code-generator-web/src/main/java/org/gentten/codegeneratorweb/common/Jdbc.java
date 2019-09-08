package org.gentten.codegeneratorweb.common;

import java.sql.Connection;

/**
 * jdbc
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 18:13
 */
public interface Jdbc {
    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPassword();

    /**
     * 获取驱动
     *
     * @return 驱动
     */
    String getDriver();

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getUsername();

    /**
     * 返回测试的 SQL
     *
     * @return sql
     */
    default String getTestSql() {
        return "select 1";
    }

    /**
     * 返回url的样例
     *
     * @return string
     */
    String urlExample();

    /**
     * 测试连接
     *
     * @param conn 连接
     * @throws Exception 异常
     */
    default void validateConnection(Connection conn) throws Exception {
    }
}
